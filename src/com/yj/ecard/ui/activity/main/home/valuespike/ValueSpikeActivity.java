/**   
* @Title: ValueSpikeActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午10:37:42
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.valuespike;

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
* @ClassName: ValueSpikeActivity
* @Description: 超值秒杀
* @author YangMingGuang
* @date 2015-5-24 下午10:37:42
* 
*/

public class ValueSpikeActivity extends BaseActivity {

	private ViewPager mViewPager;
	private TabPageIndicator indicator;
	private ValueSpikePagerAdapter mValueSpikePagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_valuespike);
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
		String[] titles = { ResourceUtil.getString(context, R.string.tab_9),
				ResourceUtil.getString(context, R.string.tab_10), ResourceUtil.getString(context, R.string.tab_14),
				ResourceUtil.getString(context, R.string.tab_16) };
		mValueSpikePagerAdapter = new ValueSpikePagerAdapter(this.getSupportFragmentManager(), titles);

		mViewPager = (ViewPager) findViewById(R.id.vpi_content_pager);
		mViewPager.setAdapter(mValueSpikePagerAdapter);
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
	* @ClassName: ValueSpikePagerAdapter
	* @Description: TODO(这里用一句话描述这个类的作用)
	* @author YangMingGuang
	* @date 2015-6-11 下午4:23:58
	*
	 */
	public class ValueSpikePagerAdapter extends FragmentPagerAdapter {

		String titles[];

		public ValueSpikePagerAdapter(FragmentManager fm, String[] titles) {
			super(fm);
			this.titles = titles;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = Tab9Fragment.newInstance(null);
				break;

			case 1:
				fragment = Tab10Fragment.newInstance(null);
				break;

			case 2:
				fragment = Tab14Fragment.newInstance(null);
				break;

			case 3:
				fragment = Tab16Fragment.newInstance(null);
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
