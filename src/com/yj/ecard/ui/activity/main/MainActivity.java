/**   
* @Title: MainActivity.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:19:16
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main;

import net.youmi.android.AdManager;
import net.youmi.android.offers.EarnPointsOrderList;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsChangeNotify;
import net.youmi.android.offers.PointsEarnNotify;
import net.youmi.android.offers.PointsManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.screenlock.ScreenLockManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.service.PhoneService;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.slidingmenu.AboutActivity;
import com.yj.ecard.ui.activity.user.ModifyPassWordActivity;

/**
* @ClassName: MainActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:19:16
* 
*/

public class MainActivity extends BaseActivity implements OnClickListener, PointsChangeNotify, PointsEarnNotify {

	private ImageView ivHead, ivSwitch;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private final int[] btns = { R.id.iv_user_head, R.id.btn_password, R.id.btn_custom, R.id.btn_cache,
			R.id.btn_switch, R.id.btn_version, R.id.btn_about, R.id.btn_exit };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		Utils.addFinishAct(this);
		initViews();
		loadAllData();
		initSDK();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (null != ivHead) {
			String path = UserManager.getInstance().getHeadUrl(context);
			ImageLoaderUtil.load(context, ImageType.NETWORK, path, R.drawable.ic_default_head,
					R.drawable.ic_default_head, ivHead);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/** When using the ActionBarDrawerToggle, you must call it during 
	onPostCreate() and onConfigurationChanged()*/
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerOpened(View drawerView) {

			}

			@Override
			public void onDrawerClosed(View drawerView) {

			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction fm = manager.beginTransaction();
		Fragment fragment = new ContentFragment();
		fm.replace(R.id.content_frame, fragment);
		fm.commit();

		ivSwitch = (ImageView) findViewById(R.id.iv_switch);
		ivHead = (ImageView) findViewById(R.id.iv_user_head);
		String userName = "账号:" + UserManager.getInstance().getUserName(context);
		int resId = Utils.getLevelDrawable(UserManager.getInstance().getLevel(context));
		((TextView) findViewById(R.id.tv_username)).setText(userName);
		((ImageView) findViewById(R.id.iv_level)).setBackgroundResource(resId);

		// listener button events
		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);

		setSwitchState();
	}

	/**
	 * 
	* @Title: setSwitchState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void setSwitchState() {
		boolean state = CommonManager.getInstance().getSwitchState(context);
		if (state) {
			ivSwitch.setBackgroundResource(R.drawable.setting_open);
		} else {
			ivSwitch.setBackgroundResource(R.drawable.setting_close);
		}
	}

	/** 
	* @Title: initParams 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		startDownload(); // 开始下载广告列表
		ScreenLockManager.getInstance().getScreenLockListData(context); // 下载锁屏广告
		CommonManager.getInstance().initLocation(context);// 开启定位服务
		CommonManager.getInstance().getShareContentData(context); // 获取分享内容
		CommonManager.getInstance().checkNewVersion(this, true);// 新版本检测
		boolean state = CommonManager.getInstance().getSwitchState(context);
		if (state) {
			startScreenLock(); // 开启锁屏后台服务
		}
	}

	/** 
	* @Title: startDownload 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void startDownload() {
		Intent intent = new Intent(context, PhoneService.class);
		intent.putExtra(Constan.COMMAND_FLAG, Constan.START);
		context.startService(intent);
	}

	/**
	 * 
	* @Title: startScreenLock 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void startScreenLock() {
		Intent intent = new Intent("com.yj.ecard.service.IScreenLockService");
		startService(intent);
	}

	/**
	 * 
	* @Title: stopScreenLock 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void stopScreenLock() {
		Intent intent = new Intent("com.yj.ecard.service.IScreenLockService");
		stopService(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_user_head:
			//ToastUtil.show(context, "Click Me!", ToastUtil.LENGTH_SHORT);
			break;

		case R.id.btn_password:
			startActivity(new Intent(context, ModifyPassWordActivity.class));
			break;

		case R.id.btn_custom:
			Utils.telCall(context, "4006588549");
			break;

		case R.id.btn_cache:
			Utils.clearAppCache(context);
			ToastUtil.show(context, R.string.clear_cache_success, ToastUtil.LENGTH_SHORT);
			break;

		case R.id.btn_switch:
			int resId = R.string.switch_close;
			boolean state = CommonManager.getInstance().getSwitchState(context);
			if (state) {
				resId = R.string.switch_close;
				CommonManager.getInstance().setSwitchState(context, false);
				stopScreenLock();
			} else {
				resId = R.string.switch_open;
				CommonManager.getInstance().setSwitchState(context, true);
				startScreenLock();
			}
			setSwitchState();
			ToastUtil.show(context, resId, ToastUtil.LENGTH_SHORT);
			break;

		case R.id.btn_version:
			CommonManager.getInstance().checkNewVersion(this, false);
			break;

		case R.id.btn_about:
			startActivity(new Intent(context, AboutActivity.class));
			break;

		case R.id.btn_exit:
			UserManager.getInstance().clearUserInfo(context);
			finish();
			break;
		}
	}

	/**********************************************集成第三方SDK********************************************************/
	@Override
	protected void onDestroy() {
		super.onDestroy();

		// （可选）注销积分监听
		// 如果在onCreate调用了PointsManager.getInstance(this).registerNotify(this)进行积分余额监听器注册，那这里必须得注销
		PointsManager.getInstance(this).unRegisterNotify(this);

		// （可选）注销积分订单赚取监听
		// 如果在onCreate调用了PointsManager.getInstance(this).registerPointsEarnNotify(this)进行积分订单赚取监听器注册，那这里必须得注销
		PointsManager.getInstance(this).unRegisterPointsEarnNotify(this);

		// 回收积分广告占用的资源
		OffersManager.getInstance(this).onAppExit();
	}

