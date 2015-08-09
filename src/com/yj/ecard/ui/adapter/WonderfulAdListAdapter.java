/**   
* @Title: WonderfulAdListAdapter.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.WonderfulAdBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.ui.activity.main.home.wonderful.BombAdDetailActivity;
import com.yj.ecard.ui.activity.main.home.wonderful.VideoAdDetailActivity;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: WonderfulAdListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class WonderfulAdListAdapter extends ArrayListBaseAdapter<WonderfulAdBean> {

	public WonderfulAdListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WonderfulAdListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_wonderful_ad, null);
			holder = new WonderfulAdListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (WonderfulAdListViewHolder) convertView.getTag();
		}

		final WonderfulAdBean wonderfulAdBean = mList.get(position);
		holder.initData(context, wonderfulAdBean);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = null;
				switch (wonderfulAdBean.sortId) {
				case Constan.TAB_BOMB:
				case Constan.TAB_DRAW:
					intent = new Intent(context, BombAdDetailActivity.class);
					break;

				case Constan.TAB_VIDEO:
					intent = new Intent(context, VideoAdDetailActivity.class);
					break;
				}
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", wonderfulAdBean.id);
				intent.putExtra("sortId", wonderfulAdBean.sortId);
				context.startActivity(intent);
			}
		});

		return convertView;
	}
}
