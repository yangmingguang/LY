/**   
* @Title: BusinessDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.business
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午6:10:28
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

import java.util.List;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.viewpagerindicator.TabPageIndicator;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.main.BusinessTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.BusinessDetailResponse;
import com.yj.ecard.publics.model.EnvironmentBean;
import com.yj.ecard.publics.model.ProductBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.ResourceUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.views.scrollview.OverScrollableScrollView;

/**
* @ClassName: BusinessDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午6:10:28
* 
*/

public class BusinessDetailActivity extends BaseActivity implements OnClickListener {

	private String content;
	private String phoneNumber;
	private List<ProductBean> productList;
	private List<EnvironmentBean> environmentList;

	private ImageView ivLogo;
	private View loadingView;
	private BusinessDetailResponse response;
	private TextView tvTitle, tvPhone, tvAddress;

	private ViewPager mViewPager;
	private TabPageIndicator indicator;
	private DetailFragmentPagerAdapter mDetailFragmentPagerAdapter;
	private Fragment mTabBriefFragment, mTabProductFragment, mTabEnvironmentFragment;

	private final int[] btns = { R.id.btn_phone_ll };

	private View mRoot;
	private OverScrollableScrollView mScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_business_detail);
		initViewPager();
		initViews();
		loadAllData();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvPhone = (TextView) findViewById(R.id.tv_phone);
		tvAddress = (TextView) findViewById(R.id.tv_address);
		loadingView = findViewById(R.id.l_loading_rl);

		mScrollView = (OverScrollableScrollView) findViewById(R.id.ll_content);
		final View root = findViewById(R.id.root);
		ViewTreeObserver vto2 = root.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				root.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				//int height = root.getHeight();
				initViewPagerHeight();
			}
		});
		mRoot = root;

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/**
	 * 
	* @Title: initViewPagerHeight 
	* @Description: 初始化viewpager高度
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void initViewPagerHeight() {
		// TODO Auto-generated method stub
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
		params.height = mRoot.getHeight() - indicator.getHeight();
		mViewPager.setLayoutParams(params);
	}

	/** 
	* @Title: initViewPager 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViewPager() {

		String[] titles = { ResourceUtil.getString(context, R.string.tab_brief),
				ResourceUtil.getString(context, R.string.tab_product),
				ResourceUtil.getString(context, R.string.tab_environment) };
		mDetailFragmentPagerAdapter = new DetailFragmentPagerAdapter(this.getSupportFragmentManager(), titles);

		mViewPager = (ViewPager) findViewById(R.id.vpi_content_pager);
		mViewPager.setAdapter(mDetailFragmentPagerAdapter);
		mViewPager.setOffscreenPageLimit(3);

		indicator = (TabPageIndicator) findViewById(R.id.vpi_indicator);
		indicator.setViewPager(mViewPager);
		indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				switchFragment(position);
				switch (position) {
				case 0:
					if (null != mTabBriefFragment)
						mScrollView.setController((TabBriefFragment) mTabBriefFragment);
					break;

				case 1:
					if (null != mTabProductFragment)
						mScrollView.setController((TabProductFragment) mTabProductFragment);
					break;

				case 2:
					if (null != mTabEnvironmentFragment)
						mScrollView.setController((TabEnvironmentFragment) mTabEnvironmentFragment);
					break;
				}
			}
		});
	}

	/**
	 * 重新设置viewPager高度
	 * 
	 * @param position
	 */
	public void resetViewPagerHeight(int position) {
		View child = mViewPager.getChildAt(position);
		if (child != null) {
			child.measure(0, 0);
			int h = child.getMeasuredHeight();
			LinearLayout.LayoutParams params = (LayoutParams) mViewPager.getLayoutParams();
			params.height = h + 50;
			mViewPager.setLayoutParams(params);
		}
	}

	/**
	 * 
	* @ClassName: DetailFragmentPagerAdapter
	* @Description: TODO(这里用一句话描述这个类的作用)
	* @author YangMingGuang
	* @date 2015-6-5 下午10:29:18
	*
	 */
	public class DetailFragmentPagerAdapter extends FragmentPagerAdapter {

		String titles[];

		public DetailFragmentPagerAdapter(FragmentManager fm, String[] titles) {
			super(fm);
			this.titles = titles;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = TabBriefFragment.newInstance(null);
				mTabBriefFragment = (TabBriefFragment) fragment;
				break;

			case 1:
				fragment = TabProductFragment.newInstance(null);
				mTabProductFragment = (TabProductFragment) fragment;
				break;

			case 2:
				fragment = TabEnvironmentFragment.newInstance(null);
				mTabEnvironmentFragment = (TabEnvironmentFragment) fragment;
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

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		int id = getIntent().getIntExtra("id", 0);
		double latitude = Double.parseDouble(CommonManager.getInstance().getLocationlat(context));
		double longitude = Double.parseDouble(CommonManager.getInstance().getLocationlng(context));
		BusinessTabManager.getInstance().getBusinessDetailData(context, handler, id, latitude, longitude);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case MeTabManager.onSuccess:
				response = (BusinessDetailResponse) msg.obj;
				setParams();
				break;

			case MeTabManager.onEmpty:

				break;

			case MeTabManager.onFailure:

				break;
			}
			return true;
		}
	});

	/**
	 * 
	* @Title: setParams 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param businessDetailResponse    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void setParams() {
		phoneNumber = response.phone;
		content = response.introduction;
		productList = response.productList;
		environmentList = response.envirList;

		tvTitle.setText(response.merName);
		tvPhone.setText(phoneNumber);
		tvAddress.setText(response.address);
		ImageLoaderUtil.load(context, ImageType.NETWORK, response.picUrl, R.drawable.banner_detail_default,
				R.drawable.banner_detail_default, ivLogo);

		switchFragment(0);

		// 显示内容
		mScrollView.setVisibility(View.VISIBLE);
		loadingView.setVisibility(View.GONE);
	}

	/** 
	* @Title: switchFragment 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/

	private void switchFragment(int index) {
		switch (index) {
		case 0:
			if (null != mTabBriefFragment && null != content) {
				((TabBriefFragment) mTabBriefFragment).setData(mScrollView, content);
			}
			break;

		case 1:
			if (null != mTabProductFragment && null != productList) {
				((TabProductFragment) mTabProductFragment).setData(productList);
			}
			break;

		case 2:
			if (null != mTabEnvironmentFragment && null != environmentList) {
				((TabEnvironmentFragment) mTabEnvironmentFragment).setData(environmentList);
			}
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_phone_ll:
			if (null != phoneNumber)
				Utils.telCall(context, phoneNumber);
			break;
		}
	}
}
