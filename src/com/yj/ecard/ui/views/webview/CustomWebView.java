/**   
* @Title: CustomWebView.java
* @Package com.yj.ecard.ui.views.webview
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-28 上午9:15:39
* @version V1.0   
*/

package com.yj.ecard.ui.views.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
* @ClassName: CustomWebView
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-28 上午9:15:39
* 
*/

public class CustomWebView extends WebView {

	ScrollInterface web;

	public CustomWebView(Context context) {
		super(context);
	}

	public CustomWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		//Log.e("hhah",""+l+" "+t+" "+oldl+" "+oldt);        
		web.onScrollChanged(l, t, oldl, oldt);
	}

	public void setOnCustomScroolChangeListener(ScrollInterface t) {
		this.web = t;
	}

	/**     
	* 定义滑动接口     
	* @param t     
	*/
	public interface ScrollInterface {
		public void onScrollChanged(int l, int t, int oldl, int oldt);
	}

}
