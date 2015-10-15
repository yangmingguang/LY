/**   
* @Title: ScreenLockReceiver.java
* @Package com.yj.ecard.receiver
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-28 下午3:52:39
* @version V1.0   
*/

package com.yj.ecard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yj.ecard.db.DBService;
import com.yj.ecard.ui.activity.screenlock.ScreenLockActivity;

/**
* @ClassName: ScreenLockReceiver
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-28 下午3:52:39
* 
*/

public class ScreenLockReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		String intentAction = intent.getAction();

		if (intentAction.equals(Intent.ACTION_SCREEN_ON)) {
			/*	if (!ScreenLockActivity.isShow) {
					Intent tempIntent = new Intent(context, ScreenLockActivity.class);
					tempIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(tempIntent);
				}*/
		} else if (intentAction.equals(Intent.ACTION_SCREEN_OFF)) {
			// 是否开启划屏
			int drawState = DBService.getInstance(context).getScreenLockState();
			// 获取当前通话状态
			int state = DBService.getInstance(context).getCallState();
			if (drawState == 0) {
				if (state == 0) {
					if (!ScreenLockActivity.isShow) {
						Intent tempIntent = new Intent(context, ScreenLockActivity.class);
						tempIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(tempIntent);
					}
				}
			}
		}
	}
}
