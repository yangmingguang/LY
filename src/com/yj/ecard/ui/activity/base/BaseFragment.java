/**   
* @Title: BaseFragment.java
* @Package com.yj.ecard.ui.activity.base
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:21:32
* @version V1.0   
*/

package com.yj.ecard.ui.activity.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
* @ClassName: BaseFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:21:33
* 
*/

public class BaseFragment extends Fragment {

	protected FragmentActivity context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this.getActivity();
	}
}
