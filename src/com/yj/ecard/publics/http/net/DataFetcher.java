/**   
* @Title: DataFetcher.java
* @Package com.iframe.source.publics.http.net
* @Description: TODO(用一句话描述该文件做什么)
* @author mingguang.yang   
* @date 2015年1月21日 下午8:10:29
* @version V1.0   
*/

package com.yj.ecard.publics.http.net;

import org.json.JSONObject;

import com.yj.ecard.publics.http.model.request.AddAddressRequest;
import com.yj.ecard.publics.http.model.request.AddressListRequest;
import com.yj.ecard.publics.http.model.request.AddressRequest;
import com.yj.ecard.publics.http.model.request.AreaIdRequest;
import com.yj.ecard.publics.http.model.request.BalanceRequest;
import com.yj.ecard.publics.http.model.request.BannerDetailRequest;
import com.yj.ecard.publics.http.model.request.BannerRequest;
import com.yj.ecard.publics.http.model.request.BusinessDetailRequest;
import com.yj.ecard.publics.http.model.request.BusinessListRequest;
import com.yj.ecard.publics.http.model.request.CheckCodeRequest;
import com.yj.ecard.publics.http.model.request.CrashRequest;
import com.yj.ecard.publics.http.model.request.DailyAttendanceRequest;
import com.yj.ecard.publics.http.model.request.DeletePreferentialRequest;
import com.yj.ecard.publics.http.model.request.ExchangeAddressRequest;
import com.yj.ecard.publics.http.model.request.ExchangeDetailRequest;
import com.yj.ecard.publics.http.model.request.ExchangeListRequest;
import com.yj.ecard.publics.http.model.request.ExchangeRecordListRequest;
import com.yj.ecard.publics.http.model.request.FriendsListRequest;
import com.yj.ecard.publics.http.model.request.GetValidateCodeRequest;
import com.yj.ecard.publics.http.model.request.IncomeListRequest;
import com.yj.ecard.publics.http.model.request.ModifyPwRequest;
import com.yj.ecard.publics.http.model.request.MyPreferentialListRequest;
import com.yj.ecard.publics.http.model.request.OrderRequest;
import com.yj.ecard.publics.http.model.request.PreferentialDetailRequest;
import com.yj.ecard.publics.http.model.request.PreferentialListRequest;
import com.yj.ecard.publics.http.model.request.ProductDetailRequest;
import com.yj.ecard.publics.http.model.request.PublishPreferentialRequest;
import com.yj.ecard.publics.http.model.request.RankingListRequest;
import com.yj.ecard.publics.http.model.request.RegisterRequest;
import com.yj.ecard.publics.http.model.request.ResetPwRequest;
import com.yj.ecard.publics.http.model.request.ScreenLockRequest;
import com.yj.ecard.publics.http.model.request.SeckillRecordRequest;
import com.yj.ecard.publics.http.model.request.SeckillRequest;
import com.yj.ecard.publics.http.model.request.SeeAdRequest;
import com.yj.ecard.publics.http.model.request.ShareRequest;
import com.yj.ecard.publics.http.model.request.TelAdListRequest;
import com.yj.ecard.publics.http.model.request.UpdateRequest;
import com.yj.ecard.publics.http.model.request.UserDataRequest;
import com.yj.ecard.publics.http.model.request.UserInfoRequest;
import com.yj.ecard.publics.http.model.request.UserLoginRequest;
import com.yj.ecard.publics.http.model.request.ValidateCodeRequest;
import com.yj.ecard.publics.http.model.request.ValueSpikeDetailRequest;
import com.yj.ecard.publics.http.model.request.ValueSpikeExchangeRequest;
import com.yj.ecard.publics.http.model.request.ValueSpikeListRequest;
import com.yj.ecard.publics.http.model.request.WithdrawBalanceRequest;
import com.yj.ecard.publics.http.model.request.WithdrawRequest;
import com.yj.ecard.publics.http.model.request.WonderfulAdDetailRequest;
import com.yj.ecard.publics.http.model.request.WonderfulAdListRequest;
import com.yj.ecard.publics.http.volley.Request.Method;
import com.yj.ecard.publics.http.volley.RequestQueue;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.toolbox.JsonObjectRequest;
import com.yj.ecard.publics.http.volley.toolbox.RequestQueueManager;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;

