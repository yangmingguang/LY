/**   
* @Title: MyPushMessageReceiver.java
* @Package com.yj.ecard.receiver
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-11-15 下午4:44:06
* @version V1.0   
*/

package com.yj.ecard.receiver;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.yj.ecard.business.notification.CustomNotificationManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.response.PushRecordResponse;
import com.yj.ecard.publics.utils.ToastUtil;

/*
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 *onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 *onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调

 * 返回值中的errorCode，解释如下：
 *0 - Success
 *10001 - Network Problem
 *10101  Integrate Check Error
 *30600 - Internal Server Error
 *30601 - Method Not Allowed
 *30602 - Request Params Not Valid
 *30603 - Authentication Failed
 *30604 - Quota Use Up Payment Required
 *30605 -Data Required Not Found
 *30606 - Request Time Expires Timeout
 *30607 - Channel Token Timeout
 *30608 - Bind Relation Not Found
 *30609 - Bind Number Too Many

 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 *
 */

public class MyPushMessageReceiver extends PushMessageReceiver {

	@Override
	public void onBind(Context context, int errorCode, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		// 绑定成功
		ToastUtil.show(context, "====" + errorCode, ToastUtil.LENGTH_LONG);
	}

	@Override
	public void onDelTags(Context arg0, int arg1, List<String> arg2, List<String> arg3, String arg4) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onListTags(Context arg0, int arg1, List<String> arg2, String arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNotificationArrived(Context arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNotificationClicked(Context arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSetTags(Context arg0, int arg1, List<String> arg2, List<String> arg3, String arg4) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUnbind(Context arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
	}

	/**
	 * 接收透传消息的函数。
	 */
	@Override
	public void onMessage(Context context, String message, String customContentString) {
		if (!TextUtils.isEmpty(message)) {

			try {
				String[] msg = message.split("\\|");
				int len = msg.length;
				PushRecordResponse mPushRecordResponse = new PushRecordResponse();
				if (len > 0)
					mPushRecordResponse.setType(Integer.parseInt(msg[0].toString()));
				if (len > 1)
					mPushRecordResponse.setUserId(Integer.parseInt(msg[1].toString()));
				if (len > 2)
					mPushRecordResponse.setTitle(msg[2].toString());
				if (len > 3)
					mPushRecordResponse.setContent(msg[3].toString());

				switch (mPushRecordResponse.getType()) {
				// 用户消息
				case 0:

					break;

				// 系统消息
				case 1:

					break;

				case 1104: // 兑换记录
				case 1105: // 秒杀记录
					// 判断当前用户
					int userId = UserManager.getInstance().getUserId(context);
					if (mPushRecordResponse != null && userId == mPushRecordResponse.userId) {
						CustomNotificationManager.getInstance().showPushMessageNotification(context,
								mPushRecordResponse);
					}
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			ToastUtil.show(context, message, ToastUtil.LENGTH_LONG);
		}
	}

}
