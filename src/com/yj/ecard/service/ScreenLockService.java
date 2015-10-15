/**   
* @Title: ScreenLockService.java
* @Package com.yj.ecard.service
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-28 下午3:52:03
* @version V1.0   
*/

package com.yj.ecard.service;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.utils.LogUtil;
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
	private AlarmManager manager;
	private PendingIntent pendingIntent;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 开始闹钟
		startAlarm();

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);

		mReceiver = new ScreenLockReceiver();

		registerReceiver(mReceiver, filter);

		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("KeyguardLock");
		keyguardLock.disableKeyguard();

	}

	/**
	 * 
	 * @Title: startAlarm
	 * @Description: 使用定时
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void startAlarm() {
		manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		// 包装需要执行Service的Intent
		Intent intent = new Intent(this, this.getClass());
		pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 触发服务的起始时间
		long triggerAtTime = SystemClock.elapsedRealtime();
		// 使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, 8 * 1000, pendingIntent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.getLogger().d("======ScreenLockService onStartCommand======");

		startIntent = intent;

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "OnDestroy");

		unregisterReceiver(mReceiver);

		// 是否开启划屏
		int drawState = DBService.getInstance(getApplicationContext()).getScreenLockState();

		if (startIntent != null && drawState == 0) {
			Log.e(TAG, "OnDestroy -> startIntent is not null");
			startService(startIntent);
		}

		super.onDestroy();
	}

}
