/**   
* @Title: FieldListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.OptionBean;

/**
* @ClassName: FieldListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class FieldListViewHolder {

	public CheckBox cb;
	private TextView tvTitle;
	private boolean hasInited;

	public FieldListViewHolder(View view) {
		if (view != null) {
			cb = (CheckBox) view.findViewById(R.id.checkBox);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param optionBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, OptionBean optionBean) {
		if (hasInited) {
			tvTitle.setText(optionBean.title);
		}
	}

}
