/**   
* @Title: ValueSpikeListViewHolder.java
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
import com.yj.ecard.publics.model.ValueSpikeBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: ValueSpikeListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class ValueSpikeListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	public TextView tvTitle, tvMarketPrice, tvPrice, btnState;

	public ValueSpikeListViewHolder(View view) {
		if (view != null) {
			ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvMarketPrice = (TextView) view.findViewById(R.id.tv_marketPrice);
			tvPrice = (TextView) view.findViewById(R.id.tv_price);
			btnState = (TextView) view.findViewById(R.id.btn_state);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param valueSpikeBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, ValueSpikeBean valueSpikeBean) {
		if (hasInited) {
			tvTitle.setText(valueSpikeBean.title);
			tvMarketPrice.setText("市场价：￥" + valueSpikeBean.marketPrice);
			tvPrice.setText("乐盈价：￥" + valueSpikeBean.price);
			if (valueSpikeBean.canbuy == 1 && valueSpikeBean.isStart) {
				btnState.setText("马上抢");
				btnState.setBackgroundResource(R.drawable.selector_btn_invite);
			} else {
				btnState.setText("未开始");
				btnState.setBackgroundResource(R.drawable.ic_btn_gray);
			}
			ImageLoaderUtil.load(context, ImageType.NETWORK, valueSpikeBean.picUrl, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, ivLogo);
		}
	}

}
