/**   
* @Title: ContentFragment.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:44:34
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.yj.ecard.R;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.activity.main.business.BusinessFragment;
import com.yj.ecard.ui.activity.main.exchange.ExchangeFragment;
import com.yj.ecard.ui.activity.main.home.HomeFragment;
import com.yj.ecard.ui.activity.main.me.MeFragment;
import com.yj.ecard.ui.activity.main.money.MoneyFragment;

/**
* @ClassName: ContentFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:44:34
* 
*/

public class ContentFragment extends BaseFragment {

	private Fragment mCurrentFragment, homeFragement, businessFragment, meFragment, moneyFragment, exchangeFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fm_drawer_content, null);
		initViews(rootView);
		return rootView;
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param rootView    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews(View rootView) {
		final MainActivity mainActivity = (MainActivity) getActivity();
		RadioGroup mRgHost = (RadioGroup) rootView.findViewById(R.id.mf_tab_rg);
		mRgHost.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				// 首页
				case R.id.mf_home_tab_rb:
					if (null == homeFragement)
						homeFragement = HomeFragment.newInstance(null);
					switchFragments(homeFragement);
					mainActivity.showMenuItem(true, true);
					break;

				// 商家
				case R.id.mf_apps_tab_rb:
					if (null == businessFragment)
						businessFragment = BusinessFragment.newInstance(null);
					switchFragments(businessFragment);
					mainActivity.showMenuItem(true, false);
					((BusinessFragment) businessFragment).reloadData();
					break;

				// 我的
				case R.id.mf_games_tab_rb:
					if (null == meFragment)
						meFragment = MeFragment.newInstance(null);
					switchFragments(meFragment);
					mainActivity.showMenuItem(false, false);
					break;

				// 赚钱
				case R.id.mf_ringtones_tab_rb:
					if (null == moneyFragment)
						moneyFragment = MoneyFragment.newInstance(null);
					switchFragments(moneyFragment);
					((MoneyFragment) moneyFragment).showWall();
					mainActivity.showMenuItem(false, false);
					break;

				// 兑换
				case R.id.mf_wallpaper_tab_rb:
					if (null == exchangeFragment)
						exchangeFragment = ExchangeFragment.newInstance(null);
					switchFragments(exchangeFragment);
					mainActivity.showMenuItem(false, false);
					break;
				}
			}
		});
		mRgHost.getChildAt(0).performClick();
	}

	/**
	 * 
	* @Title: switchFragments 
	* @Description: 切换当前fragment
	* @param @param homeFragement    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void switchFragments(Fragment fragment) {
		FragmentManager manager = getChildFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		if (mCurrentFragment == null && !fragment.isAdded()) {
			ft.add(R.id.fsh_content_fl, fragment, fragment.getClass().toString()).commitAllowingStateLoss();
		} else {
			if (fragment.isAdded()) {
				ft.hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
			} else {
				ft.hide(mCurrentFragment).add(R.id.fsh_content_fl, fragment, fragment.getClass().toString())
						.commitAllowingStateLoss();
			}
		}
		mCurrentFragment = fragment;
	}

}
