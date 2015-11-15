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
import com.yj.ecard.ui.activity.main.home.valuespike.SeckillRecordActivity;
import com.yj.ecard.ui.activity.main.me.ExchangeRecordActivity;
import com.yj.ecard.ui.activity.main.slidingmenu.MessageCenterActivity;

/**
* @ClassName: NotificationReceiver
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-4 上午8:30:26
* 
*/

public class NotificationReceiver extends BroadcastReceiver {

	public static final int USER_MESSAGE_ID = 0;
	public static final int SYS_MESSAGE_ID = 1;
	public static final int UPDATE_MESSAGE_ID = 1000;
	public static final int PUSH_MESSAGE_ID1 = 1104;
	public static final int PUSH_MESSAGE_ID2 = 1105;

	/** 点击整个通知栏  */
	public static final int BUTTON_ONTIFI_BAR_ID = 1010101;

	/** 按钮标识 */
	public static final String INTENT_BUTTONID_TAG = "ButtonId";
	public static final String INTENT_MESSAGEID_TAG = "MessageId";

	/** 通知栏按钮点击事件对应的ACTION */
	public static final String ACTION_BUTTON = "com.yj.ecard.receiver.NotificationReceiver.onClick";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(ACTION_BUTTON)) {
			// 通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
			int buttonId = intent.getIntExtra(INTENT_BUTTONID_TAG, 0);
			int messageId = intent.getIntExtra(INTENT_MESSAGEID_TAG, 0);

			switch (buttonId) {
			case BUTTON_ONTIFI_BAR_ID:
				switch (messageId) {
				case UPDATE_MESSAGE_ID: // 安装apk
					CommonManager.getInstance().toInstallApk(context);
					break;

				case PUSH_MESSAGE_ID1: // 进入兑换记录
					Intent intent1 = new Intent(context, ExchangeRecordActivity.class);
					intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent1);
					break;

				case PUSH_MESSAGE_ID2: // 进入秒杀记录
					Intent intent2 = new Intent(context, SeckillRecordActivity.class);
					intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent2);
					break;

				case USER_MESSAGE_ID: // 用户消息
				case SYS_MESSAGE_ID: // 系统消息
					Intent intent3 = new Intent(context, MessageCenterActivity.class);
					intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent3.putExtra("index", messageId);
					context.startActivity(intent3);
					break;
				}
				break;
			}
		}
	}

}
