/**   
* @Title: CityListViewHolder.java
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
import com.yj.ecard.publics.model.CityBean;

/**
* @ClassName: CityListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class CityListViewHolder {

	private boolean hasInited;
	public TextView tvLetter, tvTitle;

	public CityListViewHolder(View view) {
		if (view != null) {
			tvLetter = (TextView) view.findViewById(R.id.tv_letter);
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cityBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, CityBean cityBean) {
		if (hasInited) {
			tvLetter.setText(cityBean.getLetter());
			tvTitle.setText(cityBean.getCityName());
		}
	}

}
