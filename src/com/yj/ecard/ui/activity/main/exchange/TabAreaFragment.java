/**   
* @Title: TabAreaFragment.java
* @Package com.yj.ecard.ui.activity.main.exchange
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-5 下午11:01:14
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.exchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yj.ecard.publics.utils.Constan;

/**
* @ClassName: TabAreaFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-5 下午11:01:14
* 
*/

public class TabAreaFragment extends TabOverflowFragment {

	public static Fragment newInstance(Bundle bundle) {
		TabAreaFragment fragment = new TabAreaFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getSortId() {
		return Constan.TAB_AREA;
	}

}
