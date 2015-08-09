/**   
* @Title: MyPreferentialListViewHolder.java
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
import com.yj.ecard.publics.model.MyPreferentialBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.ResourceUtil;

/**
* @ClassName: MyPreferentialListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class MyPreferentialListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	public TextView tvTitle, tvContent, tvTime, tvState, btnDelete;

	public MyPreferentialListViewHolder(View view) {
		if (view != null) {
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvContent = (TextView) view.findViewById(R.id.tv_content);
			tvTime = (TextView) view.findViewById(R.id.tv_time);
			tvState = (TextView) view.findViewById(R.id.tv_state);
			btnDelete = (TextView) view.findViewById(R.id.btn_delete);
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
	public void initData(Context context, MyPreferentialBean myPreferentialBean) {
		if (hasInited) {
			tvTitle.setText(myPreferentialBean.title);
			tvContent.setText(myPreferentialBean.content);
			tvTime.setText(myPreferentialBean.addTime);
			tvState.setText(myPreferentialBean.state);
			if ("已审核".equals(myPreferentialBean.state)) {
				tvState.setTextColor(ResourceUtil.getInteger(context, R.color.green));
			} else {
				tvState.setTextColor(ResourceUtil.getInteger(context, R.color.red));
			}
			ImageLoaderUtil.load(context, ImageType.NETWORK, myPreferentialBean.pic, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, ivLogo);
		}
	}

}
