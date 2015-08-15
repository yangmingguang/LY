/**   
* @Title: ScreenLockService.java
* @Package com.yj.ecard.service
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-28 下午3:52:03
* @version V1.0   
*/

package com.yj.ecard.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.receiver.ScreenLockReceiver;

/**
* @ClassName: ScreenLockService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-28 下午3:52:03
* 
*/

public class ScreenLockService extends Service {

	private static final String TAG = "ScreenLockService";

	private Intent startIntent = null;
	private ScreenLockReceiver mReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);

		mReceiver = new ScreenLockReceiver();

		registerReceiver(mReceiver, filter);

		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("KeyguardLock");
		keyguardLock.disableKeyguard();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startIntent = intent;

		Log.e(TAG, "OnStartCommand -> action: " + intent.getAction() + "\n startId: " + startId);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "OnDestroy");

		unregisterReceiver(mReceiver);

		// 是否开启划屏
		boolean drawState = CommonManager.getInstance().getSwitchState(getApplicationContext());

		if (startIntent != null && drawState) {
			Log.e(TAG, "OnDestroy -> startIntent is not null");
			startService(startIntent);
		}

		super.onDestroy();
	}

}
