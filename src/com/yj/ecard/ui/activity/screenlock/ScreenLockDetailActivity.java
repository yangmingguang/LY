/**   
* @Title: ScreenLockDetailActivity.java
* @Package com.yj.ecard.ui.activity.screenlock
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-1 上午8:29:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.screenlock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.phone.PhoneManager;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: ScreenLockDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-1 上午8:29:03
* 
*/

public class ScreenLockDetailActivity extends BaseActivity {

	private int advId;
	private int count = 7;
	private TextView tvTime;
	private WebView mWebView;
	private boolean inited = true;
	private ProgressBar mProgressBar;
	private Runnable runnable;

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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (handler != null && runnable != null) {
			handler.removeCallbacks(runnable);
		}
	}

	private void initView() {
		advId = getIntent().getIntExtra("advId", 0);
		String webUrl = getIntent().getStringExtra("webUrl");
		tvTime = (TextView) findViewById(R.id.tv_time);
		mProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// 支持内嵌
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		// 支持进度条
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					mProgressBar.setVisibility(View.GONE);
					loadData();
				} else {
					if (mProgressBar.getVisibility() == View.GONE)
						mProgressBar.setVisibility(View.VISIBLE);
					mProgressBar.setProgress(newProgress);
				}
				super.onProgressChanged(view, newProgress);
			}

		});

		// 支持下载
		mWebView.setDownloadListener(new DownloadListener() {

			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
					long contentLength) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		});

		mWebView.loadUrl(webUrl);
	}

	/**
	 * 
	* @Title: loadData 
	* @Description: 延迟6秒 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void loadData() {
		if (inited) {
			inited = false;
			runnable = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub  
					handler.sendEmptyMessage(0); //要做的事情  
					handler.postDelayed(this, 1000); //每1秒执行一次runnable.  
				}
			};
			handler.postDelayed(runnable, 0);
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				count--;
				if (count < 0) {
					tvTime.setVisibility(View.GONE);
					handler.removeCallbacks(runnable);
					PhoneManager.getInstance().postSeeAdData(context, advId, Constan.TAB_DRAW, 1);
				} else {
					tvTime.setText(count + "秒可收钱");
					tvTime.setVisibility(View.VISIBLE);
				}
				break;
			}
		}
	};

	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			finish();
		}
	}
}
