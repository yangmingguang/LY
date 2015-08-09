/**   
* @Title: AutoHeightViewPager.java
* @Package com.yj.ecard.ui.views.viewpager
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-14 下午10:20:49
* @version V1.0   
*/

package com.yj.ecard.ui.views.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
* @ClassName: AutoHeightViewPager
* @Description: 自适应子View高度的viewPager
* @author YangMingGuang
* @date 2015-6-14 下午10:20:49
* 
*/

public class AutoHeightViewPager extends ViewPager {

	public AutoHeightViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int height = 0;
		// 下面遍历所有child的高度
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			int h = child.getMeasuredHeight();
			// 采用最大的view的高度
			if (h > height) {
				height = h;
			}
		}

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
