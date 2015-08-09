/**   
* @Title: MyTextView.java
* @Package com.yj.ecard.ui.views.custom
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-15 上午11:12:51
* @version V1.0   
*/

package com.yj.ecard.ui.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
* @ClassName: MyTextView
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-15 上午11:12:51
* 
*/

public class MyTextView extends TextView {

	public MyTextView(Context context) {
		super(context);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
