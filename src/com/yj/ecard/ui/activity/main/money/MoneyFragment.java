/**   
* @Title: MoneyFragment.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:46:44
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.money;

import net.youmi.android.offers.OffersManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.ui.activity.base.BaseFragment;

/**
* @ClassName: MoneyFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:46:44
* 
*/

public class MoneyFragment extends BaseFragment {

	private View rootView;
	private boolean inited = false;

	public static Fragment newInstance(Bundle bundle) {
		MoneyFragment fragment = new MoneyFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_money, null);
		initViews();
		return rootView;
	}

	/**
	 * 
	* @Title: show 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void showWall() {
		if (inited) {
			// 调用方式一：直接打开全屏积分墙
			String userName = UserManager.getInstance().getUserName(context);
			OffersManager.getInstance(context).setCustomUserId(userName);
			OffersManager.setUsingServerCallBack(true);
			OffersManager.getInstance(context).showOffersWall();
		}
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		rootView.findViewById(R.id.btn_imageview).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 调用方式一：直接打开全屏积分墙
				String userName = UserManager.getInstance().getUserName(context);
				OffersManager.getInstance(context).setCustomUserId(userName);
				OffersManager.setUsingServerCallBack(true);
				OffersManager.getInstance(context).showOffersWall();
			}
		});
		inited = true;
		showWall();
	}

}
