/**   
* @Title: PopSortActivity.java
* @Package com.yj.ecard.ui.activity.main.business
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-9-18 下午11:44:53
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.model.SortBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.ui.adapter.SortListAdapter;

/**
* @ClassName: PopSortActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-9-18 下午11:44:53
* 
*/

public class PopSortActivity extends Activity {

	private GridView mGridView1, mGridView2;
	private SortListAdapter mAdapter1, mAdapter2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_sort_view);
		// dialog样式默认不全屏，要设置全屏显示
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		initView();
	}

	// 实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		CommonManager.getInstance().setAreaClick(this, false);
		CommonManager.getInstance().setSortClick(this, false);
		List<SortBean> areaList = getIntent().getParcelableArrayListExtra("areaList");
		List<SortBean> shopList = getIntent().getParcelableArrayListExtra("shopList");

		mAdapter1 = new SortListAdapter(this, Constan.AREA_TYPE);
		mAdapter2 = new SortListAdapter(this, Constan.SHOP_TYPE);
		mGridView1 = (GridView) findViewById(R.id.gv_area);
		mGridView2 = (GridView) findViewById(R.id.gv_shop);
		mGridView1.setAdapter(mAdapter1);
		mGridView2.setAdapter(mAdapter2);

		mAdapter1.setList(areaList);
		mAdapter2.setList(shopList);

		findViewById(R.id.btn_confirm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
