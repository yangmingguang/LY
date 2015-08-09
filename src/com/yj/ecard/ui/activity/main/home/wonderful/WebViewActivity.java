/**   
* @Title: WebViewActivity.java
* @Package com.yj.ecard.ui.activity.main.home.wonderful
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-13 上午10:45:09
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.wonderful;

import android.os.Bundle;
import android.webkit.WebView;

import com.yj.ecard.R;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: WebViewActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-13 上午10:45:09
* 
*/

public class WebViewActivity extends BaseActivity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_webview);
		initViews();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		String url = getIntent().getStringExtra("webSite");
		webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(url);
	}

}
