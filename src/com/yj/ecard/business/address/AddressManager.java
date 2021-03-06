/**   
* @Title: AddressManager.java
* @Package com.yj.ecard.business.address
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-24 下午9:49:25
* @version V1.0   
*/

package com.yj.ecard.business.address;

import org.json.JSONObject;

import android.content.Context;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.AddressRequest;
import com.yj.ecard.publics.http.model.response.AddressResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.AddressBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.adapter.AddressListAdapter;

/**
* @ClassName: AddressManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-24 下午9:49:25
* 
*/

public class AddressManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	private static volatile AddressManager mAddressManager;

	/******************************单例开始********************************/

	private AddressManager() {
		// TODO Auto-generated constructor stub
	}

	public static AddressManager getInstance() {
		if (mAddressManager == null) {
			synchronized (CommonManager.class) {
				if (mAddressManager == null) {
					mAddressManager = new AddressManager();
				}
			}
		}
		return mAddressManager;
	}

	/**
	 * 
	* @Title: getDefaultAddressData 
	* @Description: 获取默认收货地址
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getDefaultAddress(final Context context, final WeakHandler handler, int id) {
		AddressRequest request = new AddressRequest();
		request.setId(id);
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getDefaultAddressResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				AddressResponse defaultAddressResponse = (AddressResponse) JsonUtil.jsonToBean(response,
						AddressResponse.class);

				// 数据响应状态
				int stateCode = defaultAddressResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, defaultAddressResponse.data));
					break;
				case Constan.EMPTY_CODE:
					handler.sendEmptyMessage(onEmpty);
					break;
				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onFailure);
			}
		}, true);
	}

	/**
	 * 
	* @Title: deleteAddressData 
	* @Description: 删除收货地址
	* @param @param context
	* @param @param handler
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteAddressData(final Context context, final AddressListAdapter mAdapter,
			final AddressBean addressBean) {
		Utils.showProgressDialog(context); // 显示dialog
		AddressRequest request = new AddressRequest();
		request.setId(addressBean.id);
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().deleteAddressResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				AddressResponse mAddressResponse = (AddressResponse) JsonUtil.jsonToBean(response,
						AddressResponse.class);
				int stateCode = mAddressResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					mAdapter.removeItem(addressBean);
					ToastUtil.show(context, mAddressResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;

				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mAddressResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;

				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_LONG);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_LONG);
			}
		}, true);
	}
}
