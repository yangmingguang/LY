package com.yj.ecard.ui.activity.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.http.model.request.CityListRequest;
import com.yj.ecard.publics.http.model.response.CityListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.CityBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.adapter.CityListAdapter;
import com.yj.ecard.ui.views.sortlistview.CharacterParser;
import com.yj.ecard.ui.views.sortlistview.ClearEditText;
import com.yj.ecard.ui.views.sortlistview.PinyinComparator;
import com.yj.ecard.ui.views.sortlistview.SideBar;
import com.yj.ecard.ui.views.sortlistview.SideBar.OnTouchingLetterChangedListener;

/**
 * 
* @ClassName: CityListActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-7 上午9:00:31
*
 */
public class CityListActivity extends BaseActivity {

	private TextView dialog;
	private SideBar mSideBar;
	private ListView mListView;
	private CityListAdapter mAdapter;
	private View loadingView, emptyView;
	private ClearEditText mClearEditText;
	private List<CityBean> mList = new ArrayList<CityBean>();

	/*汉字转换成拼音的类*/
	private CharacterParser mCharacterParser;
	/*根据拼音来排列ListView里面的数据类*/
	private PinyinComparator mPinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_city_list);
		initView();
		loadData();
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		emptyView = findViewById(R.id.l_empty_rl);
		loadingView = findViewById(R.id.l_loading_rl);

		mPinyinComparator = new PinyinComparator();
		mCharacterParser = CharacterParser.getInstance(); //实例化汉字转拼音类
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		mSideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		mSideBar.setTextView(dialog);

		mListView = (ListView) findViewById(R.id.lv_citylist);
		mAdapter = new CityListAdapter(this);
		mListView.setAdapter(mAdapter);

		//设置右侧触摸监听
		mSideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String str) {
				//该字母首次出现的位置
				int position = mAdapter.getPositionForSection(str.charAt(0));
				if (position != -1) {
					mListView.setSelection(position);
				}
			}
		});

		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence chs, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(chs.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<CityBean> filterDateList = new ArrayList<CityBean>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = mList;
		} else {
			filterDateList.clear();
			for (CityBean cityBean : mList) {
				String cityName = cityBean.getCityName();
				if (cityName.indexOf(filterStr.toString()) != -1
						|| mCharacterParser.getSelling(cityName).startsWith(filterStr.toString())) {
					filterDateList.add(cityBean);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, mPinyinComparator);
		mAdapter.setList(filterDateList);
	}

	/** 
	* @Title: loadData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadData() {
		CityListRequest request = new CityListRequest();
		DataFetcher.getInstance().getCityListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				CityListResponse mCityListResponse = (CityListResponse) JsonUtil.jsonToBean(response,
						CityListResponse.class);

				// 数据响应状态
				int stateCode = mCityListResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					loadingView.setVisibility(View.GONE);
					mList = mCityListResponse.data;
					// 根据a-z进行排序源数据
					Collections.sort(mList, mPinyinComparator);
					mAdapter.setList(mList);
					break;

				case Constan.EMPTY_CODE:
				case Constan.ERROR_CODE:
					emptyView.setVisibility(View.VISIBLE);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				emptyView.setVisibility(View.VISIBLE);
			}
		}, true);
	}

}
