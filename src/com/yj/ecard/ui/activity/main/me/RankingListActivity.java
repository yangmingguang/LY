/**   
* @Title: RankingListActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午5:09:04
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import com.yj.ecard.R;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.RankingListResponse;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.RankingListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: RankingListActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午5:09:04
* 
*/

public class RankingListActivity extends BaseActivity {

	private View loadingView, emptyView;
	private RankingListAdapter mAdapter;
	private PullToRefreshListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_ranking_list);
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
		mListView = (PullToRefreshListView) findViewById(R.id.lv_ranking);
		mListView.setMode(Mode.DISABLED);
		mListView.setEmptyView(loadingView);
		mAdapter = new RankingListAdapter(context);
		mListView.setAdapter(mAdapter);
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		MeTabManager.getInstance().getRankingListData(context, handler);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case MeTabManager.onSuccess:
				RankingListResponse response = (RankingListResponse) msg.obj;
				mAdapter.setList(response.data);
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