/**
* @ClassName: DataFetcher
* @Description: TODO(这里用一句话描述这个类的作用)
* @author mingguang.yang
* @date 2015年1月21日 下午8:10:29
* 
*/

public class DataFetcher {

	private static DataFetcher mDataFetcher;
	private static RequestQueue mRequestQueue;

	private DataFetcher() {
		mRequestQueue = RequestQueueManager.getRequestQueue();
	}

	public static synchronized DataFetcher getInstance() {
		if (mDataFetcher == null) {
			mDataFetcher = new DataFetcher();
		}
		return mDataFetcher;
	}

	// ReqMethod : "Get"
	private void get(String url, Listener<JSONObject> listener, ErrorListener errorListener, boolean shouldCache) {
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, listener, errorListener);
		LogUtil.getLogger().d("get data ==>url:" + url);
		request.setShouldCache(shouldCache);
		mRequestQueue.add(request);
	}

	// ReqMethod : "Post"
	private void post(String url, JSONObject object, Listener<JSONObject> listener, ErrorListener errorListener) {
		//JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, object, listener, errorListener);
		JsonObjectRequest request = new JsonObjectRequest(url, object, listener, errorListener);
		LogUtil.getLogger().d("post data ==>url:" + url + ",JSONObject==>" + object != null ? object.toString() : "");
		request.setShouldCache(false);
		mRequestQueue.add(request);
	}

	/**
	* @Title: getBannerListResult 
	* @Description: 获取首页广告栏数据
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBannerListResult(BannerRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_BANNER_LIST_URL, request.province, request.city, request.country,
				request.areaId);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	* @Title: getBalanceResult 
	* @Description: 获取余额
	* @param @param params
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBalanceResult(BalanceRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_BALANCE_URL, request.userName, request.passWord, request.userId);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getBusinessListResult 
	* @Description: 获取商家列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBusinessListResult(BusinessListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_BUSINESS_LIST_URL, request.areaId, request.sortId,
				request.latitude, request.longitude, request.pager.pageSize, request.pager.pageIndex);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	* @Title: getBusinessDetailResult 
	* @Description: 获取商家详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBusinessDetailResult(BusinessDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_BUSINESS_DETAIL_URL, request.id, request.latitude,
				request.longitude);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getProductListResult 
	* @Description: 获取兑换列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getProductListResult(ExchangeListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_PRODUCT_LIST_URL, request.sortId, request.pager.pageIndex,
				request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getProductDetailResult 
	* @Description: 获取产品详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getProductDetailResult(ExchangeDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_PRODUCT_DETAIL_URL, request.username, request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getPreferentialListResult 
	* @Description: 获取优惠列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getPreferentialListResult(PreferentialListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_PREFERENTIAL_LIST_URL, request.userId, request.areaId,
				request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getPreferentialDetailResult 
	* @Description: 获取优惠详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getPreferentialDetailResult(PreferentialDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_PREFERENTIAL_DETAIL_URL, request.userName, request.password,
				request.userId, request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getTelAdListResult 
	* @Description: 获取拨打电话弹屏广告
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getTelAdListResult(TelAdListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_TEL_AD_LIST_URL, request.userName);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getExchangeRecordListResult 
	* @Description: 获取兑换记录列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getExchangeRecordListResult(ExchangeRecordListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_EXCHANGE_RECORD_LIST_URL, request.userName,
				request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getUserInfoResult 
	* @Description: 获取用户信息
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getUserInfoResult(UserInfoRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_USER_INFO_URL, request.userName, request.userId);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getMyPreferentialListResult 
	* @Description: 获取我发的优惠列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getMyPreferentialListResult(MyPreferentialListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_MY_PREFERENTIAL_LIST_URL, request.userId, request.pager.pageIndex,
				request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getRankingListResult 
	* @Description: 获取排行榜列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getRankingListResult(RankingListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_RANKING_LIST_URL);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getIncomeListResult 
	* @Description: 获取今日收入列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getIncomeListResult(IncomeListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_INCOME_URL, request.userId, request.userName,
				request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getFriendsListResult 
	* @Description: 获取朋友圈列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getFriendsListResult(FriendsListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_FRIENDS_LIST_URL, request.userId, request.userName,
				request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getWonderfulAdListResult 
	* @Description: 获取精彩广告数据列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWonderfulAdListResult(WonderfulAdListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_WONDERFUL_AD_LIST_URL, request.sortId, request.userId,
				request.userName, request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getWonderfulAdDetailResult 
	* @Description: 获取精彩广告详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWonderfulAdDetailResult(WonderfulAdDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_WONDERFUL_AD_DETAIL_URL, request.sortId, request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getValueSpikeListResult 
	* @Description: 获取秒杀数据列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValueSpikeListResult(ValueSpikeListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_VALUE_SPIKE_LIST_URL, request.periodTime, request.province,
				request.city, request.country, request.areaId, request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getValueSpikeDetailResult 
	* @Description: 获取秒杀详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValueSpikeDetailResult(ValueSpikeDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_VALUE_SPIKE_DETAIL_URL, request.userId, request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getUserLoginResult 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getUserLoginResult(UserLoginRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.GET_USER_LOGIN_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getValidateCodeResult 
	* @Description: 获取手机验证码
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValidateCodeResult(ValidateCodeRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_VALIDATE_CODE_URL, request.phoneNum, request.mobileImei);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getCheckCodeResult 
	* @Description: 检测验证码
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getCheckCodeResult(CheckCodeRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_CHECK_CODE_URL, request.mobile, request.mobileCode);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getRegisterResult 
	* @Description: 用户注册
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getRegisterResult(RegisterRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.GET_REGISTER_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getModifyPwResult 
	* @Description: 修改密码
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getModifyPwResult(ModifyPwRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_MODIFY_PW_URL, request.userName, request.oldPassword,
				request.newPassword);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getValidateCode 
	* @Description: 获取短信验证码
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValidateCode(GetValidateCodeRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_VALIDATE_CODE, request.mobileNum);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getResetPwResult 
	* @Description: 重置密码
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getResetPwResult(ResetPwRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_RESET_PW_URL, request.mobileNum, request.verifyCode,
				request.newPassword);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: PostCrashResult 
	* @Description: 提交错误信息
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void PostCrashResult(CrashRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_CRASH_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: postExchangeAddressResult 
	* @Description: 兑换收货地址
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postExchangeAddressResult(ExchangeAddressRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_EXCHANGE_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getWithdrawBalanceResult 
	* @Description: 获取可提现金额
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWithdrawBalanceResult(WithdrawBalanceRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_WITHDRAW_BALANCE_URL, request.userName, request.userPwd);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postWithdrawResult 
	* @Description: 提交取现金额数据
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postWithdrawResult(WithdrawRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_WITHDRAW_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getSeckillResult 
	* @Description: 首页-获取秒杀
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getSeckillResult(SeckillRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_SECKILL_URL, request.province, request.city, request.country,
				request.areaId);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postValueSpikeExchangeResult 
	* @Description: 提交超值抢购信息
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postValueSpikeExchangeResult(ValueSpikeExchangeRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_VALUE_SPIKE_EXCHANGE_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getSeckillRecordListResult 
	* @Description: 获取我的描述记录
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getSeckillRecordListResult(SeckillRecordRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.POST_SECKILL_RECODR_URL, request.userName, request.userId,
				request.pager.pageIndex, request.pager.pageSize);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postPublishPreferentialResult 
	* @Description: 提交发布优惠信息
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postPublishPreferentialResult(PublishPreferentialRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_PUBILSH_PREFERENTIAL_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: deletePreferentialResult 
	* @Description: 删除优惠信息
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deletePreferentialResult(DeletePreferentialRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.DELETE_MY_PREFERENTIAL_URL, request.userName, request.passWord,
				request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getNewVersionResult 
	* @Description: 检测新版本
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getNewVersionResult(UpdateRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_NEW_VERSION_URL, request.versionCode);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getBannerDetailResult 
	* @Description: 获取广告栏详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBannerDetailResult(BannerDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_BANNER_DETAIL_URL, request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postDailyAttendanceResult 
	* @Description: 每日签到
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postDailyAttendanceResult(DailyAttendanceRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.GET_DAILY_ATTENDANCE_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getUserDataResult 
	* @Description: 获取用户资料
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getUserDataResult(UserDataRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_USER_DATA_URL, request.userId, request.userPwd);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postUserDataResult 
	* @Description: 提交修改资料
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postUserDataResult(UserDataRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_USER_DATA_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getShareResult 
	* @Description: 分享
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getShareResult(ShareRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_SHARE_URL, request.userName, request.userId, request.userPwd);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getShareContentResult 
	* @Description: 获取分享内容
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getShareContentResult(ShareRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_SHARE_CONTENT_URL, request.userName);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postSeeAdResult 
	* @Description: 看广告收益
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postSeeAdResult(SeeAdRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_SEE_AD_URL, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getAreaIdResult 
	* @Description: 获取区域ID 
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getAreaIdResult(AreaIdRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_AREAID_URL, request.lng, request.lat);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getScreenLockListResult 
	* @Description: 获取锁屏广告列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getScreenLockListResult(ScreenLockRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_SCREENLOCK_LIST_URL, request.userName);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getBusinessProductDetailResult 
	* @Description: 获取产品详情
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBusinessProductDetailResult(ProductDetailRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_BUSINESS_PRODUCT_DETAIL_URL, request.id, request.longitude,
				request.latitude);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: getDefaultAddressResult 
	* @Description: 获取默认收货地址
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getDefaultAddressResult(AddressRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = null;
		if (request.id != 0) {
			requestUrl = String.format(WebUrl.GET_ADDRESS_URL, request.userId, request.userPwd, request.id);
		} else {
			requestUrl = String.format(WebUrl.GET_DEFAULT_ADDRESS_URL, request.userId, request.userPwd);
		}
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: addAddressResult 
	* @Description: 添加收货地址
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addAddressResult(AddAddressRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = null;
		if (request.isUpdate) {
			url = WebUrl.POST_UPDATE_ADDRESS_URL;
		} else {
			url = WebUrl.POST_ADD_ADDRESS_URL;
		}
		post(url, requestObejct, listener, errorListener);
	}

	/**
	 * 
	* @Title: getAddressListResult 
	* @Description: 获取收货地址列表
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getAddressListResult(AddressListRequest request, Listener<JSONObject> listener,
			ErrorListener errorListener, boolean shouldCache) {
		String requestUrl = String.format(WebUrl.GET_ADDRESS_LIST_URL, request.userId, request.userPwd);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: deleteAddressResult 
	* @Description: 删除收货地址
	* @param @param request
	* @param @param listener
	* @param @param errorListener
	* @param @param shouldCache    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteAddressResult(AddressRequest request, Listener<JSONObject> listener, ErrorListener errorListener,
			boolean shouldCache) {
		String requestUrl = String.format(WebUrl.DELETE_ADDRESS_URL, request.userId, request.userPwd, request.id);
		get(requestUrl, listener, errorListener, shouldCache);
	}

	/**
	 * 
	* @Title: postOrderResult 
	* @Description: 提交订单
	* @param @param request
	* @param @param listener
	* @param @param errorListener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postOrderResult(OrderRequest request, Listener<JSONObject> listener, ErrorListener errorListener) {
		JSONObject requestObejct = null;
		try {
			requestObejct = new JSONObject(JsonUtil.objectToJson(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		post(WebUrl.POST_ORDER_URL, requestObejct, listener, errorListener);
	}

}
