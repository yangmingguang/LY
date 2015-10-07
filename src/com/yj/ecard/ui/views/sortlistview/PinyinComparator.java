package com.yj.ecard.ui.views.sortlistview;

import java.util.Comparator;

import com.yj.ecard.publics.model.CityBean;

/**
 * 
* @ClassName: PinyinComparator
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-7 上午8:54:41
*
 */
public class PinyinComparator implements Comparator<CityBean> {

	public int compare(CityBean o1, CityBean o2) {
		if (o1.getLetter().equals("@") || o2.getLetter().equals("#")) {
			return -1;
		} else if (o1.getLetter().equals("#") || o2.getLetter().equals("@")) {
			return 1;
		} else {
			return o1.getLetter().compareTo(o2.getLetter());
		}
	}

}
