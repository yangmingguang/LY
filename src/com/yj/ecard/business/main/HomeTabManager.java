/**   
* @Title: HomeTabManager.java
* @Package com.yj.ecard.business.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午3:31:42
* @version V1.0   
*/

package com.yj.ecard.business.main;

import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.BalanceRequest;
import com.yj.ecard.publics.http.model.request.BannerRequest;
import com.yj.ecard.publics.http.model.request.DailyAttendanceRequest;
import com.yj.ecard.publics.http.model.request.Pager;
import com.yj.ecard.publics.http.model.request.SeckillRecordRequest;
import com.yj.ecard.publics.http.model.request.SeckillRequest;
import com.yj.ecard.publics.http.model.request.ShareRequest;
import com.yj.ecard.publics.http.model.request.ValueSpikeListRequest;
import com.yj.ecard.publics.http.model.request.WonderfulAdDetailRequest;
import com.yj.ecard.publics.http.model.request.WonderfulAdListRequest;
import com.yj.ecard.publics.http.model.response.BalanceResponse;
import com.yj.ecard.publics.http.model.response.BannerResponse;
import com.yj.ecard.publics.http.model.response.DailyAttendanceResponse;
import com.yj.ecard.publics.http.model.response.SeckillRecordResponse;
import com.yj.ecard.publics.http.model.response.SeckillResponse;
import com.yj.ecard.publics.http.model.response.ShareResponse;
import com.yj.ecard.publics.http.model.response.ValueSpikeListResponse;
import com.yj.ecard.publics.http.model.response.WonderfulAdDetailResponse;
import com.yj.ecard.publics.http.model.response.WonderfulAdListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.views.viewflow.BannerViewFlow;

/**
* @ClassName: HomeTabManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午3:31:42
* 
*/

