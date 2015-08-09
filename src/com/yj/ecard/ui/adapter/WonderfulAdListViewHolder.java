/**   
* @Title: WonderfulAdListViewHolder.java
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
import com.yj.ecard.publics.model.WonderfulAdBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: WonderfulAdListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class WonderfulAdListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	private TextView tvTitle, tvContent, tvPhone;

	public WonderfulAdListViewHolder(View view) {
		if (view != null) {
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvContent = (TextView) view.findViewById(R.id.tv_content);
			tvPhone = (TextView) view.findViewById(R.id.tv_phone);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wonderfulAdBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, WonderfulAdBean wonderfulAdBean) {
		if (hasInited) {
			tvTitle.setText(wonderfulAdBean.title);
			tvContent.setText(wonderfulAdBean.introduction);
			tvPhone.setText("电话：" + wonderfulAdBean.phone);
			ImageLoaderUtil.load(context, ImageType.NETWORK, wonderfulAdBean.thumbPic,
					R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);
		}
	}

}
