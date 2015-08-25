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

import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.DefaultAddressRequest;
import com.yj.ecard.publics.http.model.response.DefaultAddressResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.WeakHandler;

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
	public void getDefaultAddress(final Context context, final WeakHandler handler) {
		DefaultAddressRequest request = new DefaultAddressRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getDefaultAddressResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				DefaultAddressResponse defaultAddressResponse = (DefaultAddressResponse) JsonUtil.jsonToBean(response,
						DefaultAddressResponse.class);

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
}
