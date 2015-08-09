/**   
* @Title: DetailEnvironmentListViewHolder.java
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
import com.yj.ecard.publics.model.EnvironmentBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: DetailEnvironmentListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class DetailEnvironmentListViewHolder {

	private TextView tvTitle;
	private ImageView ivLogo;
	private boolean hasInited;

	public DetailEnvironmentListViewHolder(View view) {
		if (view != null) {
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param environmentBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, EnvironmentBean environmentBean) {
		if (hasInited) {
			tvTitle.setText(environmentBean.title);
			ImageLoaderUtil.load(context, ImageType.NETWORK, environmentBean.enviPic,
					R.drawable.banner_default_vertical, R.drawable.banner_default_vertical, ivLogo);
		}
	}

}
