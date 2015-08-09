/**   
* @Title: PreferentialListViewHolder.java
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
import com.yj.ecard.publics.model.PreferentialBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.ui.views.justify.TextViewEx;

/**
* @ClassName: PreferentialListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class PreferentialListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	private TextViewEx tvContent;
	private TextView tvTitle, tvTime, tvPhone;

	public PreferentialListViewHolder(View view) {
		if (view != null) {
			tvTime = (TextView) view.findViewById(R.id.tv_time);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvPhone = (TextView) view.findViewById(R.id.tv_phone);
			tvContent = (TextViewEx) view.findViewById(R.id.tv_content);
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
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
	public void initData(Context context, PreferentialBean preferentialBean) {
		if (hasInited) {
			tvTitle.setText(preferentialBean.title);
			tvTime.setText(preferentialBean.addTime);
			tvPhone.setText("电话：" + preferentialBean.phone);
			tvContent.setText(preferentialBean.content, true); // 格式化字体
			ImageLoaderUtil.load(context, ImageType.NETWORK, preferentialBean.pic, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, ivLogo);
		}
	}

}
