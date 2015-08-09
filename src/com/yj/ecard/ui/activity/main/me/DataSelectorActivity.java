/**   
* @Title: DataSelectorActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-4 上午11:36:20
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.model.OptionBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.ui.adapter.DataSelectorAdapter;

/**
* @ClassName: DataSelectorActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-4 上午11:36:20
* 
*/

public class DataSelectorActivity extends Activity {

	private int optionCode;
	private ListView mListView;
	private DataSelectorAdapter mAdapter;
	private List<OptionBean> mList = new ArrayList<OptionBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_data_selector);
		initView();
		initParams();
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
		// dialog样式默认不全屏，要设置全屏显示
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		mListView = (ListView) findViewById(R.id.lv_listview);
		mAdapter = new DataSelectorAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
				// TODO Auto-generated method stub
				OptionBean bean = (OptionBean) mAdapter.getItem(position);
				switch (optionCode) {
				case Constan.OPTION_1:
					CommonManager.getInstance().setSexId(DataSelectorActivity.this, bean.id);
					break;

				case Constan.OPTION_2:
					CommonManager.getInstance().setMarriageId(DataSelectorActivity.this, bean.id);
					break;

				case Constan.OPTION_3:
					CommonManager.getInstance().setAgeId(DataSelectorActivity.this, bean.id);
					break;

				case Constan.OPTION_4:
					CommonManager.getInstance().setIncomeId(DataSelectorActivity.this, bean.id);
					break;

				case Constan.OPTION_5:
					CommonManager.getInstance().setProfessionId(DataSelectorActivity.this, bean.id);
					break;
				}
				CommonManager.getInstance().setOnClickable(DataSelectorActivity.this, true);
				finish();
			}
		});
	}

	/** 
	* @Title: initParams 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initParams() {
		mList.clear();
		int resId1 = 0;
		int resId2 = 0;
		optionCode = getIntent().getIntExtra(Constan.OPTION_CODE, 0);
		switch (optionCode) {
		case Constan.OPTION_0:

			break;

		case Constan.OPTION_1:
			resId1 = R.array.sexId;
			resId2 = R.array.sexStr;
			break;

		case Constan.OPTION_2:
			resId1 = R.array.marriageId;
			resId2 = R.array.marriageStr;
			break;

		case Constan.OPTION_3:
			resId1 = R.array.ageId;
			resId2 = R.array.ageStr;
			break;

		case Constan.OPTION_4:
			resId1 = R.array.incomeId;
			resId2 = R.array.incomeStr;
			break;

		case Constan.OPTION_5:
			resId1 = R.array.professionId;
			resId2 = R.array.professionStr;
			break;
		}

		int[] ids = getResources().getIntArray(resId1);
		String[] titles = getResources().getStringArray(resId2);

		for (int i = 0; i < ids.length; i++) {
			OptionBean bean = new OptionBean(ids[i], titles[i]);
			mList.add(bean);
		}
		mAdapter.setList(mList);
	}
}
