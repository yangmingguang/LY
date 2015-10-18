/**   
* @Title: WithdrawRecordActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-18 下午4:49:13
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.yj.ecard.R;
import com.yj.ecard.publics.http.model.request.Pager;
import com.yj.ecard.publics.http.model.request.WithdrawRecordListRequest;
import com.yj.ecard.publics.http.model.response.WithdrawRecordListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.WithdrawBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.WithdrawRecordListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: WithdrawRecordActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-18 下午4:49:13
* 
*/

public class WithdrawRecordActivity extends BaseActivity {

	private int pageIndex = 1;
	private View loadingView, emptyView;
	private PullToRefreshListView mListView;
	private WithdrawRecordListAdapter mAdapter;
	private List<WithdrawBean> mList = new ArrayList<WithdrawBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_withdraw_record);
		initView();
		loadAllData();
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		emptyView = LayoutInflater.from(context).inflate(R.layout.empty, null);
		loadingView = LayoutInflater.from(context).inflate(R.layout.loading, null);
		mListView = (PullToRefreshListView) findViewById(R.id.lv_withdraw_record);
		mListView.setOnRefreshListener(onRefreshListener);
		mListView.setMode(Mode.DISABLED);
		mListView.setEmptyView(loadingView);
		mAdapter = new WithdrawRecordListAdapter(this);
		mListView.setAdapter(mAdapter);
	}

	/**
	 * 分页数据监听
	 */
	private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			mList = new ArrayList<WithdrawBean>();
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
	 * 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void loadAllData() {
		WithdrawRecordListRequest request = new WithdrawRecordListRequest();
		//request.setUserName(UserManager.getInstance().getUserName(context));
		request.setUserName("15993335573");
		//request.setUserPwd(UserManager.getInstance().getPassword(context));
		request.setUserPwd("051DBF4F9F54BD82");
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getWithdrawRecordListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				WithdrawRecordListResponse mWithdrawRecordListResponse = (WithdrawRecordListResponse) JsonUtil
						.jsonToBean(response, WithdrawRecordListResponse.class);

				// 数据响应状态
				int stateCode = mWithdrawRecordListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					mList.addAll(mWithdrawRecordListResponse.data);
					mAdapter.setList(mList);

					// 判断是否最后一页
					if (mWithdrawRecordListResponse.isLast) {
						mListView.setMode(Mode.PULL_FROM_START);
					} else {
						mListView.setMode(Mode.BOTH);
					}
					break;

				case Constan.EMPTY_CODE:
				case Constan.ERROR_CODE:
					mListView.setEmptyView(emptyView);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				mListView.setEmptyView(emptyView);
			}
		}, true);
	}
}
