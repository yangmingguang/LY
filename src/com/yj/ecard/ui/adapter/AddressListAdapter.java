/**   
* @Title: AddressListAdapter.java
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
import com.yj.ecard.business.address.AddressManager;
import com.yj.ecard.publics.model.AddressBean;
import com.yj.ecard.ui.activity.order.AddAddressActivity;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: AddressListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class AddressListAdapter extends ArrayListBaseAdapter<AddressBean> {

	public AddressListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AddressListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_address_item, null);
			holder = new AddressListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (AddressListViewHolder) convertView.getTag();
		}

		final AddressBean addressBean = mList.get(position);
		holder.initData(context, addressBean);

		holder.btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddressManager.getInstance().deleteAddressData(context, AddressListAdapter.this, addressBean);
			}
		});

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, AddAddressActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", addressBean.id);
				context.startActivity(intent);
			}
		});

		return convertView;
	}
}
