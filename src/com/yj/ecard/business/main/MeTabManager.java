/**   
* @Title: MeTabManager.java
* @Package com.yj.ecard.business.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午3:31:42
* @version V1.0   
*/

package com.yj.ecard.business.main;

import org.json.JSONObject;

import android.content.Context;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.DeletePreferentialRequest;
import com.yj.ecard.publics.http.model.request.FriendsListRequest;
import com.yj.ecard.publics.http.model.request.IncomeListRequest;
import com.yj.ecard.publics.http.model.request.MyPreferentialListRequest;
import com.yj.ecard.publics.http.model.request.Pager;
import com.yj.ecard.publics.http.model.request.RankingListRequest;
import com.yj.ecard.publics.http.model.request.WithdrawBalanceRequest;
import com.yj.ecard.publics.http.model.response.DeletePreferentialResponse;
import com.yj.ecard.publics.http.model.response.FriendsListResponse;
import com.yj.ecard.publics.http.model.response.IncomeListResponse;
import com.yj.ecard.publics.http.model.response.MyPreferentialListResponse;
import com.yj.ecard.publics.http.model.response.RankingListResponse;
import com.yj.ecard.publics.http.model.response.WithdrawBalanceResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.MyPreferentialBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.adapter.MyPreferentialListAdapter;

/**
* @ClassName: MeTabManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午3:31:42
* 
*/

public class MeTabManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	public static final int onWithdrawBalanceSuccess = 201;
	public static final int onWithdrawBalanceEmpty = 301;
	public static final int onWithdrawBalanceFailure = 501;
	private static volatile MeTabManager mMeTabManager;

	/******************************单例开始********************************/

	private MeTabManager() {
		// TODO Auto-generated constructor stub
	}

	public static MeTabManager getInstance() {
		if (mMeTabManager == null) {
			synchronized (MeTabManager.class) {
				if (mMeTabManager == null) {
					mMeTabManager = new MeTabManager();
				}
			}
		}
		return mMeTabManager;
	}

	/******************************单例结束********************************/

	/**
	 * 
	* @Title: getMyPreferentialListData 
	* @Description: 获取我发的优惠列表数据
	* @param @param context
	* @param @param handler
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getMyPreferentialListData(final Context context, final WeakHandler handler, int pageIndex) {
		MyPreferentialListRequest request = new MyPreferentialListRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);

		DataFetcher.getInstance().getMyPreferentialListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				MyPreferentialListResponse myPreferentialListResponse = (MyPreferentialListResponse) JsonUtil
						.jsonToBean(response, MyPreferentialListResponse.class);
				int stateCode = myPreferentialListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, myPreferentialListResponse));
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
	* @Title: deletePreferentialData 
	* @Description: 删除我的优惠信息
	* @param @param context
	* @param @param handler
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deletePreferentialData(final Context context, final MyPreferentialListAdapter mAdapter,
			final MyPreferentialBean myPreferentialBean) {
		Utils.showProgressDialog(context); // 显示dialog
		DeletePreferentialRequest request = new DeletePreferentialRequest();
		request.setId(myPreferentialBean.id);
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setPassWord(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().deletePreferentialResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				DeletePreferentialResponse mDeletePreferentialResponse = (DeletePreferentialResponse) JsonUtil
						.jsonToBean(response, DeletePreferentialResponse.class);
				int stateCode = mDeletePreferentialResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					mAdapter.removeItem(myPreferentialBean);
					ToastUtil.show(context, mDeletePreferentialResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;

				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mDeletePreferentialResponse.status.msg, ToastUtil.LENGTH_LONG);
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

	/**
	 * 
	* @Title: getRankingListData 
	* @Description: 获取排行榜列表数据
	* @param @param context
	* @param @param handler    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getRankingListData(final Context context, final WeakHandler handler) {
		RankingListRequest request = new RankingListRequest();
		DataFetcher.getInstance().getRankingListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				RankingListResponse rankingListResponse = (RankingListResponse) JsonUtil.jsonToBean(response,
						RankingListResponse.class);
				int stateCode = rankingListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, rankingListResponse));
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
	* @Title: getIncomeListData 
	* @Description: 获取今日收入列表数据
	* @param @param context
	* @param @param handler    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getIncomeListData(final Context context, final WeakHandler handler, int pageIndex) {
		IncomeListRequest request = new IncomeListRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getIncomeListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				IncomeListResponse incomeListResponse = (IncomeListResponse) JsonUtil.jsonToBean(response,
						IncomeListResponse.class);
				int stateCode = incomeListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, incomeListResponse));
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
	* @Title: getFriendsListData 
	* @Description: 获取朋友圈列表 
	* @param @param context
	* @param @param handler
	* @param @param pageIndex    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getFriendsListData(final Context context, final WeakHandler handler, int pageIndex) {
		FriendsListRequest request = new FriendsListRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		Pager pager = new Pager(pageIndex);
		request.setPager(pager);
		DataFetcher.getInstance().getFriendsListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				FriendsListResponse friendsListResponse = (FriendsListResponse) JsonUtil.jsonToBean(response,
						FriendsListResponse.class);
				int stateCode = friendsListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onSuccess, friendsListResponse));
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
	* @Title: getWithdrawBalanceData 
	* @Description: 获取可提现金额
	* @param @param context
	* @param @param handler    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWithdrawBalanceData(final Context context, final WeakHandler handler) {
		WithdrawBalanceRequest request = new WithdrawBalanceRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getWithdrawBalanceResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				WithdrawBalanceResponse mWithdrawBalanceResponse = (WithdrawBalanceResponse) JsonUtil.jsonToBean(
						response, WithdrawBalanceResponse.class);
				int stateCode = mWithdrawBalanceResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onWithdrawBalanceSuccess, mWithdrawBalanceResponse));
					break;

				case Constan.EMPTY_CODE:
					handler.sendMessage(handler.obtainMessage(onWithdrawBalanceEmpty, mWithdrawBalanceResponse));
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onWithdrawBalanceFailure);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onWithdrawBalanceFailure);
			}
		}, true);
	}
}
