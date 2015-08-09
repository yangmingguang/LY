/**   
* @Title: Utility.java
* @Package com.yj.ecard.publics.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-15 下午2:26:11
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @ClassName: Utility
 * @Desciption: Scrollview 嵌套 ListView 计算高度; 用法:在
 *              mlistView.setAdapter(mAdapter); 之后追加
 *              Utility.setListViewHeightBasedOnChildren(ListView listView)
 * @Author: mingguang.yang
 * @Date: 2014-1-15 下午8:24:19
 */
public class Utility {
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		// 限制高度
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
