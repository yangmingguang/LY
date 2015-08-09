/**   
* @Title: WonderfulAdActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午10:20:59
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.wonderful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;
import com.yj.ecard.R;
import com.yj.ecard.publics.utils.ResourceUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: WonderfulAdActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午10:20:59
* 
*/

public class WonderfulAdActivity extends BaseActivity {

	private ViewPager mViewPager;
	private TabPageIndicator indicator;
	private WonderfulAdPagerAdapter mWonderfulAdPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_wonderful_ad);
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
		String[] titles = { ResourceUtil.getString(context, R.string.tab_bomb_screen),
				ResourceUtil.getString(context, R.string.tab_draw_screen),
				ResourceUtil.getString(context, R.string.tab_video_screen) };
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
				fragment = TabBombFragment.newInstance(null);
				break;

			case 1:
				fragment = TabDrawFragment.newInstance(null);
				break;

			case 2:
				fragment = TabVideoFragment.newInstance(null);
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
