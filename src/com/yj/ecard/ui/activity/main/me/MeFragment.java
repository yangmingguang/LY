/**   
* @Title: MeFragment.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:45:04
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.UserInfoRequest;
import com.yj.ecard.publics.http.model.response.UserInfoResponse;
import com.yj.ecard.publics.http.model.response.WithdrawBalanceResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.activity.main.home.valuespike.SeckillRecordActivity;

/**
* @ClassName: MeFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:45:04
* 
*/

public class MeFragment extends BaseFragment implements OnClickListener {

	private ImageView ivLevel, ivHead;
	private View rootView, contentView, loadingView;
	private TextView tvUserName, tvAmount, tvIncome, tvFriendIncome, tvFriendNum;
	private final int[] btns = { R.id.btn_income, R.id.btn_complete_data, R.id.btn_my_preferential,
			R.id.btn_friend_num, R.id.btn_friend_income, R.id.btn_exchange_record, R.id.btn_ranking_list,
			R.id.btn_account_withdraw, R.id.btn_seckill_record, R.id.btn_withdraw_record };

	public static Fragment newInstance(Bundle bundle) {
		MeFragment fragment = new MeFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_me, null);
		initViews();
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadAllData();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		ivLevel = (ImageView) rootView.findViewById(R.id.iv_level);
		ivHead = (ImageView) rootView.findViewById(R.id.iv_user_picture);
		tvAmount = (TextView) rootView.findViewById(R.id.tv_amount);
		tvUserName = (TextView) rootView.findViewById(R.id.tv_userName);
		tvIncome = (TextView) rootView.findViewById(R.id.btn_income);
		tvFriendIncome = (TextView) rootView.findViewById(R.id.btn_friend_income);
		tvFriendNum = (TextView) rootView.findViewById(R.id.btn_friend_num);
		contentView = rootView.findViewById(R.id.ll_content);
		loadingView = rootView.findViewById(R.id.l_loading_rl);

		// listener events
		for (int btn : btns)
			rootView.findViewById(btn).setOnClickListener(this);
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		int userId = UserManager.getInstance().getUserId(context);
		String userName = UserManager.getInstance().getUserName(context);

		UserInfoRequest request = new UserInfoRequest();
		request.setUserId(userId);
		request.setUserName(userName);
		DataFetcher.getInstance().getUserInfoResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				UserInfoResponse userInfoResponse = (UserInfoResponse) JsonUtil.jsonToBean(response,
						UserInfoResponse.class);
				ivLevel.setBackgroundResource(Utils.getLevelDrawable(userInfoResponse.level));
				tvUserName.setText(userInfoResponse.userName);
				tvAmount.setText("￥" + userInfoResponse.amount);
				tvIncome.setText("今日收入:￥" + userInfoResponse.todayEarnings);
				tvFriendIncome.setText("朋友圈收入:￥" + userInfoResponse.frindsEarnings);
				tvFriendNum.setText("朋友圈人数:" + userInfoResponse.friendsNum + "个");

				UserManager.getInstance().setHeadUrl(context, userInfoResponse.avatar); // 保存头像路径
				ImageLoaderUtil.load(context, ImageType.NETWORK, userInfoResponse.avatar, R.drawable.ic_default_head,
						R.drawable.ic_default_head, ivHead);

				// 显示数据内容
				loadingView.setVisibility(View.GONE);
				contentView.setVisibility(View.VISIBLE);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Utils.dismissProgressDialog(); // 取消dialog
			WithdrawBalanceResponse response = (WithdrawBalanceResponse) msg.obj;
			switch (msg.what) {
			case MeTabManager.onWithdrawBalanceSuccess:
				ToastUtil.show(context, response.status.msg, ToastUtil.LENGTH_SHORT);
				UserManager.getInstance().setCanAmount(context, "可用余额:" + response.canCashAmount + "元");
				startActivity(new Intent(context, WithdrawActivity.class));
				break;

			case MeTabManager.onWithdrawBalanceEmpty:
				ToastUtil.show(context, response.status.msg, ToastUtil.LENGTH_SHORT);
				break;

			case MeTabManager.onWithdrawBalanceFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;
			}

			return true;
		}
	});

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 今日收入
		case R.id.btn_income:
			startActivity(new Intent(context, IncomeActivity.class));
			break;

		// 完善资料	
		case R.id.btn_complete_data:
			startActivity(new Intent(context, CompleteDataActivity.class));
			break;

		// 朋友圈人数
		case R.id.btn_friend_num:
			//startActivity(new Intent(context, CompleteDataActivity.class));
			break;

		// 朋友圈收入
		case R.id.btn_friend_income:
			startActivity(new Intent(context, FriendsListActivity.class));
			break;

		// 我发的优惠	
		case R.id.btn_my_preferential:
			startActivity(new Intent(context, MyPreferentialActivity.class));
			break;

		// 兑换记录	
		case R.id.btn_exchange_record:
			startActivity(new Intent(context, ExchangeRecordActivity.class));
			break;

		// 秒杀记录	
		case R.id.btn_seckill_record:
			startActivity(new Intent(context, SeckillRecordActivity.class));
			break;

		// 排行榜
		case R.id.btn_ranking_list:
			startActivity(new Intent(context, RankingListActivity.class));
			break;

		// 账户提现	
		case R.id.btn_account_withdraw:
			Utils.showProgressDialog(context);// 显示dialog
			MeTabManager.getInstance().getWithdrawBalanceData(context, handler);
			break;

		// 提现记录	
		case R.id.btn_withdraw_record:
			startActivity(new Intent(context, WithdrawRecordActivity.class));
			break;
		}
	}
}
