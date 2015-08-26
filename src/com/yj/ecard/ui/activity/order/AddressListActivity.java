/**   
* @Title: AddressListActivity.java
* @Package com.yj.ecard.ui.activity.order
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-26 下午8:10:01
* @version V1.0   
*/

package com.yj.ecard.ui.activity.order;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.AddressListRequest;
import com.yj.ecard.publics.http.model.response.AddressListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.AddressListAdapter;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshBase.Mode;
import com.yj.ecard.ui.views.pulltorefresh.PullToRefreshListView;

/**
* @ClassName: AddressListActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-26 下午8:10:01
* 
*/

public class AddressListActivity extends BaseActivity {

	private AddressListAdapter mAdapter;
	private View loadingView, emptyView;
	private PullToRefreshListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_address_list);
		initView();
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
		mListView = (PullToRefreshListView) findViewById(R.id.lv_address);
		mListView.setMode(Mode.DISABLED);
		mListView.setEmptyView(loadingView);
		mAdapter = new AddressListAdapter(this);
		mListView.setAdapter(mAdapter);

		getAddressListData();
	}

	/**
	 * 
	* @Title: getAddressListData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void getAddressListData() {
		AddressListRequest request = new AddressListRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getAddressListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				AddressListResponse addressListResponse = (AddressListResponse) JsonUtil.jsonToBean(response,
						AddressListResponse.class);
				int stateCode = addressListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					mAdapter.setList(addressListResponse.data);
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
				mListView.setEmptyView(emptyView);
			}
		}, true);
	}

}
