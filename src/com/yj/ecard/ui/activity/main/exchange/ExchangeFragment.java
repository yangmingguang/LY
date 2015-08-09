/**   
* @Title: ExchangeFragment.java
* @Package com.yj.ecard.ui.activity.main
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:47:04
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.exchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;
import com.yj.ecard.R;
import com.yj.ecard.publics.utils.ResourceUtil;
import com.yj.ecard.ui.activity.base.BaseFragment;

/**
* @ClassName: ExchangeFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:47:04
* 
*/

public class ExchangeFragment extends BaseFragment {

	private View rootView;
	private ViewPager mViewPager;
	private TabPageIndicator indicator;
	private ExchangeFragmentPagerAdapter mExchangeFragmentPagerAdapter;

	public static Fragment newInstance(Bundle bundle) {
		ExchangeFragment fragment = new ExchangeFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_exchange, null);
		initViewPager();
		return rootView;
	}

	/** 
	* @Title: initViewPager 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViewPager() {
		String[] titles = { ResourceUtil.getString(context, R.string.tab_overflow),
				ResourceUtil.getString(context, R.string.tab_special),
				ResourceUtil.getString(context, R.string.tab_area) };
		mExchangeFragmentPagerAdapter = new ExchangeFragmentPagerAdapter(this.getChildFragmentManager(), titles);

		mViewPager = (ViewPager) rootView.findViewById(R.id.vpi_content_pager);
		mViewPager.setAdapter(mExchangeFragmentPagerAdapter);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
			}
		});
		indicator = (TabPageIndicator) rootView.findViewById(R.id.vpi_indicator);
		indicator.setViewPager(mViewPager);
	}

	/**
	 * 
	* @ClassName: ExchangeFragmentPagerAdapter
	* @Description: TODO(这里用一句话描述这个类的作用)
	* @author YangMingGuang
	* @date 2015-6-5 下午10:29:18
	*
	 */
	public class ExchangeFragmentPagerAdapter extends FragmentPagerAdapter {

		String titles[];

		public ExchangeFragmentPagerAdapter(FragmentManager fm, String[] titles) {
			super(fm);
			this.titles = titles;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = TabOverflowFragment.newInstance(null);
				break;

			case 1:
				fragment = TabSpecialFragment.newInstance(null);
				break;

			case 2:
				fragment = TabAreaFragment.newInstance(null);
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}
	}

}
