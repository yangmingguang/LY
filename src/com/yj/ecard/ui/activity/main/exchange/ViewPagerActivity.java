package com.yj.ecard.ui.activity.main.exchange;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.model.ExchangePicBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.Utils;

/**
 * @ClassName: ViewPagerActivity
 * @Desciption: 查看大图 viewPager
 * @Author: mingguang.yang
 * @Date: 2014-1-21 上午8:35:20
 */

public class ViewPagerActivity extends Activity {

	private ViewPager mPager;
	private ViewPagerAdapter mAdapter;
	private ArrayList<ExchangePicBean> picList;
	private int index = 0;
	private ImageView[] imageViews;
	private ImageView imageView;
	private Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_viewpager);
		initViews();
	}

	private void initViews() {
		index = getIntent().getIntExtra("picIndex", 0);
		picList = getIntent().getParcelableArrayListExtra("picList");

		mPager = (ViewPager) findViewById(R.id.viewpager);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);

		// 对imageviews进行填充
		imageViews = new ImageView[picList.size()];
		// 小图标
		for (int i = 0; i < picList.size(); i++) {
			LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			// 设置每个小圆点距离左边的间距
			margin.setMargins(15, 0, 0, 0);
			imageView = new ImageView(this);
			// 设置每个小圆点的宽高
			imageView.setLayoutParams(new LayoutParams(15, 15));
			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i].setBackgroundResource(R.drawable.ic_page_indicator_unfocused);
			} else {
				// 其他图片都设置未选中状态
				imageViews[i].setBackgroundResource(R.drawable.ic_page_indicator_focused);
			}
			group.addView(imageViews[i], margin);
		}

		mAdapter = new ViewPagerAdapter();
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new ViewPageChangeListener());
		// 设置当前显示的图片（下标值），默认从0开始...注意，这句话必须在mPager.setAdapter(mAdapter)之后才起作用
		mPager.setCurrentItem(index);
	}

	private final class ViewPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0].setBackgroundResource(R.drawable.ic_page_indicator_unfocused);
				if (arg0 != i) {
					imageViews[i].setBackgroundResource(R.drawable.ic_page_indicator_focused);
				}
			}

		}

	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return picList.size();
		}

		@Override
		public Object instantiateItem(View collection, int position) {

			View rootView = getLayoutInflater().inflate(R.layout.viewpager_product_picture, null);
			final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageview);

			String path = picList.get(position).getPicUrl();

			ImageLoaderUtil.load(context, ImageType.NETWORK, path, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, imageView);

			((ViewPager) collection).addView(rootView, 0);

			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// 点击关闭当前activity
					ViewPagerActivity.this.finish();
				}
			});

			return rootView;
		}

		@Override
		public void destroyItem(View collection, int position, Object view) {
			try {
				ImageView iv = (ImageView) ((View) view).findViewById(R.id.imageview);
				Utils.recycleImageView(iv);
			} catch (Exception e) {
				e.printStackTrace();
			}
			((ViewPager) collection).removeView((View) view);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((View) object);
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		try {
			int count = mPager.getChildCount();
			for (int i = 0; i < count; i++) {
				ImageView iv = (ImageView) mPager.getChildAt(i).findViewById(R.id.imageview);
				ImageLoaderUtil.cancleDisplay(iv, this);
				Utils.recycleImageView(iv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}

}