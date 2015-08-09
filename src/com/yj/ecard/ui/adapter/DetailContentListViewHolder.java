/**   
* @Title: DetailContentListViewHolder.java
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

/**
* @ClassName: DetailContentListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class DetailContentListViewHolder {

	private boolean hasInited;
	private TextView tvContent;

	public DetailContentListViewHolder(View view) {
		if (view != null) {
			tvContent = (TextView) view.findViewById(R.id.tv_content);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param content
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, String content) {
		if (hasInited) {
			tvContent.setText(content);
		}
	}

}