public class HomeTabManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	private static volatile HomeTabManager mHomeTabManager;

	/******************************单例开始********************************/

	private HomeTabManager() {
		// TODO Auto-generated constructor stub
	}

	public static HomeTabManager getInstance() {
		if (mHomeTabManager == null) {
			synchronized (HomeTabManager.class) {
				if (mHomeTabManager == null) {
					mHomeTabManager = new HomeTabManager();
				}
			}
		}
		return mHomeTabManager;
	}

	/******************************单例结束********************************/

	/**
	* @Title: getBannerData 
	* @Description: 获取广告栏数据
	* @param @param context
	* @param @param bannerViewFlow    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBannerData(final Context context, final Handler handler, final BannerViewFlow bannerViewFlow) {
		BannerRequest request = new BannerRequest();
		request.setCity(CommonManager.getInstance().getCity(context));
		request.setCountry(CommonManager.getInstance().getCountry(context));
		request.setProvince(CommonManager.getInstance().getProvince(context));
		request.setAreaId(CommonManager.getInstance().getAreaId(context));
		DataFetcher.getInstance().getBannerListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				BannerResponse bannerResponse = (BannerResponse) JsonUtil.jsonToBean(response, BannerResponse.class);
				if (bannerResponse.status.code == 1 && null != bannerResponse.list) {
					bannerViewFlow.creatMyViewFlow(bannerResponse.list);
					handler.sendEmptyMessage(onSuccess);
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
	* @Title: getBalanceData 
	* @Description: 获取金额数据
	* @param @param context
	* @param @param bannerViewFlow    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBalanceData(final Context context, final TextView tvBalance) {
		BalanceRequest request = new BalanceRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setPassWord(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getBalanceResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				BalanceResponse mBalanceResponse = (BalanceResponse) JsonUtil.jsonToBean(response,
						BalanceResponse.class);

				// 数据响应状态
				int stateCode = mBalanceResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					tvBalance.setText(mBalanceResponse.myamont + "元");
					break;
				case Constan.EMPTY_CODE:
					//handler.sendEmptyMessage(onEmpty);
					break;
				case Constan.ERROR_CODE:
					//handler.sendEmptyMessage(onFailure);
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

	/**
	 * 
	* @Title: getWonderfulAdListData 
	* @Description: 获取精彩广告列表数据
	* @param @param context
	* @param @param handler
	* @param @param sortId
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWonderfulAdListData(final Context context, final WeakHandler handler, int sortId, int pageIndex) {
		WonderfulAdListRequest request = new WonderfulAdListRequest();
		request.setSortId(sortId);
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getWonderfulAdListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				WonderfulAdListResponse wonderfulAdListResponse = (WonderfulAdListResponse) JsonUtil.jsonToBean(
						response, WonderfulAdListResponse.class);

				// 数据响应状态
				int stateCode = wonderfulAdListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, wonderfulAdListResponse));
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
	* @Title: getWonderfulAdDetailData 
	* @Description: 获取精彩广告详情 
	* @param @param context
	* @param @param handler
	* @param @param sortId
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWonderfulAdDetailData(final Context context, final WeakHandler handler, int sortId, int id) {
		WonderfulAdDetailRequest request = new WonderfulAdDetailRequest();
		request.setId(id);
		request.setSortId(sortId);
		DataFetcher.getInstance().getWonderfulAdDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				WonderfulAdDetailResponse wonderfulAdDetailResponse = (WonderfulAdDetailResponse) JsonUtil.jsonToBean(
						response, WonderfulAdDetailResponse.class);

				// 数据响应状态
				int stateCode = wonderfulAdDetailResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, wonderfulAdDetailResponse));
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

	public void getSeckillData(final Context context, final TextView tvPrice, final TextView tvTitle,
			final ImageView ivLogo) {
		SeckillRequest request = new SeckillRequest();
		request.setAreaId(CommonManager.getInstance().getAreaId(context));
		request.setProvince(CommonManager.getInstance().getProvince(context));
		request.setCity(CommonManager.getInstance().getCity(context));
		request.setCountry(CommonManager.getInstance().getCountry(context));
		DataFetcher.getInstance().getSeckillResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				SeckillResponse mSeckillResponse = (SeckillResponse) JsonUtil.jsonToBean(response,
						SeckillResponse.class);

				// 数据响应状态
				int stateCode = mSeckillResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					tvTitle.setText(mSeckillResponse.title);
					tvPrice.setText("￥" + mSeckillResponse.price + "元");
					ImageLoaderUtil.load(context, ImageType.NETWORK, mSeckillResponse.picUrl,
							R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);
					break;
				case Constan.EMPTY_CODE:
					//handler.sendEmptyMessage(onEmpty);
					break;
				case Constan.ERROR_CODE:
					//handler.sendEmptyMessage(onFailure);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				//handler.sendEmptyMessage(onFailure);
			}
		}, true);
	}

	/**
	 * 
	* @Title: getValueSpikeListData 
	* @Description: 获取超值秒杀列表
	* @param @param context
	* @param @param handler
	* @param @param periodTime
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValueSpikeListData(final Context context, final WeakHandler handler, int periodTime, int pageIndex) {
		ValueSpikeListRequest request = new ValueSpikeListRequest();
		request.setPeriodTime(periodTime);
		request.setAreaId(CommonManager.getInstance().getAreaId(context));
		request.setProvince(CommonManager.getInstance().getProvince(context));
		request.setCity(CommonManager.getInstance().getCity(context));
		request.setCountry(CommonManager.getInstance().getCountry(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getValueSpikeListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ValueSpikeListResponse valueSpikeListResponse = (ValueSpikeListResponse) JsonUtil.jsonToBean(response,
						ValueSpikeListResponse.class);

				// 数据响应状态
				int stateCode = valueSpikeListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, valueSpikeListResponse));
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
	* @Description: 获取我的秒杀记录列表
	* @param @param context
	* @param @param handler
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getSeckillRecordListData(final Context context, final WeakHandler handler, int pageIndex) {
		SeckillRecordRequest request = new SeckillRecordRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getSeckillRecordListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				SeckillRecordResponse mSeckillRecordResponse = (SeckillRecordResponse) JsonUtil.jsonToBean(response,
						SeckillRecordResponse.class);

				// 数据响应状态
				int stateCode = mSeckillRecordResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, mSeckillRecordResponse));
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
	* @Title: postDailyAttendanceData 
	* @Description: 每日签到
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postDailyAttendanceData(final Context context) {
		Utils.showProgressDialog(context);
		DailyAttendanceRequest request = new DailyAttendanceRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		request.setLng(Double.parseDouble(CommonManager.getInstance().getLocationlng(context)));
		request.setLat(Double.parseDouble(CommonManager.getInstance().getLocationlat(context)));
		DataFetcher.getInstance().postDailyAttendanceResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
				LogUtil.getLogger().d("response==>" + response.toString());
				DailyAttendanceResponse mDailyAttendanceResponse = (DailyAttendanceResponse) JsonUtil.jsonToBean(
						response, DailyAttendanceResponse.class);

				// 数据响应状态
				int stateCode = mDailyAttendanceResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mDailyAttendanceResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;
				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mDailyAttendanceResponse.status.msg, ToastUtil.LENGTH_SHORT);
					break;
				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
			}
		});
	}

	/**
	 * 
	* @Title: getShareData 
	* @Description: 分享
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getShareData(final Context context) {
		ShareRequest request = new ShareRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getShareResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ShareResponse mShareResponse = (ShareResponse) JsonUtil.jsonToBean(response, ShareResponse.class);

				// 数据响应状态
				int stateCode = mShareResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mShareResponse.status.msg, ToastUtil.LENGTH_SHORT);
					break;

				case Constan.EMPTY_CODE:
					// ToastUtil.show(context, mShareResponse.status.msg, ToastUtil.LENGTH_SHORT);
					break;

				case Constan.ERROR_CODE:

					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				//ToastUtil.show(context, R.string.error_tips, duration);
			}
		}, true);
	}
}
