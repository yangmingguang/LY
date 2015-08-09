/**   
* @Title: BusinessFragment.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:45:47
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

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
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.main.BusinessTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.BusinessListResponse;
import com.yj.ecard.publics.model.BusinessBean;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.adapter.BusinessListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: BusinessFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:45:47
* 
*/

public class BusinessFragment extends BaseFragment {

	private int pageIndex = 1;
	private TextView tvLocation;
	private ListView mListView;
	private BusinessListAdapter mAdapter;
	private PullToRefreshListView mPtrListView;
	private View rootView, headerView, loadingView, emptyView;
	private List<BusinessBean> mList = new ArrayList<BusinessBean>();

	public static Fragment newInstance(Bundle bundle) {
		BusinessFragment fragment = new BusinessFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_business, null);
		initViews();
		loadAllData();
		return rootView;
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		int sortId = 0;
		BusinessTabManager.getInstance().getBusinessListData(context, handler, sortId, pageIndex);
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
		mPtrListView = (PullToRefreshListView) rootView.findViewById(R.id.lv_business);
		mPtrListView.setOnRefreshListener(onRefreshListener);
		mPtrListView.setMode(Mode.DISABLED);
		mPtrListView.setEmptyView(loadingView);

		// 设置header
		headerView = LayoutInflater.from(context).inflate(R.layout.header, null);
		tvLocation = (TextView) headerView.findViewById(R.id.tv_location);
		tvLocation.setText(CommonManager.getInstance().getLocationAddress(context));
		mListView = mPtrListView.getRefreshableView();
		mListView.addHeaderView(headerView);

		mAdapter = new BusinessListAdapter(context);
		mPtrListView.setAdapter(mAdapter);
	}

	/**
	 * 分页数据监听
	 */
	private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			mList = new ArrayList<BusinessBean>();
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
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			mPtrListView.onRefreshComplete();
			switch (msg.what) {
			case MeTabManager.onSuccess:
				BusinessListResponse response = (BusinessListResponse) msg.obj;
				mList.addAll(response.data);
				mAdapter.setList(mList);

				// 判断是否最后一页
				if (response.isLast) {
					mPtrListView.setMode(Mode.PULL_FROM_START);
				} else {
					mPtrListView.setMode(Mode.BOTH);
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
