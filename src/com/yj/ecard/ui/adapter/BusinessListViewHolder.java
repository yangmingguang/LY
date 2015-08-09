/**   
* @Title: BusinessListViewHolder.java
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
import com.yj.ecard.publics.model.BusinessBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: BusinessListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class BusinessListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	private TextView tvTitle, tvContent, tvPhone, tvDistance;

	public BusinessListViewHolder(View view) {
		if (view != null) {
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvPhone = (TextView) view.findViewById(R.id.tv_phone);
			tvContent = (TextView) view.findViewById(R.id.tv_content);
			tvDistance = (TextView) view.findViewById(R.id.tv_distance);
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param businessBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, BusinessBean businessBean) {
		if (hasInited) {
			tvTitle.setText(businessBean.merName);
			tvContent.setText(businessBean.introduction);
			tvPhone.setText("电话：" + businessBean.phone);
			tvDistance.setText(businessBean.distance);
			ImageLoaderUtil.load(context, ImageType.NETWORK, businessBean.picUrl, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, ivLogo);
		}
	}

}
