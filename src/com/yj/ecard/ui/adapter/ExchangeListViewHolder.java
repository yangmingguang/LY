/**   
* @Title: ExchangeListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.model.ExchangeBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: ExchangeListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class ExchangeListViewHolder {

	private ImageView logo;
	private boolean hasInited;
	public TextView title, price, marketPrice, selled, exchangeBtn;

	public ExchangeListViewHolder(View view) {
		if (view != null) {
			logo = (ImageView) view.findViewById(R.id.iv_logo);
			title = (TextView) view.findViewById(R.id.tv_title);
			price = (TextView) view.findViewById(R.id.tv_price);
			selled = (TextView) view.findViewById(R.id.tv_selled);
			exchangeBtn = (TextView) view.findViewById(R.id.btn_exchange);
			marketPrice = (TextView) view.findViewById(R.id.tv_marketPrice);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param productBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, ExchangeBean productBean) {
		if (hasInited) {
			title.setText(productBean.title);
			price.setText("乐盈价:" + productBean.price + "元");
			selled.setText("已兑换:" + productBean.selled + "个");
			marketPrice.setText("市场价:" + productBean.marketPrice + "元");
			if (productBean.canExchange) {
				exchangeBtn.setBackgroundResource(R.drawable.selector_btn_invite);
			} else {
				exchangeBtn.setBackgroundResource(R.drawable.ic_btn_gray);
			}
			ImageLoaderUtil.load(context, ImageType.NETWORK, productBean.picUrl, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, logo);
		}
	}

}
