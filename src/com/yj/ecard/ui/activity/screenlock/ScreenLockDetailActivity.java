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
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return false;
	}

	private void initView() {
		String webUrl = getIntent().getStringExtra("webUrl");
		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.loadUrl(webUrl);
	}

	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			finish();
		}
	}
}
