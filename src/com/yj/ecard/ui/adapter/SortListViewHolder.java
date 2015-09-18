/**   
* @Title: SortListViewHolder.java
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
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.model.SortBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ResourceUtil;

/**
* @ClassName: SortListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class SortListViewHolder {

	private boolean hasInited;
	public TextView tvTitle;

	public SortListViewHolder(View view) {
		if (view != null) {
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sortBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, SortBean sortBean, int type) {
		if (hasInited) {
			tvTitle.setText(sortBean.title);

			switch (type) {
			case Constan.AREA_TYPE:
				if (sortBean.id == CommonManager.getInstance().getAreaSortValue(context)) {
					tvTitle.setTextColor(ResourceUtil.getInteger(context, R.color.red));
				} else {
					tvTitle.setTextColor(ResourceUtil.getInteger(context, R.color.black));
				}
				break;

			case Constan.SHOP_TYPE:
				if (sortBean.id == CommonManager.getInstance().getShopSortValue(context)) {
					tvTitle.setTextColor(ResourceUtil.getInteger(context, R.color.red));
				} else {
					tvTitle.setTextColor(ResourceUtil.getInteger(context, R.color.black));
				}
				break;
			}
		}
	}

}
