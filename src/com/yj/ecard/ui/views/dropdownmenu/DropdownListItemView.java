package com.yj.ecard.ui.views.dropdownmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yj.ecard.R;

/**
 * 
* @ClassName: DropdownListItemView
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-26 下午3:22:40
*
 */
public class DropdownListItemView extends TextView {
	public DropdownListItemView(Context context) {
		this(context, null);
	}

	public DropdownListItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DropdownListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void bind(CharSequence text, boolean checked) {
		setText(text);
		if (checked) {
			Drawable icon = getResources().getDrawable(R.drawable.ic_task_status_list_check);
			setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
		} else {
			setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
	}

}
