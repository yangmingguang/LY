/**   
* @Title: MoneyTabManager.java
* @Package com.yj.ecard.business.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-27 上午10:10:01
* @version V1.0   
*/

package com.yj.ecard.business.main;

import org.json.JSONObject;

import android.content.Context;

import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.Pager;
import com.yj.ecard.publics.http.model.request.PreferentialListRequest;
import com.yj.ecard.publics.http.model.response.PreferentialListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.WeakHandler;

/**
* @ClassName: MoneyTabManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-27 上午10:10:01
* 
*/

public class MoneyTabManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	private static volatile MoneyTabManager mMoneyTabManager;

	/******************************单例开始********************************/

	private MoneyTabManager() {
		// TODO Auto-generated constructor stub
	}

	public static MoneyTabManager getInstance() {
		if (mMoneyTabManager == null) {
			synchronized (MoneyTabManager.class) {
				if (mMoneyTabManager == null) {
					mMoneyTabManager = new MoneyTabManager();
				}
			}
		}
		return mMoneyTabManager;
	}

	/******************************单例结束********************************/

	/**
	 * 
	* @Title: getPreferentialListData 
	* @Description: 获取逛优惠列表
	* @param @param context
	* @param @param handler    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getPreferentialListData(final Context context, final WeakHandler handler, int pageIndex) {
		PreferentialListRequest request = new PreferentialListRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setAreaId(CommonManager.getInstance().getAreaId(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getPreferentialListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				PreferentialListResponse preferentialListResponse = (PreferentialListResponse) JsonUtil.jsonToBean(
						response, PreferentialListResponse.class);
				int stateCode = preferentialListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, preferentialListResponse));
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
