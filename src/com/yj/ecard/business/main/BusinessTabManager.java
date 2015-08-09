/**   
* @Title: BusinessTabManager.java
* @Package com.yj.ecard.business.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午2:51:23
* @version V1.0   
*/

package com.yj.ecard.business.main;

import org.json.JSONObject;

import android.content.Context;

import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.http.model.request.BusinessDetailRequest;
import com.yj.ecard.publics.http.model.request.BusinessListRequest;
import com.yj.ecard.publics.http.model.request.Pager;
import com.yj.ecard.publics.http.model.response.BusinessDetailResponse;
import com.yj.ecard.publics.http.model.response.BusinessListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.WeakHandler;

/**
* @ClassName: BusinessTabManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午2:51:23
* 
*/

public class BusinessTabManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	private static volatile BusinessTabManager mBusinessTabManager;

	/******************************单例开始********************************/

	private BusinessTabManager() {
		// TODO Auto-generated constructor stub
	}

	public static BusinessTabManager getInstance() {
		if (mBusinessTabManager == null) {
			synchronized (BusinessTabManager.class) {
				if (mBusinessTabManager == null) {
					mBusinessTabManager = new BusinessTabManager();
				}
			}
		}
		return mBusinessTabManager;
	}

	/******************************单例结束********************************/

	/**
	* @Title: getBusinessListData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param handler
	* @param @param sortId
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBusinessListData(final Context context, final WeakHandler handler, int sortId, int pageIndex) {
		BusinessListRequest request = new BusinessListRequest();
		request.setAreaId(CommonManager.getInstance().getAreaId(context));
		request.setSortId(sortId);
		request.setLatitude(Double.parseDouble(CommonManager.getInstance().getLocationlat(context)));
		request.setLongitude(Double.parseDouble(CommonManager.getInstance().getLocationlng(context)));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getBusinessListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				BusinessListResponse businessListResponse = (BusinessListResponse) JsonUtil.jsonToBean(response,
						BusinessListResponse.class);

				// 数据响应状态
				int stateCode = businessListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, businessListResponse));
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
	* @Title: getBusinessDetailData 
	* @Description: 获取商家详情 
	* @param @param context
	* @param @param handler
	* @param @param id
	* @param @param latitude
	* @param @param longitude    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBusinessDetailData(final Context context, final WeakHandler handler, int id, double latitude,
			double longitude) {
		BusinessDetailRequest request = new BusinessDetailRequest();
		request.setId(id);
		request.setLatitude(latitude);
		request.setLongitude(longitude);
		DataFetcher.getInstance().getBusinessDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				BusinessDetailResponse businessDetailResponse = (BusinessDetailResponse) JsonUtil.jsonToBean(response,
						BusinessDetailResponse.class);

				// 数据响应状态
				int stateCode = businessDetailResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, businessDetailResponse));
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
