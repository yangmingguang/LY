/**   
* @Title: Tab16Fragment.java
* @Package com.yj.ecard.ui.activity.main.home.valuespike
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-5 下午11:00:27
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.valuespike;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yj.ecard.publics.utils.Constan;

/**
* @ClassName: Tab16Fragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-5 下午11:00:27
* 
*/

public class Tab16Fragment extends Tab9Fragment {

	public static Fragment newInstance(Bundle bundle) {
		Tab16Fragment fragment = new Tab16Fragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getSortId() {
		return Constan.TAB_16;
	}
}
