/**   
* @Title: MessageListAdapter.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.model.MessageBean;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: MessageListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class MessageListAdapter extends ArrayListBaseAdapter<MessageBean> {

	public MessageListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MessageListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_message_item, null);
			holder = new MessageListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (MessageListViewHolder) convertView.getTag();
		}

		final MessageBean messageBean = mList.get(position);
		holder.initData(context, messageBean);

		holder.btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonManager.getInstance().deleteMessageData(context, MessageListAdapter.this, messageBean);
			}
		});

		return convertView;
	}
}
