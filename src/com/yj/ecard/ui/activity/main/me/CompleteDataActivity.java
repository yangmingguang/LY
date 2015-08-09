/**   
* @Title: CompleteDataActivity.java
* @Package com.yj.ecard.ui.activity.user
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午4:41:45
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import java.io.File;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.UserDataRequest;
import com.yj.ecard.publics.http.model.response.UserDataResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Base64Util;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.DataUtil;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.MainActivity;
import com.yj.ecard.ui.activity.main.home.preferential.SelectPicPopupWindow;

/**
* @ClassName: CompleteDataActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午4:41:45
* 
*/

public class CompleteDataActivity extends BaseActivity implements OnClickListener {

	private String field;
	private Intent intent;
	private String imgPath;
	private ImageView ivHead;
	private View contentView, loadingView, emptyView;
	private TextView tvSex, tvMarriage, tvAge, tvIncome, tvOccupation, tvField;
	private final int[] btns = { R.id.btn_head_ll, R.id.btn_sex_ll, R.id.btn_marriage_ll, R.id.btn_age_ll,
			R.id.btn_income_ll, R.id.btn_occupation_ll, R.id.btn_field_ll, R.id.btn_confirm };

	private final int SEX_DATA = 4001;//性别
	private final int MARRIAGE_DATA = 4002;//婚姻
	private final int AGE_DATA = 4003;//年龄
	private final int INCOME_DATA = 4004;//收入
	private final int OCCUPATION_DATA = 4005;//职业
	private final int FIELD_DATA = 4006;//领域
	private boolean isFromRegister = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_complete_data);
		CommonManager.getInstance().setCropImagePath(context, ""); // 每次进入界面执行清除
		initView();
		loadAllData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (null != ivHead) {
			imgPath = CommonManager.getInstance().getCropImagePath(context);
			if (!imgPath.isEmpty())
				ImageLoaderUtil.load(context, ImageType.LOCALPIC, imgPath, R.drawable.ic_default_head,
						R.drawable.ic_default_head, ivHead);
		}
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		isFromRegister = getIntent().getBooleanExtra("fromRegister", false);
		ivHead = (ImageView) findViewById(R.id.iv_head_picture);
		tvSex = (TextView) findViewById(R.id.tv_sex);
		tvMarriage = (TextView) findViewById(R.id.tv_marriage);
		tvAge = (TextView) findViewById(R.id.tv_age);
		tvIncome = (TextView) findViewById(R.id.tv_income);
		tvOccupation = (TextView) findViewById(R.id.tv_occupation);
		tvField = (TextView) findViewById(R.id.tv_field);
		contentView = findViewById(R.id.ll_content);
		loadingView = findViewById(R.id.l_loading_rl);
		emptyView = findViewById(R.id.l_empty_rl);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/**
	 * 
	* @Title: showEmptyView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void showEmptyView() {
		contentView.setVisibility(View.GONE);
		loadingView.setVisibility(View.GONE);
		emptyView.setVisibility(View.VISIBLE);
	}

	/**
	 * 
	* @Title: showContentView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void showContentView() {
		contentView.setVisibility(View.VISIBLE);
		loadingView.setVisibility(View.GONE);
		emptyView.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_head_ll:
			startActivity(new Intent(context, SelectPicPopupWindow.class));
			break;

		case R.id.btn_sex_ll:
			intent = new Intent(this, DataSelectorActivity.class);
			intent.putExtra(Constan.OPTION_CODE, Constan.OPTION_1);
			startActivityForResult(intent, SEX_DATA);
			break;

		case R.id.btn_marriage_ll:
			intent = new Intent(this, DataSelectorActivity.class);
			intent.putExtra(Constan.OPTION_CODE, Constan.OPTION_2);
			startActivityForResult(intent, MARRIAGE_DATA);
			break;

		case R.id.btn_age_ll:
			intent = new Intent(this, DataSelectorActivity.class);
			intent.putExtra(Constan.OPTION_CODE, Constan.OPTION_3);
			startActivityForResult(intent, AGE_DATA);
			break;

		case R.id.btn_income_ll:
			intent = new Intent(this, DataSelectorActivity.class);
			intent.putExtra(Constan.OPTION_CODE, Constan.OPTION_4);
			startActivityForResult(intent, INCOME_DATA);
			break;

		case R.id.btn_occupation_ll:
			intent = new Intent(this, DataSelectorActivity.class);
			intent.putExtra(Constan.OPTION_CODE, Constan.OPTION_5);
			startActivityForResult(intent, OCCUPATION_DATA);
			break;

		case R.id.btn_field_ll:
			if (null != field) {
				intent = new Intent(this, FieldActivity.class);
				intent.putExtra("field", field);
				startActivityForResult(intent, FIELD_DATA);
			}
			break;

		case R.id.btn_confirm:
			toSubmit();
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
		boolean isOnClick = CommonManager.getInstance().isOnClickable(context);
		if (isOnClick) {
			switch (requestCode) {
			case SEX_DATA:
				int sexId = CommonManager.getInstance().getSexId(context);
				tvSex.setText(DataUtil.getSexById(sexId));
				break;

			case MARRIAGE_DATA:
				int marriageId = CommonManager.getInstance().getMarriageId(context);
				tvMarriage.setText(DataUtil.getMarriageById(marriageId));
				break;

			case AGE_DATA:
				int ageId = CommonManager.getInstance().getAgeId(context);
				tvAge.setText(DataUtil.getAgeById(ageId));
				break;

			case INCOME_DATA:
				int incomeId = CommonManager.getInstance().getIncomeId(context);
				tvIncome.setText(DataUtil.getIncomeById(incomeId));
				break;

			case OCCUPATION_DATA:
				int professionId = CommonManager.getInstance().getProfessionId(context);
				tvOccupation.setText(DataUtil.getProfessionById(professionId));
				break;

			case FIELD_DATA:
				tvField.setText(CommonManager.getInstance().getFieldText(context));
				field = CommonManager.getInstance().getFieldId(context);
				break;
			}
		}
		CommonManager.getInstance().setOnClickable(context, false);
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		UserDataRequest request = new UserDataRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getUserDataResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				UserDataResponse mUserDataResponse = (UserDataResponse) JsonUtil.jsonToBean(response,
						UserDataResponse.class);

				// 数据响应状态
				int stateCode = mUserDataResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					int sexId = mUserDataResponse.sex;
					int marriageId = mUserDataResponse.marriage;
					int ageId = mUserDataResponse.age;
					int incomeId = mUserDataResponse.income;
					int professionId = mUserDataResponse.occupation;
					// 保存id
					CommonManager.getInstance().setSexId(context, sexId);
					CommonManager.getInstance().setMarriageId(context, marriageId);
					CommonManager.getInstance().setAgeId(context, ageId);
					CommonManager.getInstance().setIncomeId(context, incomeId);
					CommonManager.getInstance().setProfessionId(context, professionId);

					field = mUserDataResponse.field;
					tvSex.setText(DataUtil.getSexById(sexId));
					tvMarriage.setText(DataUtil.getMarriageById(marriageId));
					tvAge.setText(DataUtil.getAgeById(ageId));
					tvIncome.setText(DataUtil.getIncomeById(incomeId));
					tvOccupation.setText(DataUtil.getProfessionById(professionId));
					tvField.setText(Utils.getFieldStr(mUserDataResponse.field));
					ImageLoaderUtil.load(context, ImageType.NETWORK, mUserDataResponse.url, R.drawable.ic_default_head,
							R.drawable.ic_default_head, ivHead);
					showContentView(); // 显示内容界面
					break;

				case Constan.EMPTY_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
					showEmptyView(); // 显示错误界面
					break;

				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
					showEmptyView(); // 显示错误界面
					break;
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				showEmptyView(); // 显示错误界面
			}
		}, true);

	}

	/**
	 * 
	* @Title: toSubmit 
	* @Description: 提交修改数据
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void toSubmit() {

		Utils.showProgressDialog(this); // 显示dialog
		UserDataRequest request = new UserDataRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));

		request.setSex(CommonManager.getInstance().getSexId(context));
		request.setMarriage(CommonManager.getInstance().getMarriageId(context));
		request.setAge(CommonManager.getInstance().getAgeId(context));
		request.setIncome(CommonManager.getInstance().getIncomeId(context));
		request.setOccupation(CommonManager.getInstance().getProfessionId(context));
		request.setField(CommonManager.getInstance().getFieldId(context));
		request.setPicName(new File(imgPath).getName());
		request.setUrlStream(Base64Util.imgaeToBase64(imgPath));
		DataFetcher.getInstance().postUserDataResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				UserDataResponse mUserDataResponse = (UserDataResponse) JsonUtil.jsonToBean(response,
						UserDataResponse.class);

				// 数据响应状态
				int stateCode = mUserDataResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mUserDataResponse.status.msg, ToastUtil.LENGTH_LONG);
					onBackPressed();
					break;

				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mUserDataResponse.status.msg, ToastUtil.LENGTH_SHORT);
					break;

				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if (isFromRegister) {
				startActivity(new Intent(context, MainActivity.class));
				UserManager.getInstance().setLogin(context, true);
			}
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (isFromRegister) {
			startActivity(new Intent(context, MainActivity.class));
			UserManager.getInstance().setLogin(context, true);
		}
		finish();
	}
}
