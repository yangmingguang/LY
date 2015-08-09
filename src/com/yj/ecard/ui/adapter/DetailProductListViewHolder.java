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
	private TextView tvTitle, tvContent;

	public DetailProductListViewHolder(View view) {
		if (view != null) {
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvContent = (TextView) view.findViewById(R.id.tv_content);
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
			tvTitle.setText(productBean.productName);
			tvContent.setText(productBean.productInfo);
			ImageLoaderUtil.load(context, ImageType.NETWORK, productBean.productPic, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, ivLogo);
		}
	}

}