	/** 
	* @Title: initSDK 
	* @Description: 初始化第三方SDK
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initSDK() {
		// 有米android 积分墙sdk 5.0.0之后支持定制浏览器顶部标题栏的部分UI
		// deployOfferBrowserConfig();

		// (可选)关闭有米log输出，建议开发者在嵌入有米过程中不要关闭，以方便随时捕捉输出信息，出问题时可以快速定位问题
		// AdManager.getInstance(Context context).setEnableDebugLog(false);

		// 初始化接口，应用启动的时候调用，参数：appId = da948623b6157101, appSecret =  0325d5eef49593e6
		//AdManager.getInstance(this).init("cfdbdd2786ea88ea", "d8edde7d10dd0073");
		AdManager.getInstance(this).init("da948623b6157101", "0325d5eef49593e6");

		// (可选)开启用户数据统计服务,(sdk v4.08之后新增功能)默认不开启，传入false值也不开启，只有传入true才会调用
		AdManager.getInstance(this).setUserDataCollect(true);

		// 如果开发者使用积分墙的服务器回调
		// 1.需要告诉sdk，现在采用服务器回调
		// 2.建议开发者传入自己系统中用户id（如：邮箱账号之类的）（请限制在50个字符串以内）
		// OffersManager.setUsingServerCallBack(true);
		// OffersManager.getInstance(this).setCustomUserId("user_id");

		// 如果使用积分广告，请务必调用积分广告的初始化接口:
		OffersManager.getInstance(this).onAppLaunch();

		// （可选）注册积分监听-随时随地获得积分的变动情况
		PointsManager.getInstance(this).registerNotify(this);

		// （可选）注册积分订单赚取监听（sdk v4.10版本新增功能）
		PointsManager.getInstance(this).registerPointsEarnNotify(this);

		// (可选)设置是否在通知栏显示下载相关提示。默认为true，标识开启；设置为false则关闭。（sdk v4.10版本新增功能）
		// AdManager.setIsDownloadTipsDisplayOnNotification(false);

		// (可选)设置安装完成后是否在通知栏显示已安装成功的通知。默认为true，标识开启；设置为false则关闭。（sdk v4.10版本新增功能）
		// AdManager.setIsInstallationSuccessTipsDisplayOnNotification(false);

		// (可选)设置是否在通知栏显示积分赚取提示。默认为true，标识开启；设置为false则关闭。
		// PointsManager.setEnableEarnPointsNotification(false);

		// (可选)设置是否开启积分赚取的Toast提示。默认为true，标识开启；设置为false这关闭。
		// PointsManager.setEnableEarnPointsToastTips(false);
	}

	/* (非 Javadoc) 
	* <p>Title: onPointBalanceChange</p> 
	* <p>Description: </p> 
	* @param arg0 
	* @see net.youmi.android.offers.PointsChangeNotify#onPointBalanceChange(int) 
	*/
	@Override
	public void onPointBalanceChange(int arg0) {
		// TODO Auto-generated method stub

	}

	/* (非 Javadoc) 
	* <p>Title: onPointEarn</p> 
	* <p>Description: </p> 
	* @param arg0
	* @param arg1 
	* @see net.youmi.android.offers.PointsEarnNotify#onPointEarn(android.content.Context, net.youmi.android.offers.EarnPointsOrderList) 
	*/
	@Override
	public void onPointEarn(Context arg0, EarnPointsOrderList arg1) {
		// TODO Auto-generated method stub

	}

}
