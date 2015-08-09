/**   
* @Title: ScreenLockDetailActivity.java
* @Package com.yj.ecard.ui.activity.screenlock
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-1 上午8:29:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.screenlock;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.yj.ecard.R;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: ScreenLockDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-1 上午8:29:03
* 
*/

public class ScreenLockDetailActivity extends BaseActivity {

	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_screen_lock_detail);
		initView();
		loadAllData();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setScrollBarStyle(0);
		WebSettings settings = mWebView.getSettings();
		settings.setDefaultTextEncodingName("utf-8");// 避免中文乱码  
		settings.setNeedInitialFocus(false);
		settings.setSupportZoom(true);
		settings.setLoadWithOverviewMode(true);//适应屏幕
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setLoadsImagesAutomatically(true);//自动加载图片
		settings.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		String webUrl = getIntent().getStringExtra("webUrl");
		mWebView.loadUrl(webUrl);
	}
}
