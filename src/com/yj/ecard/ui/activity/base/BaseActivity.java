/**   
* @Title: BaseActivity.java
* @Package com.yj.ecard.ui.activity.base
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午2:18:09
* @version V1.0   
*/

package com.yj.ecard.ui.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
* @ClassName: BaseActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午2:18:09
* 
*/

public class BaseActivity extends ActionBarActivity {

	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		initActionBar();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	* @Title: initActionBar 
	* @Description: Enable ActionBar appIcon to behave as action to toggle navDrawer
	* @return void
	 */
	private void initActionBar() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	public void setPageTitle(CharSequence title) {
		getSupportActionBar().setTitle(title);
	}

	public void setPageTitle(int titleId) {
		getSupportActionBar().setTitle(titleId);
	}

}
