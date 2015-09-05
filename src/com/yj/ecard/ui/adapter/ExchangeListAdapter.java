/**   
* @Title: ExchangeListAdapter.java
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
import com.yj.ecard.publics.model.ExchangeBean;
import com.yj.ecard.ui.activity.main.exchange.ExchangeDetailActivity;
import com.yj.ecard.ui.activity.order.OrderDetailActivity;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: ExchangeListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class ExchangeListAdapter extends ArrayListBaseAdapter<ExchangeBean> {

	public ExchangeListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ExchangeListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_exchange, null);
			holder = new ExchangeListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ExchangeListViewHolder) convertView.getTag();
		}

		final ExchangeBean productBean = mList.get(position);
		holder.initData(context, productBean);

		holder.exchangeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (productBean.canExchange) {
					// 旧版支付
					/*Intent intent = new Intent(context, ExchangeAddressActivity.class);
					intent.putExtra("id", productBean.id);
					context.startActivity(intent);*/

					// 新版支付
					Intent intent = new Intent(context, OrderDetailActivity.class);
					intent.putExtra("id", productBean.id);
					intent.putExtra("shopName", productBean.companyName);
					intent.putExtra("productName", productBean.title);
					intent.putExtra("price", productBean.price);
					intent.putExtra("imgUrl", productBean.picUrl);
					intent.putExtra("orderType", 2); // 1=秒杀订单，2=兑换订单
					context.startActivity(intent);
				}
			}
		});

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, ExchangeDetailActivity.class);
				intent.putExtra("id", productBean.id);
				intent.putExtra("title", productBean.title);
				intent.putExtra("canExchange", productBean.canExchange);
				intent.putExtra("shopName", productBean.companyName);
				intent.putExtra("price", productBean.price);
				intent.putExtra("imgUrl", productBean.picUrl);
				context.startActivity(intent);
			}
		});

		return convertView;
	}
}
