/**   
* @Title: SeckillRecordActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午5:06:35
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.valuespike;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.yj.ecard.R;
import com.yj.ecard.business.main.HomeTabManager;
import com.yj.ecard.publics.http.model.response.SeckillRecordResponse;
import com.yj.ecard.publics.model.ExchangeRecordBean;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.ExchangeRecordListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: SeckillRecordActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午5:06:35
* 
*/

public class SeckillRecordActivity extends BaseActivity {

	private int pageIndex = 1;
	private View loadingView, emptyView;
	private PullToRefreshListView mListView;
	private ExchangeRecordListAdapter mAdapter;
	private List<ExchangeRecordBean> mList = new ArrayList<ExchangeRecordBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exchange_record);
		initViews();
		loadAllData();
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
		mListView = (PullToRefreshListView) findViewById(R.id.lv_exchange_record);
		mListView.setOnRefreshListener(onRefreshListener);
		mListView.setMode(Mode.DISABLED);
		mListView.setEmptyView(loadingView);
		mAdapter = new ExchangeRecordListAdapter(context);
		mListView.setAdapter(mAdapter);
	}

	/**
	 * 分页数据监听
	 */
	private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			mList = new ArrayList<ExchangeRecordBean>();
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
		HomeTabManager.getInstance().getSeckillRecordListData(context, handler, pageIndex);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			mListView.onRefreshComplete();
			switch (msg.what) {
			case HomeTabManager.onSuccess:
				SeckillRecordResponse response = (SeckillRecordResponse) msg.obj;
				mList.addAll(response.data);
				mAdapter.setList(mList);

				// 判断是否最后一页
				if (response.isLast) {
					mListView.setMode(Mode.PULL_FROM_START);
				} else {
					mListView.setMode(Mode.BOTH);
				}
				break;

			case HomeTabManager.onEmpty:
				mListView.setEmptyView(emptyView);
				break;

			case HomeTabManager.onFailure:
				mListView.setEmptyView(emptyView);
				break;
			}
			return true;
		}
	});
}
