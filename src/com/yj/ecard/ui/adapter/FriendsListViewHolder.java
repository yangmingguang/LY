/**   
* @Title: FriendsListViewHolder.java
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

import com.yj.ecard.R;
import com.yj.ecard.publics.model.FriendsBean;
import com.yj.ecard.publics.utils.Utils;

/**
* @ClassName: FriendsListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class FriendsListViewHolder {

	private boolean hasInited;
	private ImageView ivLogo, ivLevel;
	private TextView tvUserName, tvAmount;

	public FriendsListViewHolder(View view) {
		if (view != null) {
			tvUserName = (TextView) view.findViewById(R.id.tv_userName);
			tvAmount = (TextView) view.findViewById(R.id.tv_amount);
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			ivLevel = (ImageView) view.findViewById(R.id.iv_level);
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
	public void initData(Context context, FriendsBean friendsBean) {
		if (hasInited) {
			ivLevel.setBackgroundResource(Utils.getLevelDrawable(friendsBean.level));
			tvUserName.setText(friendsBean.userName);
			tvAmount.setText("￥" + friendsBean.amount);
		}
	}
}
