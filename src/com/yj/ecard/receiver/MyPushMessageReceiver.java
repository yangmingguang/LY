/**   
 * @Title: MyPushMessageReceiver.java
 * @Package com.yj.ecard.receiver
 * @Description: TODO(用一句话描述该文件做什么)
 * @author YangMingGuang
 * @date 2015-10-24 下午4:52:57
 * @version V1.0   
 */

package com.yj.ecard.receiver;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.frontia.api.FrontiaPushMessageReceiver;
import com.yj.ecard.business.notification.CustomNotificationManager;
import com.yj.ecard.publics.http.model.response.PushRecordResponse;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.ToastUtil;

/**
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 * onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 * onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调
 * 
 * 返回值中的errorCode，解释如下： 0 - Success 10001 - Network Problem 30600 - Internal
 * Server Error 30601 - Method Not Allowed 30602 - Request Params Not Valid
 * 30603 - Authentication Failed 30604 - Quota Use Up Payment Required 30605 -
 * Data Required Not Found 30606 - Request Time Expires Timeout 30607 - Channel
 * Token Timeout 30608 - Bind Relation Not Found 30609 - Bind Number Too Many
 * 
 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 * 
 */
public class MyPushMessageReceiver extends FrontiaPushMessageReceiver {

	/**
	 * 调用PushManager.startWork后，sdk将对push
	 * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
	 * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
	 */
	@Override
	public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {

	}

	/**
	 * 接收透传消息的函数。
	 */
	@Override
	public void onMessage(Context context, String message, String customContentString) {
		if (!TextUtils.isEmpty(message)) {
			try {
				JSONObject jsonObject = new JSONObject(message);
				if (jsonObject != null) {
					int type = jsonObject.getInt("type");
					switch (type) {
					// 启动画面
					case 1101:
						//PushWelcomeResponse mPushWelcomeResponse = (PushWelcomeResponse) JsonUtil.jsonToBean(message,
						//		PushWelcomeResponse.class);
						//CommonManager.getInstance().startDownloadImage(context, mPushWelcomeResponse.picUrl);
						break;

					// 电话广告
					case 1102:

						break;

					// 划屏广告
					case 1103:

						break;

					case 1104: // 兑换记录
					case 1105: // 秒杀记录
						PushRecordResponse mPushRecordResponse = (PushRecordResponse) JsonUtil.jsonToBean(message,
								PushRecordResponse.class);
						CustomNotificationManager.getInstance().showPushMessageNotification(context,
								mPushRecordResponse);
						break;

					// 按地区推送不同通知
					case 1106:

						break;
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Demo更新界面展示代码，应用请在这里加入自己的处理逻辑
		updateContent(context, message);
	}

	/**
	 * 接收通知点击的函数。注：推送通知被用户点击前，应用无法通过接口获取通知的内容。
	 */
	@Override
	public void onNotificationClicked(Context context, String title, String description, String customContentString) {

	}

	/**
	 * setTags() 的回调函数。
	 */
	@Override
	public void onSetTags(Context context, int errorCode, List<String> sucessTags, List<String> failTags,
			String requestId) {

	}

	/**
	 * delTags() 的回调函数。
	 */
	@Override
	public void onDelTags(Context context, int errorCode, List<String> sucessTags, List<String> failTags,
			String requestId) {
	}

	/**
	 * listTags() 的回调函数。
	 */
	@Override
	public void onListTags(Context context, int errorCode, List<String> tags, String requestId) {

	}

	/**
	 * PushManager.stopWork() 的回调函数。
	 */
	@Override
	public void onUnbind(Context context, int errorCode, String requestId) {

	}

	private void updateContent(Context context, String content) {
		Log.e(TAG, "updateContent");

		/*
		 * Intent intent = new Intent(); intent.putExtra("result", content);
		 * intent.setClass(context.getApplicationContext(), MainActivity.class);
		 * intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * context.getApplicationContext().startActivity(intent);
		 */

		ToastUtil.show(context, content + "", ToastUtil.LENGTH_LONG);
	}

}
