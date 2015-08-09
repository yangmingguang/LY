/**   
* @Title: BannerDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-30 上午10:14:23
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.yj.ecard.R;
import com.yj.ecard.publics.http.model.request.BannerDetailRequest;
import com.yj.ecard.publics.http.model.response.BannerDetailResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: BannerDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-30 上午10:14:23
* 
*/

public class BannerDetailActivity extends BaseActivity {

	private int id;
	private WebView mWebView;
	private View loadingView, emptyView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_banner_detail);
		initViews();
		loadAllData();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		id = getIntent().getIntExtra("id", 0);
		emptyView = findViewById(R.id.l_empty_rl);
		loadingView = findViewById(R.id.l_loading_rl);
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
		BannerDetailRequest request = new BannerDetailRequest();
		request.setId(id);
		DataFetcher.getInstance().getBannerDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				BannerDetailResponse mBannerDetailResponse = (BannerDetailResponse) JsonUtil.jsonToBean(response,
						BannerDetailResponse.class);

				// 数据响应状态
				int stateCode = mBannerDetailResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					Utils.loadHtmlCode(mWebView, mBannerDetailResponse.content); // 加载html代码
					mWebView.setVisibility(View.VISIBLE);
					loadingView.setVisibility(View.GONE);
					emptyView.setVisibility(View.GONE);
					break;
				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mBannerDetailResponse.status.msg, ToastUtil.LENGTH_LONG);
					mWebView.setVisibility(View.GONE);
					loadingView.setVisibility(View.GONE);
					emptyView.setVisibility(View.VISIBLE);
					break;
				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_LONG);
					mWebView.setVisibility(View.GONE);
					loadingView.setVisibility(View.GONE);
					emptyView.setVisibility(View.VISIBLE);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_LONG);
				mWebView.setVisibility(View.GONE);
				loadingView.setVisibility(View.GONE);
				emptyView.setVisibility(View.VISIBLE);
			}
		}, true);

	}

}
