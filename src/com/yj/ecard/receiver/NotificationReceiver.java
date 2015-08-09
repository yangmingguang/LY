/**   
* @Title: NotificationReceiver.java
* @Package com.yj.ecard.receiver
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-4 上午8:30:26
* @version V1.0   
*/

package com.yj.ecard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yj.ecard.business.common.CommonManager;

/**
* @ClassName: NotificationReceiver
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-4 上午8:30:26
* 
*/

public class NotificationReceiver extends BroadcastReceiver {

	/** 点击整个通知栏  */
	public final static int BUTTON_ONTIFI_BAR_ID = 1010101;
	/** 按钮标识 */
	public final static String INTENT_BUTTONID_TAG = "ButtonId";
	/** 通知栏按钮点击事件对应的ACTION */
	public final static String ACTION_BUTTON = "com.yj.ecard.receiver.NotificationReceiver.onClick";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(ACTION_BUTTON)) {
			// 通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
			int buttonId = intent.getIntExtra(INTENT_BUTTONID_TAG, 0);

			switch (buttonId) {
			case BUTTON_ONTIFI_BAR_ID:
				CommonManager.getInstance().toInstallApk(context);
				break;
			}
		}
	}

}
