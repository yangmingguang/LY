/**   
* @Title: Tab9Fragment.java
* @Package com.yj.ecard.ui.activity.main.home.valuespike
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-5 下午10:51:16
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.valuespike;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yj.ecard.R;
import com.yj.ecard.business.main.HomeTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.ValueSpikeListResponse;
import com.yj.ecard.publics.model.ValueSpikeBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.adapter.ValueSpikeListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: Tab9Fragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-5 下午10:51:16
* 
*/

public class Tab9Fragment extends BaseFragment {

	private int pageIndex = 1;
	private PullToRefreshListView mListView;
	private ValueSpikeListAdapter mAdapter;
	private View rootView, loadingView, emptyView;
	private List<ValueSpikeBean> mList = new ArrayList<ValueSpikeBean>();

	public static Fragment newInstance(Bundle bundle) {
		Tab9Fragment fragment = new Tab9Fragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_indicator_common, null);
		initViews();
		loadAllData();
		return rootView;
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		emptyView = LayoutInflater.from(context).inflate(R.layout.empty, null);
		loadingView = LayoutInflater.from(context).inflate(R.layout.loading, null);
		mListView = (PullToRefreshListView) rootView.findViewById(R.id.lv_common);
		mListView.setOnRefreshListener(onRefreshListener);
		mListView.setMode(Mode.DISABLED);
		mListView.setEmptyView(loadingView);
		mAdapter = new ValueSpikeListAdapter(context);
		mListView.setAdapter(mAdapter);
	}

	/**
	 * 分页数据监听
	 */
	private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			mList = new ArrayList<ValueSpikeBean>();
			pageIndex = 1;
			loadAllData();
		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			pageIndex++;
			loadAllData();
		}

	};

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		HomeTabManager.getInstance().getValueSpikeListData(context, handler, getSortId(), pageIndex);
	}

	/**
	 * 
	* @Title: getSortId 
	* @Description: 获取sortId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getSortId() {
		return Constan.TAB_9;
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			mListView.onRefreshComplete();
			switch (msg.what) {
			case MeTabManager.onSuccess:
				ValueSpikeListResponse response = (ValueSpikeListResponse) msg.obj;
				mList.addAll(response.data);
				mAdapter.setList(mList);

				// 判断是否最后一页
				if (response.isLast) {
					mListView.setMode(Mode.PULL_FROM_START);
				} else {
					mListView.setMode(Mode.BOTH);
				}
				break;

			case MeTabManager.onEmpty:
				mListView.setEmptyView(emptyView);
				break;

			case MeTabManager.onFailure:

				break;
			}
			return true;
		}
	});

}
