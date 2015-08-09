/**   
* @Title: ValueSpikeListAdapter.java
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
import com.yj.ecard.publics.model.ValueSpikeBean;
import com.yj.ecard.ui.activity.main.home.valuespike.ValueSpikeDetailActivity;
import com.yj.ecard.ui.activity.main.home.valuespike.ValueSpikeExchangeActivity;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: ValueSpikeListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class ValueSpikeListAdapter extends ArrayListBaseAdapter<ValueSpikeBean> {

	public ValueSpikeListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ValueSpikeListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_value_spike, null);
			holder = new ValueSpikeListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ValueSpikeListViewHolder) convertView.getTag();
		}

		final ValueSpikeBean valueSpikeBean = mList.get(position);
		holder.initData(context, valueSpikeBean);

		holder.btnState.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (valueSpikeBean.canbuy == 1 && valueSpikeBean.isStart) {
					Intent intent = new Intent(context, ValueSpikeExchangeActivity.class);
					intent.putExtra("id", valueSpikeBean.id);
					context.startActivity(intent);
				}
			}
		});

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, ValueSpikeDetailActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", valueSpikeBean.id);
				intent.putExtra("canBuy", valueSpikeBean.canbuy);
				intent.putExtra("isStart", valueSpikeBean.isStart);
				context.startActivity(intent);
			}
		});

		return convertView;
	}
}
