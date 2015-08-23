/**   
* @Title: OrderDetailActivity.java
* @Package com.yj.ecard.ui.activity.order
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-23 下午3:51:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.order;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: OrderDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-23 下午3:51:03
* 
*/

public class OrderDetailActivity extends BaseActivity {

	private ImageView ivLogo;
	private TextView tvShopName, tvProductName, tvPrice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_order_detail);
		initView();
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
		tvPrice = (TextView) findViewById(R.id.tv_price);
		ivLogo = (ImageView) findViewById(R.id.iv_logo);

		// 设置参数
		tvShopName.setText(getIntent().getStringExtra("shopName"));
		tvProductName.setText(getIntent().getStringExtra("productName"));
		tvPrice.setText(getIntent().getStringExtra("price"));
		ImageLoaderUtil.load(context, ImageType.NETWORK, getIntent().getStringExtra("imgUrl"),
				R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);
	}

}
