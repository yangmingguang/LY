/**   
* @Title: CallPhoneReceiver.java
* @Package com.yj.ecard.receiver
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-27 下午5:44:34
* @version V1.0   
*/

package com.yj.ecard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
* @ClassName: CallPhoneReceiver
* @Description: 5.0系统需要这个拦截电话
* @author YangMingGuang
* @date 2015-7-27 下午5:44:34
* 
*/
public class CallPhoneReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取拨打电话的电话号码
		String num = getResultData();
		setResultData(num);
	}

}
