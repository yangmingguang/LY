/**   
* @Title: FriendsListAdapter.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.FriendsBean;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: FriendsListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class FriendsListAdapter extends ArrayListBaseAdapter<FriendsBean> {

	public FriendsListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FriendsListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_friends, null);
			holder = new FriendsListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (FriendsListViewHolder) convertView.getTag();
		}

		final FriendsBean friendsBean = mList.get(position);
		holder.initData(context, friendsBean);

		return convertView;
	}
}
