/**   
* @Title: FriendsListActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午5:09:04
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.response.FriendsListResponse;
import com.yj.ecard.publics.model.FriendsBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.FriendsListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: FriendsListActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午5:09:04
* 
*/

public class FriendsListActivity extends BaseActivity {

	private int pageIndex = 1;
	private TextView tvUserName;
	private ImageView ivLogo, ivLevel;
	private FriendsListAdapter mAdapter;
	private View loadingView, emptyView;
	private PullToRefreshListView mListView;
	private List<FriendsBean> mList = new ArrayList<FriendsBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_friends_list);
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
		tvUserName = (TextView) findViewById(R.id.tv_userName);
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		ivLevel = (ImageView) findViewById(R.id.iv_level);
		tvUserName.setText(UserManager.getInstance().getUserName(context));
		int resid = Utils.getLevelDrawable(UserManager.getInstance().getLevel(context));
		String path = UserManager.getInstance().getHeadUrl(context);
		ivLevel.setBackgroundResource(resid);
		ImageLoaderUtil.load(context, ImageType.NETWORK, path, R.drawable.ic_default_head, R.drawable.ic_default_head,
				ivLogo);

		emptyView = LayoutInflater.from(context).inflate(R.layout.empty, null);
		loadingView = LayoutInflater.from(context).inflate(R.layout.loading, null);
		mListView = (PullToRefreshListView) findViewById(R.id.lv_friends);
		mListView.setOnRefreshListener(onRefreshListener);
		mListView.setMode(Mode.DISABLED);
		mListView.setEmptyView(loadingView);
		mAdapter = new FriendsListAdapter(context);
		mListView.setAdapter(mAdapter);
	}

	/**
	 * 分页数据监听
	 */
	private OnRefreshListener2<ListView> onRefreshListener = new OnRefreshListener2<ListView>() {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			mList = new ArrayList<FriendsBean>();
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
		MeTabManager.getInstance().getFriendsListData(context, handler, pageIndex);
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
				FriendsListResponse response = (FriendsListResponse) msg.obj;
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
