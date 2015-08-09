/**   
* @Title: IncomeListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.IncomeBean;

/**
* @ClassName: IncomeListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class IncomeListViewHolder {

	private boolean hasInited;
	private TextView tvTitle, tvTime, tvAmount;

	public IncomeListViewHolder(View view) {
		if (view != null) {
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvTime = (TextView) view.findViewById(R.id.tv_time);
			tvAmount = (TextView) view.findViewById(R.id.tv_amount);
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
	public void initData(Context context, IncomeBean incomeBean) {
		if (hasInited) {
			tvTitle.setText(incomeBean.title);
			tvTime.setText(incomeBean.addTime);
			tvAmount.setText("￥" + incomeBean.amount);
		}
	}
}
