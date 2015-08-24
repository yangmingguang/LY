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

	private ImageView ivLogo;
	private TextView tvShopName, tvProductName, tvPrice, tvDefaultTips;
	private final int[] btns = { R.id.btn_address, R.id.btn_submit };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_order_detail);
		initView();
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
		tvShopName = (TextView) findViewById(R.id.tv_shop_name);
		tvProductName = (TextView) findViewById(R.id.tv_product_name);
		tvDefaultTips = (TextView) findViewById(R.id.tv_default_tips);
		tvPrice = (TextView) findViewById(R.id.tv_price);
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		for (int btn : btns) {
			findViewById(btn).setOnClickListener(this);
		}

		// 设置参数
		tvShopName.setText(getIntent().getStringExtra("shopName"));
		tvProductName.setText(getIntent().getStringExtra("productName"));
		tvPrice.setText(getIntent().getStringExtra("price"));
		ImageLoaderUtil.load(context, ImageType.NETWORK, getIntent().getStringExtra("imgUrl"),
				R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);

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
		AddressManager.getInstance().getDefaultAddress(context, handler);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case AddressManager.onSuccess:
				/*	ToastUtil.show(context, R.string.login_success_tips, ToastUtil.LENGTH_SHORT);
					startActivity(new Intent(context, MainActivity.class));
					UserManager.getInstance().setLogin(context, true);
					finish();*/
				break;

			case AddressManager.onEmpty:
			case AddressManager.onFailure:
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
			startActivity(new Intent(context, AddAddressActivity.class));
			break;

		case R.id.btn_submit:

			break;
		}
	}

}
