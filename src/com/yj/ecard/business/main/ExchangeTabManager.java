/**   
* @Title: ExchangeTabManager.java
* @Package com.yj.ecard.business.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午12:17:25
* @version V1.0   
*/

package com.yj.ecard.business.main;

import org.json.JSONObject;

import android.content.Context;

import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.ExchangeAddressRequest;
import com.yj.ecard.publics.http.model.request.ExchangeListRequest;
import com.yj.ecard.publics.http.model.request.ExchangeRecordListRequest;
import com.yj.ecard.publics.http.model.request.Pager;
import com.yj.ecard.publics.http.model.response.ExchangeAddressResponse;
import com.yj.ecard.publics.http.model.response.ExchangeListResponse;
import com.yj.ecard.publics.http.model.response.ExchangeRecordListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.WeakHandler;

/**
* @ClassName: ExchangeTabManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午12:17:25
* 
*/

public class ExchangeTabManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	private static volatile ExchangeTabManager mExchangeTabManager;

	/******************************单例开始********************************/

	private ExchangeTabManager() {
		// TODO Auto-generated constructor stub
	}

	public static ExchangeTabManager getInstance() {
		if (mExchangeTabManager == null) {
			synchronized (ExchangeTabManager.class) {
				if (mExchangeTabManager == null) {
					mExchangeTabManager = new ExchangeTabManager();
				}
			}
		}
		return mExchangeTabManager;
	}

	/******************************单例结束********************************/

	/**
	 * 
	* @Title: getExchangeListData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param handler
	* @param @param sortId
	* @param @param price    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getExchangeListData(final Context context, final WeakHandler handler, int sortId, int pageIndex) {
		ExchangeListRequest request = new ExchangeListRequest();
		request.setSortId(sortId);
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getProductListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ExchangeListResponse exchangeListResponse = (ExchangeListResponse) JsonUtil.jsonToBean(response,
						ExchangeListResponse.class);

				// 数据响应状态
				int stateCode = exchangeListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, exchangeListResponse));
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
	* @Title: getExchangeRecordListData 
	* @Description: 获取兑换记录列表
	* @param @param context
	* @param @param handler
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getExchangeRecordListData(final Context context, final WeakHandler handler, int pageIndex) {
		ExchangeRecordListRequest request = new ExchangeRecordListRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getExchangeRecordListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ExchangeRecordListResponse exchangeRecordListResponse = (ExchangeRecordListResponse) JsonUtil
						.jsonToBean(response, ExchangeRecordListResponse.class);

				// 数据响应状态
				int stateCode = exchangeRecordListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, exchangeRecordListResponse));
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
	* @Title: postExchangeAddressData 
	* @Description: 提交收货地址
	* @param @param context
	* @param @param handler
	* @param @param name
	* @param @param phone
	* @param @param address    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postExchangeAddressData(final Context context, final WeakHandler handler, int proId, String realName,
			String mobile, String address, String postNum, String sign) {
		ExchangeAddressRequest request = new ExchangeAddressRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setAddress(address);
		request.setMobile(mobile);
		request.setPostNum(postNum);
		request.setProId(proId);
		request.setRealName(realName);
		request.setSign(sign);
		DataFetcher.getInstance().postExchangeAddressResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ExchangeAddressResponse mExchangeAddressResponse = (ExchangeAddressResponse) JsonUtil.jsonToBean(
						response, ExchangeAddressResponse.class);

				// 数据响应状态
				int stateCode = mExchangeAddressResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, mExchangeAddressResponse));
					break;
				case Constan.EMPTY_CODE:
					handler.sendMessage(handler.obtainMessage(onEmpty, mExchangeAddressResponse));
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
		});
	}
}
