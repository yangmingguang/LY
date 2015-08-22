/**   
* @Title: DetailProductListAdapter.java
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
import com.yj.ecard.publics.model.ProductBean;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: DetailProductListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class DetailProductListAdapter extends ArrayListBaseAdapter<ProductBean> {

	public DetailProductListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DetailProductListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_detail_product1, null);
			holder = new DetailProductListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (DetailProductListViewHolder) convertView.getTag();
		}

		final ProductBean productBean = mList.get(position);
		holder.initData(context, productBean);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				/*	Intent intent = new Intent(context, WonderfulAdDetailActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("id", wonderfulAdBean.id);
					intent.putExtra("sortId", wonderfulAdBean.sortId);
					context.startActivity(intent);*/
			}
		});

		return convertView;
	}
}
