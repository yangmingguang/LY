/**   
* @Title: ExchangeGiftActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午10:37:42
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;
import com.yj.ecard.R;
import com.yj.ecard.publics.utils.ResourceUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.exchange.TabAreaFragment;
import com.yj.ecard.ui.activity.main.exchange.TabOverflowFragment;
import com.yj.ecard.ui.activity.main.exchange.TabSpecialFragment;

/**
* @ClassName: ExchangeGiftActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午10:37:42
* 
*/

public class ExchangeGiftActivity extends BaseActivity {

	private ViewPager mViewPager;
	private TabPageIndicator indicator;
	private WonderfulAdPagerAdapter mWonderfulAdPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exchange_gift);
		initViewPager();
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
		mWonderfulAdPagerAdapter = new WonderfulAdPagerAdapter(this.getSupportFragmentManager(), titles);

		mViewPager = (ViewPager) findViewById(R.id.vpi_content_pager);
		mViewPager.setAdapter(mWonderfulAdPagerAdapter);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
			}
		});
		indicator = (TabPageIndicator) findViewById(R.id.vpi_indicator);
		indicator.setViewPager(mViewPager);
	}

	/**
	 * 
	* @ClassName: WonderfulAdPagerAdapter
	* @Description: TODO(这里用一句话描述这个类的作用)
	* @author YangMingGuang
	* @date 2015-6-11 下午4:23:58
	*
	 */
	public class WonderfulAdPagerAdapter extends FragmentPagerAdapter {

		String titles[];

		public WonderfulAdPagerAdapter(FragmentManager fm, String[] titles) {
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
