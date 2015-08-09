/**   
* @Title: CustomNotificationManager.java
* @Package com.yj.ecard.business.notification
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-2 下午10:30:08
* @version V1.0   
*/

package com.yj.ecard.business.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.yj.ecard.R;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.receiver.NotificationReceiver;

/**
* @ClassName: CustomNotificationManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-2 下午10:30:08
* 
*/

public class CustomNotificationManager {

	private NotificationCompat.Builder mBuilder;
	private NotificationManager mNotificationManager;
	private static volatile CustomNotificationManager mCustomNotificationManager;

	/******************************单例开始********************************/

	private CustomNotificationManager() {
		// TODO Auto-generated constructor stub
	}

	public static CustomNotificationManager getInstance() {
		if (mCustomNotificationManager == null) {
			synchronized (CustomNotificationManager.class) {
				if (mCustomNotificationManager == null) {
					mCustomNotificationManager = new CustomNotificationManager();
				}
			}
		}
		return mCustomNotificationManager;
	}

	/******************************单例结束********************************/

	/**
	 * 
	* @Title: initNotification 
	* @Description: 初始化通知栏 
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void initNotification(Context context) {
		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(context);
		mBuilder.setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
				.setContentIntent(getDefalutIntent(context, 0)).setPriority(NotificationCompat.PRIORITY_MAX)// 设置该通知优先级
				.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
				.setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
				//.setDefaults(Notification.DEFAULT_VIBRATE)// 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
				.setSmallIcon(R.drawable.app_icon);

	}

	public PendingIntent getDefalutIntent(Context context, int flags) {
		// 点击的事件处理
		Intent buttonIntent = new Intent(NotificationReceiver.ACTION_BUTTON);
		buttonIntent.putExtra(NotificationReceiver.INTENT_BUTTONID_TAG, NotificationReceiver.BUTTON_ONTIFI_BAR_ID);
		// 这里加了广播，所及INTENT的必须用getBroadcast方法
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, buttonIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		return pendingIntent;
	}

	/**
	 * 
	* @Title: updateProgress 
	* @Description: 更新进度 
	* @param @param max
	* @param @param progress    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateProgress(int max, int progress) {
		String contentTitle = "";
		if (max == progress) {
			mBuilder.setProgress(0, 0, false);
			contentTitle = "下载完成，点击安装！";
		} else {
			contentTitle = "下载中.";
			mBuilder.setProgress(max, progress, false);
		}
		mBuilder.setContentTitle(contentTitle).setContentText(Utils.sizeFormat(progress) + "/" + Utils.sizeFormat(max));
		mNotificationManager.notify(100, mBuilder.build());
	}
}
