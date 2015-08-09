/**   
* @Title: FieldListAdapter.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.OptionBean;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: FieldListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class FieldListAdapter extends ArrayListBaseAdapter<OptionBean> {

	private Set<Integer> numList = new HashSet<Integer>();

	public FieldListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final FieldListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_field_item, null);
			holder = new FieldListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (FieldListViewHolder) convertView.getTag();
		}

		final OptionBean optionBean = mList.get(position);
		holder.initData(context, optionBean);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (holder.cb.isChecked()) {
					holder.cb.setChecked(false);
					numList.remove(optionBean.id);
				} else {
					holder.cb.setChecked(true);
					numList.add(optionBean.id);
				}
			}
		});

		if (numList.contains(optionBean.id)) {
			holder.cb.setChecked(true);
		} else {
			holder.cb.setChecked(false);
		}

		return convertView;
	}

	/**
	 * 
	* @Title: initState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param str    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void initState(String str) {
		String[] strArray = str.split(",");
		for (int i = 0; i < strArray.length; i++) {
			numList.add(Integer.parseInt(strArray[i].toString()));
			notifyDataSetChanged();
		}
	}

	/**
	 * 
	* @Title: getFields 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getFields() {
		if (numList.size() > 1) {
			numList.remove(0);
			StringBuffer sb = new StringBuffer();
			for (int i : numList) {
				sb.append(i).append(",");
			}
			String str = sb.substring(0, sb.length() - 1);
			return str;
		} else {
			return "0";
		}
	}

	public int getSize() {
		int size = numList.size();
		if (numList.contains(0)) {
			return size - 1;
		}
		return size;
	}
}
