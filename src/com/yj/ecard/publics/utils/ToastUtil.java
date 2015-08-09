/**   
* @Title: ToastUtil.java
* @Package cn.cty.saletool.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang 
* @date 2015-5-18 下午3:28:10
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

import android.content.Context;
import android.os.Handler;

/**
* @ClassName: ToastUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-18 下午3:28:10
* 
*/

public class ToastUtil {
	private static android.widget.Toast toast;
	private static Handler handler = new Handler();

	public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;
	public static final int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;

	private static Runnable run = new Runnable() {
		public void run() {
			toast.cancel();
		}
	};

	/**
	* @Title: toast 
	* @Description: TODO 
	* @param @param context
	* @param @param msg
	* @param @param duration    
	* @return void
	 */
	private static void toast(Context context, CharSequence msg, int duration) {
		handler.removeCallbacks(run);
		// handler的duration不能直接对应Toast的常量时长，在此针对Toast的常量相应定义时长
		switch (duration) {
		case LENGTH_SHORT:// Toast.LENGTH_SHORT值为0，对应的持续时间大概为1s
			duration = 1000;
			break;
		case LENGTH_LONG:// Toast.LENGTH_LONG值为1，对应的持续时间大概为3s
			duration = 3000;
			break;
		default:
			break;
		}
		if (null != toast) {
			toast.setText(msg);
		} else {
			toast = android.widget.Toast.makeText(context, msg, duration);
		}
		handler.postDelayed(run, duration);
		toast.show();
	}

	/**
	* @Title: show 
	* @Description: TODO 
	* @param @param context
	* @param @param msg
	* @param @param duration
	* @param @throws NullPointerException    
	* @return void
	 */
	public static void show(Context context, CharSequence msg, int duration) throws NullPointerException {
		if (null == context) {
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration) {
			duration = LENGTH_SHORT;
		}
		toast(context, msg, duration);
	}

	/**
	* @Title: show 
	* @Description: TODO 
	* @param @param context
	* @param @param resId
	* @param @param duration
	* @param @throws NullPointerException    
	* @return void
	 */
	public static void show(Context context, int resId, int duration) throws NullPointerException {
		if (null == context) {
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration) {
			duration = LENGTH_SHORT;
		}
		toast(context, context.getResources().getString(resId), duration);
	}

}
