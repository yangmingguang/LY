/**   
* @Title: FieldActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-19 下午11:37:49
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import java.util.ArrayList;
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
import com.yj.ecard.publics.model.OptionBean;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.adapter.FieldListAdapter;

/**
* @ClassName: FieldActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-19 下午11:37:49
* 
*/

public class FieldActivity extends Activity implements OnClickListener {

	private String field;
	private GridView mGridView;
	private FieldListAdapter mAdapter;
	private List<OptionBean> mList = new ArrayList<OptionBean>();
	private final int[] btns = { R.id.btn_cancel, R.id.btn_confirm };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_field);
		initView();
		initData();
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
		field = getIntent().getStringExtra("field");
		// dialog样式默认不全屏，要设置全屏显示
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		mAdapter = new FieldListAdapter(this);
		mGridView = (GridView) findViewById(R.id.gridview);
		mGridView.setAdapter(mAdapter);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initData() {

		int[] ids = getResources().getIntArray(R.array.fieldId);
		String[] titles = getResources().getStringArray(R.array.fieldStr);

		for (int i = 0; i < ids.length; i++) {
			OptionBean bean = new OptionBean(ids[i], titles[i]);
			mList.add(bean);
		}
		mAdapter.setList(mList);
		mAdapter.initState(field);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			CommonManager.getInstance().setFieldId(this, field);
			CommonManager.getInstance().setFieldText(this, Utils.getFieldStr(field));
			CommonManager.getInstance().setOnClickable(this, true);
			finish();
			break;

		case R.id.btn_confirm:
			if (mAdapter.getSize() < 5) {
				ToastUtil.show(this, "至少选择5项！", ToastUtil.LENGTH_SHORT);
			} else {
				field = mAdapter.getFields();
				CommonManager.getInstance().setFieldId(this, field);
				CommonManager.getInstance().setFieldText(this, Utils.getFieldStr(field));
				CommonManager.getInstance().setOnClickable(this, true);
				finish();
			}
			break;
		}
	}

}
