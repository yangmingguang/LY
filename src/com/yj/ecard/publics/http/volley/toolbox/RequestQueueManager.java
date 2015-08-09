/**   
* @Title: RequestQueueManager.java
* @Package com.iframe.source.publics.http.volley.toolbox
* @Description: TODO(用一句话描述该文件做什么)
* @author mingguang.yang   
* @date 2015年1月22日 上午11:06:36
* @version V1.0   
*/

package com.yj.ecard.publics.http.volley.toolbox;

import android.content.Context;

import com.yj.ecard.publics.http.volley.RequestQueue;

/**
* @ClassName: RequestQueueManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author mingguang.yang
* @date 2015年1月22日 上午11:06:36
* 
*/

public class RequestQueueManager {

	private static RequestQueue mRequestQueue;

	private RequestQueueManager() {
		// TODO Auto-generated constructor stub
	}

	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}

	public static synchronized RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("Not initialized");
		}
	}
}
