/**   
* @Title: OrderDetailActivity.java
* @Package com.yj.ecard.ui.activity.order
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-23 下午3:51:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.address.AddressManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.model.AddressBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
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
	private boolean isUsed = true;
	private ImageView ivLogo, ivSwitch;
	private double price, myAmount, needPay;
	private TextView tvName, tvAddress, tvPhone, tvShopName, tvProductName, tvPrice, tvDefaultTips, tvAmount,
			tvMyAmount, tvNeedPay;
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
		price = getIntent().getDoubleExtra("price", 0);
		myAmount = Double.parseDouble(UserManager.getInstance().getAmount(context));
		needPay = myAmount - price;

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
		for (int btn : btns) {
			findViewById(btn).setOnClickListener(this);
		}

		// 设置参数
		tvShopName.setText(getIntent().getStringExtra("shopName"));
		tvProductName.setText(getIntent().getStringExtra("productName"));
		tvPrice.setText("￥" + price);
		tvAmount.setText("￥" + price);
		tvMyAmount.setText("账户余额，可抵现  ￥" + myAmount);

		if (needPay > 0) {
			tvNeedPay.setText("共1件，需付总金额：￥0.0");
		}
		ImageLoaderUtil.load(context, ImageType.NETWORK, getIntent().getStringExtra("imgUrl"),
				R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);

		ivSwitch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isUsed) {
					isUsed = true;
					ivSwitch.setBackgroundResource(R.drawable.setting_open);
					if (needPay > 0) {
						tvNeedPay.setText("共1件，需付总金额：￥0.0");
					}
				} else {
					isUsed = false;
					ivSwitch.setBackgroundResource(R.drawable.setting_close);
					tvNeedPay.setText("共1件，需付总金额：￥" + price);
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

			break;
		}
	}

}
