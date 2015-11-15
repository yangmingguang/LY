/**   
* @Title: WelcomeActivity.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-31 上午9:43:46
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.SharedPrefsUtil;
import com.yj.ecard.publics.utils.StorageUtils;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.user.LoginActivity;

/**
* @ClassName: WelcomeActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 上午9:43:46
* 
*/

public class WelcomeActivity extends Activity {

	private ImageView imageView;
	private Context context = this;
	private static final String welcomeUrl = StorageUtils.IMAGE_PATH + "app_welcome.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_welcome);
		initParams();
		// 百度云推送
		// PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "B5yXQAdhC0hIz1rlGZ7nPeqO");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 回收图片，释放资源
		Utils.recycleBackgroundResource(imageView);
	}

	/** 
	* @Title: initParams 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initParams() {
		// 设置Welcome图片
		imageView = (ImageView) findViewById(R.id.img_welcome);
		// 显示欢迎页
		showWelcome();

		// 存储屏幕宽度，方便后续使用
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		SharedPrefsUtil.putValue(this, Constan.SCREEN_WIDTH, width);

		DBService.getInstance(context).updateCall(0); // 初始化--未在通话中
		CommonManager.getInstance().initLocation(context);// 开启定位服务

		// 进入下一个界面
		showNextPage();
	}

	/** 
	* @Title: showWelcome 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void showWelcome() {
		File imageFile = new File(welcomeUrl);
		if (imageFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(welcomeUrl);
			imageView.setImageBitmap(bitmap);
		} else {
			Utils.setBackgroundResource(this, R.drawable.ic_welcome, imageView);
		}

		//初始化  
		Animation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
		alphaAnimation.setDuration(800); //设置动画时间
		imageView.startAnimation(alphaAnimation);
	}

	/** 
	* @Title: showNextPage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void showNextPage() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				boolean isAutoLogin = UserManager.getInstance().isLogin(context);
				if (isAutoLogin) {
					startActivity(new Intent(context, MainActivity.class));
				} else {
					startActivity(new Intent(context, LoginActivity.class));
				}
				finish();

			}
		}, 2000);
	}
}
