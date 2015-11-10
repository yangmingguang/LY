/**   
* @Title: MessageListViewHolder.java
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
import com.yj.ecard.publics.model.MessageBean;

/**
* @ClassName: MessageListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class MessageListViewHolder {

	private boolean hasInited;
	public TextView tvTitle, tvContent, btnDelete;

	public MessageListViewHolder(View view) {
		if (view != null) {
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			tvContent = (TextView) view.findViewById(R.id.tv_content);
			btnDelete = (TextView) view.findViewById(R.id.btn_delete);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param messageBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, MessageBean messageBean) {
		if (hasInited) {
			tvTitle.setText(messageBean.title);
			tvContent.setText(messageBean.content);
		}
	}

}
