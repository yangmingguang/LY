/**   
* @Title: BusinessListAdapter.java
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
import com.yj.ecard.publics.model.BusinessBean;
import com.yj.ecard.ui.activity.main.business.BusinessDetailActivity1;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: BusinessListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class BusinessListAdapter extends ArrayListBaseAdapter<BusinessBean> {

	public BusinessListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BusinessListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_business, null);
			holder = new BusinessListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (BusinessListViewHolder) convertView.getTag();
		}

		final BusinessBean businessBean = mList.get(position);
		holder.initData(context, businessBean);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, BusinessDetailActivity1.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", businessBean.id);
				context.startActivity(intent);
			}
		});

		return convertView;
	}
}
