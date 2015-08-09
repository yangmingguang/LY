/**   
 * @Title: PhoneService.java
 * @Package com.yj.ecard.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author YangMingGuang
 * @date 2015-6-1 下午10:29:30
 * @version V1.0   
 */

package com.yj.ecard.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.yj.ecard.business.phone.FloatWindowManager;
import com.yj.ecard.business.phone.PhoneManager;
import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.Utils;

/**
 * @ClassName: PhoneService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author YangMingGuang
 * @date 2015-6-1 下午10:29:30 1.动态注册 比 静态注册优先级高，设置最大值 2147483647 防止第三方软件360等禁用广播 2.去电可以注册广播，来电则需要实现监听
 */

public class PhoneService extends Service {

	private Context context;
	private TelephonyManager tm;
	private CallPhoneStateReceiver mCallPhoneStateReceiver;// 去电广播
	private RingPhoneStateListener mRingPhoneStateListener;// 来电监听
	private boolean isCallingFlag, incomingFlag, isCallFinish = false;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		// 开始闹钟
		startAlarm();
		// 注册去电广播
		regCallPhoneStateReceiver();
		// 注册来电监听
		regRingPhoneStateReceiver();
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
		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		// 包装需要执行Service的Intent
		Intent intent = new Intent(this, this.getClass());
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 触发服务的起始时间
		long triggerAtTime = SystemClock.elapsedRealtime();
		// 使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, 8 * 1000, pendingIntent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unRegCallPhoneStateReceiver();
		unRegRingPhoneStateReceiver();

		// 销毁时重新启动Service
		Intent localIntent = new Intent();
		localIntent.setClass(this, PhoneService.class);
		this.startService(localIntent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		LogUtil.getLogger().d("======onStartCommand======");

		if (null != intent) {
			int state = intent.getExtras().getInt(Constan.COMMAND_FLAG);
			switch (state) {
			// 开始下载广告
			case Constan.START:
				LogUtil.getLogger().d("===开始下载广告===");
				PhoneManager.getInstance().getTelAdListData(context);
				break;

			// 清空已下载的广告
			case Constan.CLEAR:

				break;
			}
		}

		return START_STICKY;
	}

	/**
	 * 
	 * @Title: regPhoneStateReceiver
	 * @Description: 注册-去电广播接收
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void regCallPhoneStateReceiver() {
		if (null == mCallPhoneStateReceiver) {
			mCallPhoneStateReceiver = new CallPhoneStateReceiver();
			// startForeground(0x1982, new Notification());
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
			intentFilter.addAction("android.intent.action.PHONE_STATE");
			// intentFilter.addAction("android.intent.action.SCREEN_ON");
			// intentFilter.addAction("android.intent.action.SCREEN_OFF");
			// intentFilter.addAction("android.intent.action.USER_PRESENT");
			intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
			intentFilter.addAction("android.intent.action.ALARM_CHANGED");
			intentFilter.setPriority(Integer.MAX_VALUE);
			registerReceiver(mCallPhoneStateReceiver, intentFilter);

		}
	}

	/**
	 * @Title: regRingPhoneStateReceiver
	 * @Description: 注册来电监听
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void regRingPhoneStateReceiver() {
		if (null == mRingPhoneStateListener) {
			mRingPhoneStateListener = new RingPhoneStateListener();
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mRingPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		}
	}

	/**
	 * 
	 * @Title: unRegPhoneStateReceiver
	 * @Description: 注销去电广播
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void unRegCallPhoneStateReceiver() {
		if (null != mCallPhoneStateReceiver) {
			unregisterReceiver(mCallPhoneStateReceiver);
			mCallPhoneStateReceiver = null;
		}
	}

	/**
	 * 
	 * @Title: unRegRingPhoneStateReceiver
	 * @Description: 注销来电监听
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void unRegRingPhoneStateReceiver() {
		if (null != mRingPhoneStateListener) {
			mRingPhoneStateListener = null;
		}
	}

	/**
	 * 
	 * @ClassName: MyPhoneStateListener
	 * @Description: 来电监听
	 * @author YangMingGuang
	 * @date 2015-6-3 上午11:36:37
	 * 
	 */
	class RingPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			// 响铃
			case TelephonyManager.CALL_STATE_RINGING:
				isCallFinish = false;
				Utils.clearActivity();// 清除锁屏activity
				incomingFlag = true; // 标记来电
				LogUtil.getLogger().d("=======RINGING : " + incomingNumber);
				FloatWindowManager.getInstance().showFloatView(context, incomingNumber, false, false, true);// 显示半屏悬浮窗
				break;

			// 接听
			case TelephonyManager.CALL_STATE_OFFHOOK:
				isCallFinish = false;
				if (incomingFlag) {
					LogUtil.getLogger().d("=======incoming ACCEPT : " + incomingNumber);
				}
				break;

			// 挂电话
			case TelephonyManager.CALL_STATE_IDLE:
				if (incomingFlag || isCallingFlag) {
					isCallFinish = true;
					LogUtil.getLogger().d("=======incoming IDLE : " + incomingNumber);
					FloatWindowManager.getInstance().showFloatView(context, incomingNumber, true, isCallingFlag, true); // 显示全屏悬浮窗
					isCallingFlag = false; // 标记打电话结束
					incomingFlag = false; // 标记通话结束
					// 初始化--未在通话中，结束通话
					DBService.getInstance(context).updateCall(0);
				}
				break;
			}
		}
	}

	/**
	 * 
	 * @ClassName: CallPhoneStateReceiver
	 * @Description: 去电广播接收
	 * @author YangMingGuang
	 * @date 2015-6-3 上午11:58:32
	 * 
	 */
	class CallPhoneStateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			// 1.去电
			if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
				isCallingFlag = true;
				String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
				if (null == phoneNumber) {
					phoneNumber = "";
				}
				LogUtil.getLogger().d("Call Out : " + phoneNumber.toString());
				// 显示半屏悬浮窗
				FloatWindowManager.getInstance().showFloatView(context, phoneNumber, false, true, true);

			} else if (intent.getAction().equals("android.intent.action.PHONE_STATE") && !incomingFlag && !isCallFinish) {

				if ((android.os.Build.MANUFACTURER).toLowerCase().contains("meizu")) {
					isCallingFlag = true;
					String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
					if (null == phoneNumber) {
						phoneNumber = "";
					}
					LogUtil.getLogger().d("Call Out : " + phoneNumber.toString());
					FloatWindowManager.getInstance().showFloatView(context, phoneNumber, false, true, false);
				}

			}
		}
	}

}
