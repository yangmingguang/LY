/**   
* @Title: ProductDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.business
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-22 下午4:40:38
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.http.model.request.ProductDetailRequest;
import com.yj.ecard.publics.http.model.response.ProductDetailResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.order.OrderDetailActivity;
import com.yj.ecard.ui.views.webview.CustomWebView;

/**
* @ClassName: ProductDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-22 下午4:40:38
* 
*/

public class ProductDetailActivity extends BaseActivity implements OnClickListener {

	private Button btnBuy;
	private View loadingView;
	private ImageView ivLogo;
	private String phoneNumber;
	private CustomWebView mWebView;
	private ProductDetailResponse productDetailResponse;
	private TextView tvTitle, tvPhone, tvAddress, tvShopName, tvPrice, tvMarketPrice;

	private final int[] btns = { R.id.btn_phone_ll, R.id.btn_buy };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_product_detail);
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
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvPhone = (TextView) findViewById(R.id.tv_phone);
		tvAddress = (TextView) findViewById(R.id.tv_address);
		tvShopName = (TextView) findViewById(R.id.tv_shop_name);
		btnBuy = (Button) findViewById(R.id.btn_buy);
		tvPrice = (TextView) findViewById(R.id.tv_price);
		tvMarketPrice = (TextView) findViewById(R.id.tv_marketPrice);
		loadingView = findViewById(R.id.l_loading_rl);

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

		ProductDetailRequest request = new ProductDetailRequest();
		request.setId(id);
		request.setLatitude(latitude);
		request.setLongitude(longitude);
		DataFetcher.getInstance().getBusinessProductDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				productDetailResponse = (ProductDetailResponse) JsonUtil.jsonToBean(response,
						ProductDetailResponse.class);
				// 数据响应状态
				int stateCode = productDetailResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					phoneNumber = productDetailResponse.phone;
					tvTitle.setText(productDetailResponse.title);
					tvPhone.setText(phoneNumber);
					tvShopName.setText(productDetailResponse.merchantsName);
					tvAddress.setText(productDetailResponse.address);
					tvPrice.setText("￥" + productDetailResponse.price);
					tvMarketPrice.setText("￥" + productDetailResponse.marketPrice);
					tvMarketPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
					if (productDetailResponse.canBuy == 0) {
						btnBuy.setBackgroundResource(R.drawable.round_shape_gray);
					} else {
						btnBuy.setBackgroundResource(R.drawable.round_shape_yellow);
					}
					ImageLoaderUtil.load(context, ImageType.NETWORK, productDetailResponse.imgUrl,
							R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);
					// 公司简介
					Utils.loadHtmlCode(mWebView, productDetailResponse.introduce); // 加载html代码
					loadingView.setVisibility(View.GONE);
					break;
				case Constan.EMPTY_CODE:

					break;
				case Constan.ERROR_CODE:

					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_phone_ll:
			if (null != phoneNumber)
				Utils.telCall(context, phoneNumber);
			break;

		case R.id.btn_buy:
			if (productDetailResponse != null) {
				Intent intent = new Intent(context, OrderDetailActivity.class);
				intent.putExtra("shopName", productDetailResponse.merchantsName);
				intent.putExtra("productName", productDetailResponse.title);
				intent.putExtra("price", productDetailResponse.price);
				intent.putExtra("imgUrl", productDetailResponse.imgUrl);
				startActivity(intent);
			}
			break;
		}
	}
}
