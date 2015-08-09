/**   
* @Title: AboutActivity.java
* @Package com.yj.ecard.ui.activity.main.slidingmenu
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-5 下午3:57:20
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.slidingmenu;

import android.os.Bundle;

import com.yj.ecard.R;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.views.justify.TextViewEx;

/**
* @ClassName: AboutActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-5 下午3:57:20
* 
*/

public class AboutActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about);

		// 格式化字体设置
		TextViewEx tvAbout = (TextViewEx) findViewById(R.id.tv_about);
		tvAbout.setText(getString(R.string.about_content), true);
	}

}
