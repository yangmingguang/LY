/**   
* @Title: BusinessDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.business
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午6:10:28
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.main.BusinessTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.BusinessDetailResponse;
import com.yj.ecard.publics.model.ProductBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.DetailProductListAdapter;
import com.yj.ecard.ui.views.custom.MyListView;
import com.yj.ecard.ui.views.webview.CustomWebView;

/**
* @ClassName: BusinessDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午6:10:28
* 
*/

public class BusinessDetailActivity1 extends BaseActivity implements OnClickListener {

	private String webUrl;
	private String phoneNumber;
	private MyListView mListView;
	private List<ProductBean> productList;
	private DetailProductListAdapter mAdapter;

	private CustomWebView mWebView;
	private ImageView ivLogo, btnSite;
	private View loadingView, productView;
	private TextView tvTitle, tvPhone, tvAddress;

	private final int[] btns = { R.id.btn_phone_ll };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_business_detail1);
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
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		btnSite = (ImageView) findViewById(R.id.btn_site);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvPhone = (TextView) findViewById(R.id.tv_phone);
		tvAddress = (TextView) findViewById(R.id.tv_address);
		loadingView = findViewById(R.id.l_loading_rl);
		productView = findViewById(R.id.ll_product_layout);

		mListView = (MyListView) findViewById(R.id.lv_product);
		mAdapter = new DetailProductListAdapter(context);
		mListView.setAdapter(mAdapter);

		// webView 
		mWebView = (CustomWebView) findViewById(R.id.webview);
		mWebView.setScrollBarStyle(0);
		WebSettings settings = mWebView.getSettings();
		settings.setDefaultTextEncodingName("utf-8");// 避免中文乱码  
		settings.setNeedInitialFocus(false);
		settings.setSupportZoom(true);
		settings.setLoadWithOverviewMode(true);//适应屏幕
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setLoadsImagesAutomatically(true);//自动加载图片
		settings.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);

		btnSite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 使用内置浏览器
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(webUrl);
				intent.setData(content_url);
				startActivity(intent);
			}
		});

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		int id = getIntent().getIntExtra("id", 0);
		double latitude = Double.parseDouble(CommonManager.getInstance().getLocationlat(context));
		double longitude = Double.parseDouble(CommonManager.getInstance().getLocationlng(context));
		BusinessTabManager.getInstance().getBusinessDetailData(context, handler, id, latitude, longitude);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case MeTabManager.onSuccess:
				setDataInView((BusinessDetailResponse) msg.obj);
				break;

			case MeTabManager.onEmpty:

				break;

			case MeTabManager.onFailure:

				break;
			}
			return true;
		}
	});

	/** 
	* @Title: setDataInView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param response2    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	protected void setDataInView(BusinessDetailResponse response) {
		webUrl = response.webUrl;
		if (webUrl != null) {
			btnSite.setVisibility(View.VISIBLE);
		}
		phoneNumber = response.phone;
		productList = response.productList;
		tvTitle.setText(response.merName);
		tvPhone.setText(phoneNumber);
		tvAddress.setText(response.address);
		ImageLoaderUtil.load(context, ImageType.NETWORK, response.picUrl, R.drawable.banner_detail_default,
				R.drawable.banner_detail_default, ivLogo);
		// 公司产品
		if (productList != null && !productList.isEmpty()) {
			mAdapter.setList(productList);
		} else {
			productView.setVisibility(View.GONE);
		}
		// 公司简介
		Utils.loadHtmlCode(mWebView, response.introduction); // 加载html代码

		// 隐藏loading
		loadingView.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_phone_ll:
			if (null != phoneNumber)
				Utils.telCall(context, phoneNumber);
			break;
		}
	}
}
