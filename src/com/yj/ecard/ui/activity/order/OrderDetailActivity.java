/**   
* @Title: OrderDetailActivity.java
* @Package com.yj.ecard.ui.activity.order
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-23 下午3:51:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.order;

import java.text.DecimalFormat;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.address.AddressManager;
import com.yj.ecard.business.alipay.AlipayManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.BalanceRequest;
import com.yj.ecard.publics.http.model.request.OrderRequest;
import com.yj.ecard.publics.http.model.response.BalanceResponse;
import com.yj.ecard.publics.http.model.response.OrderResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.AddressBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.MD5Util;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: OrderDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-23 下午3:51:03
* 
*/

public class OrderDetailActivity extends BaseActivity implements OnClickListener {

	private boolean hasData;
	private View loadingView;
	private String productName;
	private int isAddmyamont = 1;
	private EditText etFeedback, etPhone;
	private boolean isUsed = true;
	private int id, orderType, sortId;
	private ImageView ivLogo, ivSwitch;
	private String name, address, phone, phoneNum;
	private double price, myAmount, needPay;
	private TextView tvName, tvAddress, tvPhone, tvShopName, tvProductName, tvPrice, tvDefaultTips, tvAmount,
			tvMyAmount, tvNeedPay;
	private DecimalFormat mDecimalFormat = new DecimalFormat("######0.00");
	private final int[] btns = { R.id.btn_address, R.id.btn_submit };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_order_detail);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getBalanceData();
		loadAllData();
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		id = getIntent().getIntExtra("id", 0);
		price = getIntent().getDoubleExtra("price", 0);
		orderType = getIntent().getIntExtra("orderType", 2);// 1=秒杀订单，2=商品订单，3=兑换订单
		sortId = getIntent().getIntExtra("sortId", 0);

		tvName = (TextView) findViewById(R.id.tv_name);
		tvPhone = (TextView) findViewById(R.id.tv_phone);
		tvAddress = (TextView) findViewById(R.id.tv_address);
		tvShopName = (TextView) findViewById(R.id.tv_shop_name);
		tvProductName = (TextView) findViewById(R.id.tv_product_name);
		tvDefaultTips = (TextView) findViewById(R.id.tv_default_tips);
		tvPrice = (TextView) findViewById(R.id.tv_price);
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		ivSwitch = (ImageView) findViewById(R.id.iv_switch);
		tvAmount = (TextView) findViewById(R.id.tv_amount);
		tvMyAmount = (TextView) findViewById(R.id.tv_my_amount);
		tvNeedPay = (TextView) findViewById(R.id.tv_need_pay);
		etFeedback = (EditText) findViewById(R.id.et_feedback);
		etPhone = (EditText) findViewById(R.id.et_phone);
		loadingView = findViewById(R.id.l_loading_rl);

		for (int btn : btns) {
			findViewById(btn).setOnClickListener(this);
		}

		// 设置参数
		tvShopName.setText(getIntent().getStringExtra("shopName"));
		productName = getIntent().getStringExtra("productName");
		tvProductName.setText(productName);
		tvPrice.setText("￥" + price);
		tvAmount.setText("￥" + price);

		// 话费充值
		if (sortId == 3) {
			etPhone.setVisibility(View.VISIBLE);
			findViewById(R.id.btn_address).setClickable(false);
			((TextView) findViewById(R.id.tv_title_tips)).setText("请填写充值号码");
		}

		ImageLoaderUtil.load(context, ImageType.NETWORK, getIntent().getStringExtra("imgUrl"),
				R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);

		ivSwitch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isUsed) {
					isUsed = true;
					isAddmyamont = 1;
					ivSwitch.setBackgroundResource(R.drawable.setting_open);
					if (needPay > 0) {
						tvNeedPay.setText("共1件，应需付金额：￥0.0");
					} else {
						tvNeedPay.setText("共1件，应需付金额：￥" + mDecimalFormat.format((-needPay)));
					}
				} else {
					isUsed = false;
					isAddmyamont = 0;
					ivSwitch.setBackgroundResource(R.drawable.setting_close);
					tvNeedPay.setText("共1件，应需付金额：￥" + price);
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
		// TODO Auto-generated method stub
		AddressManager.getInstance().getDefaultAddress(context, handler, 0);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {

			loadingView.setVisibility(View.GONE);

			switch (msg.what) {
			case AddressManager.onSuccess:
				hasData = true;
				tvDefaultTips.setVisibility(View.GONE);
				AddressBean bean = (AddressBean) msg.obj;
				tvName.setText(bean.realName);
				tvPhone.setText(bean.phone);
				tvAddress.setText(bean.address);
				break;

			case AddressManager.onEmpty:
			case AddressManager.onFailure:
				hasData = false;
				tvName.setText("");
				tvPhone.setText("");
				tvAddress.setText("");
				tvDefaultTips.setVisibility(View.VISIBLE);
				break;

			case AlipayManager.SDK_PAY_FLAG:
				String result = (String) msg.obj;
				if (result.contains("9000")) {
					ToastUtil.show(context, "支付成功！", ToastUtil.LENGTH_LONG);
					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							finish();
						}
					}, 1000);
				}
				break;
			}

			return true;
		}
	});

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_address:
			if (hasData) {
				startActivity(new Intent(context, AddressListActivity.class));
			} else {
				startActivity(new Intent(context, AddAddressActivity.class));
			}
			break;

		case R.id.btn_submit:
			name = tvName.getText().toString();
			phone = tvPhone.getText().toString();
			address = tvAddress.getText().toString();
			phoneNum = etPhone.getText().toString();

			if (sortId == 3) { // 话费充值
				if (TextUtils.isEmpty(phoneNum)) {
					ToastUtil.show(context, R.string.phone_number_tips, ToastUtil.LENGTH_SHORT);
				} else {
					submitOrder();
				}
			} else {
				if (TextUtils.isEmpty(name)) {
					ToastUtil.show(context, R.string.name_tips, ToastUtil.LENGTH_SHORT);
				} else if (TextUtils.isEmpty(phone)) {
					ToastUtil.show(context, R.string.phone_tips, ToastUtil.LENGTH_SHORT);
				} else if (TextUtils.isEmpty(address)) {
					ToastUtil.show(context, R.string.address_tips, ToastUtil.LENGTH_SHORT);
				} else {
					submitOrder();
				}
			}

			break;
		}
	}

	/**
	 * 
	* @Title: getBalanceData 
	* @Description: 获取余额
	* @param @param context
	* @param @param tvBalance    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getBalanceData() {
		BalanceRequest request = new BalanceRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setPassWord(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getBalanceResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				BalanceResponse mBalanceResponse = (BalanceResponse) JsonUtil.jsonToBean(response,
						BalanceResponse.class);

				// 数据响应状态
				int stateCode = mBalanceResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					myAmount = mBalanceResponse.myamont;
					tvMyAmount.setText("账户余额，可抵现  ￥" + myAmount);

					needPay = myAmount - price;
					if (needPay > 0) {
						tvNeedPay.setText("共1件，应需付金额：￥0.0");
					} else {
						tvNeedPay.setText("共1件，应需付金额：￥" + mDecimalFormat.format((-needPay)));
					}

					UserManager.getInstance().setAmount(context, myAmount + "");
					break;
				case Constan.EMPTY_CODE:
					//handler.sendEmptyMessage(onEmpty);
					break;
				case Constan.ERROR_CODE:
					//handler.sendEmptyMessage(onFailure);
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

	private void submitOrder() {
		Utils.showProgressDialog(this);
		int userId = UserManager.getInstance().getUserId(context);
		String feedBack = etFeedback.getText().toString();
		String imei = Utils.getIMEI(context);
		String sign = MD5Util.getMD5(imei + userId + id); // MD5加密

		OrderRequest request = new OrderRequest();
		request.setProductId(id);
		request.setOrderType(orderType); // 1=秒杀订单，2=商品订单，3=兑换订单
		if (sortId == 3) {
			request.setPhone(phoneNum);
		} else {
			request.setPhone(phone);
		}
		request.setAddress(address);
		request.setIsAddmyamont(isAddmyamont);
		request.setRealName(name);
		request.setFeedBack(feedBack);
		request.setSign(sign);
		request.setUserId(userId);

		DataFetcher.getInstance().postOrderResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
				LogUtil.getLogger().d("response==>" + response.toString());
				OrderResponse orderResponse = (OrderResponse) JsonUtil.jsonToBean(response, OrderResponse.class);

				// 数据响应状态
				int stateCode = orderResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					// 使用支付宝支付
					if (orderResponse.needPay > 0) {
						toAliPay(productName, productName, orderResponse.orderNum, orderResponse.needPay + "");
					} else {
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								finish();
							}
						}, 1000);
						ToastUtil.show(context, orderResponse.status.msg, ToastUtil.LENGTH_LONG);
					}
					break;

				case Constan.EMPTY_CODE:
				case Constan.ERROR_CODE:
					ToastUtil.show(context, orderResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
			}
		});
	}

	/**
	 * 
	* @Title: toAliPay 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param subject 商品名称
	* @param @param shopDesc  商品描述
	* @param @param price 支付金额
	* @return void    返回类型 
	* @throws
	 */
	private void toAliPay(String shopName, String shopDesc, String orderNum, String amount) {
		/*String subject = "测试的商品";
		String body = "该测试商品的详细描述";
		String price = "0.01";*/
		AlipayManager.getInstance().SDKPay(this, handler, shopName, shopDesc, orderNum, amount);
	}

}
