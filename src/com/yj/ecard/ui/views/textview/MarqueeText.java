/**   
* @Title: MarqueeText.java
* @Package com.yj.ecard.ui.views.textview
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-18 上午9:39:27
* @version V1.0   
*/

package com.yj.ecard.ui.views.textview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
* @ClassName: MarqueeText
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-18 上午9:39:27
* 
*/

public class MarqueeText extends TextView {

	public MarqueeText(Context context) {
		super(context);
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

	}
}
