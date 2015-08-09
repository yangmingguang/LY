/**   
* @Title: MyPreferentialListAdapter.java
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
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.model.MyPreferentialBean;
import com.yj.ecard.ui.activity.main.home.preferential.PreferentialDetailActivity;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: MyPreferentialListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class MyPreferentialListAdapter extends ArrayListBaseAdapter<MyPreferentialBean> {

	public MyPreferentialListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyPreferentialListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_my_preferential, null);
			holder = new MyPreferentialListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (MyPreferentialListViewHolder) convertView.getTag();
		}

		final MyPreferentialBean myPreferentialBean = mList.get(position);
		holder.initData(context, myPreferentialBean);

		holder.btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MeTabManager.getInstance().deletePreferentialData(context, MyPreferentialListAdapter.this,
						myPreferentialBean);
			}
		});

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, PreferentialDetailActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", myPreferentialBean.id);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

}
