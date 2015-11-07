/**   
* @Title: SysMessageFragment.java
* @Package com.yj.ecard.ui.activity.main.home.valuespike
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-5 下午11:00:27
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
* @ClassName: SysMessageFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-5 下午11:00:27
* 
*/

public class SysMessageFragment extends UserMessageFragment {

	public static Fragment newInstance(Bundle bundle) {
		SysMessageFragment fragment = new SysMessageFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}
}
