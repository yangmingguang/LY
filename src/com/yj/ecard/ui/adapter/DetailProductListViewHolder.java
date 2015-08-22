/**   
* @Title: DetailProductListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.model.ProductBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: DetailProductListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class DetailProductListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	private TextView tvTitle, tvPrice, tvMarketPrice, tvSaleNum;

	public DetailProductListViewHolder(View view) {
		if (view != null) {
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvPrice = (TextView) view.findViewById(R.id.tv_price);
			tvMarketPrice = (TextView) view.findViewById(R.id.tv_marketPrice);
			tvSaleNum = (TextView) view.findViewById(R.id.tv_saleNum);
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
	public void initData(Context context, ProductBean productBean) {
		if (hasInited) {
			tvTitle.setText(productBean.title);
			tvPrice.setText("￥" + productBean.price);
			tvMarketPrice.setText("￥" + productBean.marketPrice);
			tvMarketPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
			tvSaleNum.setText("已售" + productBean.sales);
			ImageLoaderUtil.load(context, ImageType.NETWORK, productBean.imgUrl, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, ivLogo);
		}
	}

}
