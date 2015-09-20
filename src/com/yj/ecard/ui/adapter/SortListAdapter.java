/**   
* @Title: SortListAdapter.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.model.SortBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: SortListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class SortListAdapter extends ArrayListBaseAdapter<SortBean> {

	private int type;
	private Activity activity;

	public SortListAdapter(Context context) {
		super(context);
	}

	public SortListAdapter(Activity activity, int type) {
		super(activity);
		this.type = type;
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SortListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_sort_item, null);
			holder = new SortListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (SortListViewHolder) convertView.getTag();
		}

		final SortBean sortBean = mList.get(position);
		holder.initData(context, sortBean, type);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				switch (type) {
				case Constan.AREA_TYPE:
					CommonManager.getInstance().setAreaSortValue(context, sortBean.id);
					break;

				case Constan.SHOP_TYPE:
					CommonManager.getInstance().setShopSortValue(context, sortBean.id);
					break;
				}
				notifyDataSetChanged();
			}
		});

		return convertView;
	}
}
