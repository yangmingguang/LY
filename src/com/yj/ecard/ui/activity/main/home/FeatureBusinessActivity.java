/**   
* @Title: FeatureBusinessActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午10:33:25
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.main.BusinessTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.request.SortListRequest;
import com.yj.ecard.publics.http.model.response.BusinessListResponse;
import com.yj.ecard.publics.http.model.response.SortListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.BusinessBean;
import com.yj.ecard.publics.model.SortBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.business.PopSortActivity;
import com.yj.ecard.ui.adapter.BusinessListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: FeatureBusinessActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午10:33:25
* 
*/

public class FeatureBusinessActivity extends BaseActivity {

	private int sortId, areaId;
	private int pageIndex = 1;
	private TextView tvLocation;
	private ListView mListView;
	private boolean isClicked = false;
	private BusinessListAdapter mAdapter;
	private PullToRefreshListView mPtrListView;
	private View headerView, loadingView, emptyView;
	private List<BusinessBean> mList = new ArrayList<BusinessBean>();
	private List<SortBean> areaList = new ArrayList<SortBean>();
	private List<SortBean> shopList = new ArrayList<SortBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_featrue_business);
		initViews();
		loadAllData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isClicked) {
			isClicked = false;
			sortId = CommonManager.getInstance().getShopSortValue(context);
			areaId = CommonManager.getInstance().getAreaSortValue(context);

			mList.clear();
			mListView.setEmptyView(loadingView);
			pageIndex = 1;
			loadAllData();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem menuItem = menu.findItem(R.id.all_business);
		menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem paramMenuItem) {
				Intent intent = new Intent(context, PopSortActivity.class);
				intent.putParcelableArrayListExtra("areaList", (ArrayList<? extends Parcelable>) areaList);
				intent.putParcelableArrayListExtra("shopList", (ArrayList<? extends Parcelable>) shopList);
				startActivity(intent);
				isClicked = true;
				return false;
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		// 默认全部
		CommonManager.getInstance().setAreaSortValue(context, 0);
		CommonManager.getInstance().setShopSortValue(context, 0);

		sortId = 0;
		areaId = CommonManager.getInstance().getAreaId(context);

		emptyView = LayoutInflater.from(context).inflate(R.layout.empty, null);
		loadingView = LayoutInflater.from(context).inflate(R.layout.loading, null);
		mPtrListView = (PullToRefreshListView) findViewById(R.id.lv_business);
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
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		BusinessTabManager.getInstance().getBusinessListData(context, handler, areaId, sortId, pageIndex);

		getSortListData(this, Constan.AREA_TYPE);
		getSortListData(this, Constan.SHOP_TYPE);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			mPtrListView.onRefreshComplete();
			switch (msg.what) {
			case MeTabManager.onSuccess:
				emptyView.setVisibility(View.GONE);
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
				mPtrListView.setEmptyView(emptyView);
				break;

			case MeTabManager.onFailure:
				mPtrListView.setEmptyView(emptyView);
				break;
			}
			return true;
		}
	});

	/**
	 * 
	* @Title: getSortListData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param type    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getSortListData(final Context context, final int type) {
		SortListRequest request = new SortListRequest();
		request.setType(type);
		request.setAreaId(CommonManager.getInstance().getAreaId(context));
		DataFetcher.getInstance().getSortListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				final SortListResponse mSortListResponse = (SortListResponse) JsonUtil.jsonToBean(response,
						SortListResponse.class);

				// 数据响应状态
				int stateCode = mSortListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					switch (type) {
					case Constan.AREA_TYPE:
						areaList = mSortListResponse.sortList;
						break;

					case Constan.SHOP_TYPE:
						shopList = mSortListResponse.sortList;
						break;

					}
					break;

				case Constan.EMPTY_CODE:

					break;

				case Constan.ERROR_CODE:

					break;
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
			}
		}, true);
	}
}
