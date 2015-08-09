/**   
* @Title: ArrayListBaseAdapter.java
* @Package com.yj.ecard.ui.adapter.base
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:17:32
* @version V1.0   
*/

package com.yj.ecard.ui.adapter.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
* @ClassName: ArrayListBaseAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:17:32
* 
*/

public abstract class ArrayListBaseAdapter<T> extends BaseAdapter {

	protected List<T> mList;
	protected Context context;
	protected ListView mListView;
	protected LayoutInflater inflater;

	public ArrayListBaseAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (mList != null)
			return mList.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	abstract public View getView(int position, View convertView, ViewGroup parent);

	public void setList(List<T> list) {
		this.mList = list;
		notifyDataSetChanged();
	}

	public void setListNotnotify(List<T> list) {
		this.mList = list;
	}

	public List<T> getList() {
		return mList;
	}

	public void addItem(T t) {
		mList.add(t);
		notifyDataSetChanged();
	}

	public void removeItem(T t) {
		mList.remove(t);
		notifyDataSetChanged();
	}

	public void setList(T[] list) {
		ArrayList<T> arrayList = new ArrayList<T>(list.length);
		for (T t : list) {
			arrayList.add(t);
		}
		setList(arrayList);
	}

	public ListView getListView() {
		return mListView;
	}

	public void setListView(ListView listView) {
		mListView = listView;
	}
}
