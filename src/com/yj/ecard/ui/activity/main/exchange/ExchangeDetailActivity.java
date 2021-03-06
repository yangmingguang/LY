/**   
* @Title: ExchangeDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.exchange
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午3:05:31
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.exchange;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.ExchangeDetailRequest;
import com.yj.ecard.publics.http.model.response.ExchangeDetailResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.order.OrderDetailActivity;
import com.yj.ecard.ui.views.viewflow.DetailBannerViewFlow;

/**
* @ClassName: ExchangeDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午3:05:31
* 
*/

public class ExchangeDetailActivity extends BaseActivity {

	private int id, sortId;
	private String account;
	private WebView mWebView;
	private boolean canExchange;
	private DetailBannerViewFlow mDetailBannerViewFlow;
	private View mScrollView, loadingView, containerView;
	private TextView tvTitle, tvType, tvMarketPrice, tvInventory, btnExchange;
	private String title, imgUrl, shopName;
	private double price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exchange_detail);
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
		sortId = getIntent().getIntExtra("sortId", 0);
		title = getIntent().getStringExtra("title");
		imgUrl = getIntent().getStringExtra("imgUrl");
		price = getIntent().getDoubleExtra("price", 0);
		shopName = getIntent().getStringExtra("shopName");
		canExchange = getIntent().getBooleanExtra("canExchange", false);
		account = UserManager.getInstance().getUserName(context);

		tvType = (TextView) findViewById(R.id.tv_type);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		btnExchange = (TextView) findViewById(R.id.btn_exchange);
		tvMarketPrice = (TextView) findViewById(R.id.tv_marketPrice);
		tvInventory = (TextView) findViewById(R.id.tv_inventory);
		loadingView = findViewById(R.id.l_loading_rl);
		mScrollView = findViewById(R.id.ll_scrollview);
		containerView = findViewById(R.id.fl_container);
		mDetailBannerViewFlow = new DetailBannerViewFlow(context, containerView, R.id.fb_viewflow,
				R.id.fb_viewflowindic, null);

		// WebView
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

		// 兑换按钮
		if (canExchange) {
			btnExchange.setBackgroundResource(R.drawable.selector_btn_invite);
		} else {
			btnExchange.setBackgroundResource(R.drawable.ic_btn_gray);
		}
		btnExchange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
				if (canExchange) {
					/*Intent intent = new Intent(context, ExchangeAddressActivity.class);
					intent.putExtra("id", id);
					startActivity(intent);*/

					// 新版支付
					Intent intent = new Intent(context, OrderDetailActivity.class);
					intent.putExtra("id", id);
					intent.putExtra("shopName", shopName);
					intent.putExtra("productName", title);
					intent.putExtra("price", price);
					intent.putExtra("imgUrl", imgUrl);
					intent.putExtra("orderType", 3); // 1=秒杀订单，2=商品订单，3=兑换订单
					intent.putExtra("sortId", sortId); // 话费充值，特别处理
					startActivity(intent);
				}
			}
		});
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		ExchangeDetailRequest request = new ExchangeDetailRequest();
		request.setId(id);
		request.setUsername(account);
		DataFetcher.getInstance().getProductDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ExchangeDetailResponse productDetailResponse = (ExchangeDetailResponse) JsonUtil.jsonToBean(response,
						ExchangeDetailResponse.class);

				// 解析数据
				if (productDetailResponse.status.code == 1) {
					tvTitle.setText(productDetailResponse.title);
					tvMarketPrice.setText("￥" + productDetailResponse.marketPrice + "元");
					tvInventory.setText("库存：" + productDetailResponse.inventory + "个");
					// 加载html代码
					Utils.loadHtmlCode(mWebView, productDetailResponse.content);

					// 邮件方式
					if (productDetailResponse.isMailed) {
						tvType.setText("包邮");
					} else {
						tvType.setText("到付");
					}

					// 图片列表
					if (null != productDetailResponse.picList) {
						mDetailBannerViewFlow.creatMyViewFlow(productDetailResponse.picList);
					}
				}

				// 显示加载内容
				loadingView.setVisibility(View.GONE);
				mScrollView.setVisibility(View.VISIBLE);
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);
	}
}
