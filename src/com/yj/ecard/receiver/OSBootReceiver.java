/**   
* @Title: OSBootReceiver.java
* @Package com.yj.ecard.receiver
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-1 下午10:21:30
* @version V1.0   
*/

package com.yj.ecard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.service.PhoneService;

/**
* @ClassName: OSBootReceiver
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-1 下午10:21:30
* 
*/

public class OSBootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		// 系统开机，启动服务
		Intent intent1 = new Intent(context, PhoneService.class);
		intent1.putExtra(Constan.COMMAND_FLAG, Constan.START);
		context.startService(intent1);

	}
}
