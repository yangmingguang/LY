/**   
* @Title: ProgressWebView.java
* @Package com.yj.ecard.ui.views.webview
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-17 下午1:40:03
* @version V1.0   
*/

package com.yj.ecard.ui.views.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
* @ClassName: ProgressWebView
* @Description: 带进度条的WebView
* @author YangMingGuang
* @date 2015-10-17 下午1:40:03
* 
*/

public class ProgressWebView extends WebView {

	private ProgressBar progressbar;

	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0);
		progressbar.setLayoutParams(params);
		addView(progressbar);
		//        setWebViewClient(new WebViewClient(){});
		setWebChromeClient(new WebChromeClient());
	}

	public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress == 100) {
				progressbar.setVisibility(GONE);
			} else {
				if (progressbar.getVisibility() == GONE)
					progressbar.setVisibility(VISIBLE);
				progressbar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
