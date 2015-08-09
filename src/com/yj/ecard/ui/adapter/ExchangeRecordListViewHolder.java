/**   
* @Title: ExchangeRecordListViewHolder.java
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
import com.yj.ecard.publics.model.ExchangeRecordBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: ExchangeRecordListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class ExchangeRecordListViewHolder {

	private ImageView ivLogo;
	private boolean hasInited;
	private TextView tvTilte, tvExchangeState, tvIntroduction, ivState, tvPhone, tvExchangeTime;

	public ExchangeRecordListViewHolder(View view) {
		if (view != null) {
			tvTilte = (TextView) view.findViewById(R.id.tv_title);
			tvExchangeState = (TextView) view.findViewById(R.id.tv_exchange_state);
			tvIntroduction = (TextView) view.findViewById(R.id.tv_introduction);
			ivState = (TextView) view.findViewById(R.id.tv_state);
			tvPhone = (TextView) view.findViewById(R.id.tv_phone);
			tvExchangeTime = (TextView) view.findViewById(R.id.tv_exchange_time);
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
	public void initData(Context context, ExchangeRecordBean exchangeRecordBean) {
		if (hasInited) {
			tvTilte.setText(exchangeRecordBean.title);
			ivState.setText(exchangeRecordBean.state);
			if (exchangeRecordBean.state.equals("已发货")) {
				ivState.setBackgroundResource(R.drawable.ic_exchange_state);
			} else {
				ivState.setBackgroundResource(R.drawable.ic_unexchange_state);
			}
			tvExchangeState.setText(exchangeRecordBean.exchangeState);
			tvIntroduction.setText(exchangeRecordBean.introduction);
			tvPhone.setText("客服电话：" + exchangeRecordBean.servicePhone);
			tvExchangeTime.setText("兑换时间：" + exchangeRecordBean.exchangeTime);
			ImageLoaderUtil.load(context, ImageType.NETWORK, exchangeRecordBean.picUrl,
					R.drawable.banner_detail_default, R.drawable.banner_detail_default, ivLogo);
		}
	}

}
