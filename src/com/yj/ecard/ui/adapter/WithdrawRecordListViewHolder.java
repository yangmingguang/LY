/**   
* @Title: WithdrawRecordListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.WithdrawBean;

/**
* @ClassName: WithdrawRecordListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class WithdrawRecordListViewHolder {

	private View state;
	private boolean hasInited;
	public TextView tvName, tvCard, tvTime, tvAmount;

	public WithdrawRecordListViewHolder(View view) {
		if (view != null) {
			tvName = (TextView) view.findViewById(R.id.tv_name);
			tvCard = (TextView) view.findViewById(R.id.tv_card);
			tvTime = (TextView) view.findViewById(R.id.tv_time);
			tvAmount = (TextView) view.findViewById(R.id.tv_amount);
			state = view.findViewById(R.id.state);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param withdrawBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, WithdrawBean withdrawBean) {
		if (hasInited) {
			tvName.setText("姓名：" + withdrawBean.realName);
			tvCard.setText("卡号：" + withdrawBean.bankCardnum);
			tvTime.setText(withdrawBean.addTime);
			tvAmount.setText(Html.fromHtml("提现金额:<font color=#D00000>￥" + withdrawBean.cashAmount + "</font>"));
			if (withdrawBean.state == 1) {
				state.setBackgroundResource(R.color.state_green_color);
			} else {
				state.setBackgroundResource(R.color.state_gray_color);
			}

		}
	}
}
