/**   
* @Title: CityListAdapter.java
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
import android.widget.SectionIndexer;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.model.CityBean;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.ui.adapter.base.ArrayListBaseAdapter;

/**
* @ClassName: CityListAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:20:14
* 
*/

public class CityListAdapter extends ArrayListBaseAdapter<CityBean> implements SectionIndexer {

	public CityListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CityListViewHolder holder;

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_city_item, null);
			holder = new CityListViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (CityListViewHolder) convertView.getTag();
		}

		final CityBean cityBean = mList.get(position);
		holder.initData(context, cityBean);

		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			holder.tvLetter.setVisibility(View.VISIBLE);
			holder.tvLetter.setText(cityBean.getLetter());
		} else {
			holder.tvLetter.setVisibility(View.GONE);
		}

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				ToastUtil.show(context, cityBean.getCityName(), ToastUtil.LENGTH_SHORT);
				// 存储城市id、名称、经纬度
				CommonManager.getInstance().setAreaId(context, cityBean.getId());
				CommonManager.getInstance().setLocationCity(context, cityBean.getCityName());
				CommonManager.getInstance().setLocationLat(context, cityBean.getLat());
				CommonManager.getInstance().setLocationLng(context, cityBean.getLng());
				((Activity) context).finish();
			}
		});

		return convertView;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	@Override
	public int getPositionForSection(int sectionIndex) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = mList.get(i).getLetter();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == sectionIndex) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	@Override
	public int getSectionForPosition(int position) {
		return mList.get(position).getLetter().charAt(0);
	}
}
