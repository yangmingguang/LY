/**   
* @Title: HomeFragment.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:45:25
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home;

import net.youmi.android.offers.OffersManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.main.HomeTabManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.activity.main.home.preferential.PublishPreferentialActivity;
import com.yj.ecard.ui.activity.main.home.preferential.StrollPreferentialActivity;
import com.yj.ecard.ui.activity.main.home.valuespike.ValueSpikeActivity;
import com.yj.ecard.ui.activity.main.home.wonderful.WonderfulAdActivity;
import com.yj.ecard.ui.views.viewflow.BannerViewFlow;

/**
* @ClassName: HomeFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:45:25
* 
*/

public class HomeFragment extends BaseFragment implements OnClickListener {

	private ImageView ivLogo;
	private BannerViewFlow bannerViewFlow;
	private TextView tvBalance, tvPrice, tvTitle;
	private View rootView, homeTabView, loadingView;
	private final int[] btns = { R.id.btn_ad, R.id.btn_business, R.id.btn_exchange, R.id.btn_invite,
			R.id.btn_every_reg, R.id.btn_every_task, R.id.btn_value_spike, R.id.btn_stroll_preferential,
			R.id.btn_publish_preferential };

	public static Fragment newInstance(Bundle bundle) {
		HomeFragment fragment = new HomeFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_home, null);
		initViews();
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		// 余额
		HomeTabManager.getInstance().getBalanceData(context, tvBalance);
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		homeTabView = rootView.findViewById(R.id.ll_home_tab);
		loadingView = rootView.findViewById(R.id.l_loading_rl);
		homeTabView.setVisibility(View.GONE);
		loadingView.setVisibility(View.VISIBLE);
		ivLogo = (ImageView) rootView.findViewById(R.id.iv_logo);
		tvPrice = (TextView) rootView.findViewById(R.id.tv_price);
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		// 设置金额
		String amount = UserManager.getInstance().getAmount(context);
		tvBalance = (TextView) rootView.findViewById(R.id.tv_amount);
		tvBalance.setText(amount + "元");

		// listener button events
		for (int btn : btns)
			rootView.findViewById(btn).setOnClickListener(this);

		bannerViewFlow = new BannerViewFlow(context, rootView, R.id.fb_viewflow, R.id.fb_viewflowindic, null);

		loadAllData();//加载所有数据
	}

	/** 
	* @Title: loadAllData 
	* @Description: 加载所有数据
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		// 余额
		// HomeTabManager.getInstance().getBalanceData(context, tvBalance);
		// 广告
		HomeTabManager.getInstance().getBannerData(context, handler, bannerViewFlow);
		// 超值秒杀
		HomeTabManager.getInstance().getSeckillData(context, tvPrice, tvTitle, ivLogo);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 精彩广告
		case R.id.btn_ad:
			startActivity(new Intent(context, WonderfulAdActivity.class));
			break;

		// 特色商家	
		case R.id.btn_business:
			startActivity(new Intent(context, FeatureBusinessActivity.class));
			break;

		// 兑换礼品	
		case R.id.btn_exchange:
			startActivity(new Intent(context, ExchangeGiftActivity.class));
			break;

		// 邀请好友
		case R.id.btn_invite:
			Utils.toSocialShare(context);
			break;

		// 每日签到	
		case R.id.btn_every_reg:
			HomeTabManager.getInstance().postDailyAttendanceData(context);
			break;

		// 每日任务	
		case R.id.btn_every_task:
			String userName = UserManager.getInstance().getUserName(context);
			OffersManager.getInstance(context).setCustomUserId(userName);
			OffersManager.setUsingServerCallBack(true);
			OffersManager.getInstance(context).showOffersWall();
			break;

		// 超值秒杀	
		case R.id.btn_value_spike:
			startActivity(new Intent(context, ValueSpikeActivity.class));
			break;

		// 逛优惠	
		case R.id.btn_stroll_preferential:
			startActivity(new Intent(context, StrollPreferentialActivity.class));
			break;

		// 发优惠	
		case R.id.btn_publish_preferential:
			startActivity(new Intent(context, PublishPreferentialActivity.class));
			break;

		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HomeTabManager.onSuccess:
				loadingView.setVisibility(View.GONE);
				homeTabView.setVisibility(View.VISIBLE);
				break;

			case HomeTabManager.onFailure:
				loadingView.setVisibility(View.VISIBLE);
				homeTabView.setVisibility(View.GONE);
				break;
			}
		}
	};
}
