/**   
* @Title: ValueSpikeDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.exchange
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午3:05:31
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.valuespike;

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
import com.yj.ecard.publics.http.model.request.ValueSpikeDetailRequest;
import com.yj.ecard.publics.http.model.response.ValueSpikeDetailResponse;
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
* @ClassName: ValueSpikeDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午3:05:31
* 
*/

public class ValueSpikeDetailActivity extends BaseActivity {

	private int canBuy;
	private int id, userId;
	private boolean isStart;
	private WebView mWebView;
	private DetailBannerViewFlow mDetailBannerViewFlow;
	private View mScrollView, loadingView, containerView;
	private TextView tvTitle, tvType, tvMarketPrice, tvSellCount, tvPrice, btnState;
	private String title, imgUrl, shopName;
	private double price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_valuespike_detail);
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
		imgUrl = getIntent().getStringExtra("imgUrl");
		title = getIntent().getStringExtra("title");
		shopName = getIntent().getStringExtra("shopName");
		price = getIntent().getDoubleExtra("price", 0);
		canBuy = getIntent().getIntExtra("canBuy", 0);
		isStart = getIntent().getBooleanExtra("isStart", false);
		userId = UserManager.getInstance().getUserId(context);

		tvType = (TextView) findViewById(R.id.tv_type);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvMarketPrice = (TextView) findViewById(R.id.tv_marketPrice);
		tvSellCount = (TextView) findViewById(R.id.tv_sellCount);
		tvPrice = (TextView) findViewById(R.id.tv_price);
		btnState = (TextView) findViewById(R.id.btn_state);

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

		// 立即抢购
		if (canBuy == 1 && isStart) {
			btnState.setText("马上抢");
			btnState.setBackgroundResource(R.drawable.selector_btn_invite);
		} else if (canBuy == 2 && isStart) {
			btnState.setText("抢光了");
			btnState.setBackgroundResource(R.drawable.ic_btn_gray);
		} else {
			btnState.setText("未开始");
			btnState.setBackgroundResource(R.drawable.ic_btn_gray);
		}

		btnState.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
				if (canBuy == 1 && isStart) {

					// 旧版支付
					/*Intent intent = new Intent(context, ValueSpikeExchangeActivity.class);
					intent.putExtra("id", id);
					startActivity(intent);*/

					// 新版支付
					Intent intent = new Intent(context, OrderDetailActivity.class);
					intent.putExtra("id", id);
					intent.putExtra("shopName", shopName);
					intent.putExtra("productName", title);
					intent.putExtra("price", price);
					intent.putExtra("imgUrl", imgUrl);
					intent.putExtra("orderType", 1); // 1=秒杀订单，2=兑换订单
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
		ValueSpikeDetailRequest request = new ValueSpikeDetailRequest();
		request.setId(id);
		request.setUserId(userId);
		DataFetcher.getInstance().getValueSpikeDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ValueSpikeDetailResponse valueSpikeDetailResponse = (ValueSpikeDetailResponse) JsonUtil.jsonToBean(
						response, ValueSpikeDetailResponse.class);

				// 解析数据
				if (valueSpikeDetailResponse.status.code == 1) {

					tvTitle.setText(valueSpikeDetailResponse.title);
					tvMarketPrice.setText("市场价：￥" + valueSpikeDetailResponse.marketPrice);
					tvSellCount.setText("销售量：" + valueSpikeDetailResponse.sellCount);
					tvPrice.setText("乐盈价：￥" + valueSpikeDetailResponse.price);
					tvType.setText(valueSpikeDetailResponse.freight);
					// 加载html代码
					Utils.loadHtmlCode(mWebView, valueSpikeDetailResponse.introduce);

					// 图片列表
					if (null != valueSpikeDetailResponse.picList) {
						mDetailBannerViewFlow.creatMyViewFlow(valueSpikeDetailResponse.picList);
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
