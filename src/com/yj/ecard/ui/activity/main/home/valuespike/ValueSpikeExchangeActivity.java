/**   
* @Title: ValueSpikeExchangeActivity.java
* @Package com.yj.ecard.ui.activity.main.home.valuespike
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-28 上午10:32:29
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.valuespike;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.ValueSpikeExchangeRequest;
import com.yj.ecard.publics.http.model.response.ValueSpikeExchangeResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: ValueSpikeExchangeActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-28 上午10:32:29
* 
*/

public class ValueSpikeExchangeActivity extends BaseActivity implements OnClickListener {

	private int id;
	private String realName, address, phone, postNum;
	private EditText etAddress, etPostalCode, etName, etPhone;
	private final int[] btns = { R.id.btn_cancel, R.id.btn_confirm };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_valuespike_exchange);
		initViews();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		id = getIntent().getIntExtra("id", 0);
		etAddress = (EditText) findViewById(R.id.et_address);
		etPostalCode = (EditText) findViewById(R.id.et_postalcode);
		etName = (EditText) findViewById(R.id.et_name);
		etPhone = (EditText) findViewById(R.id.et_phone);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			finish();
			break;

		case R.id.btn_confirm:
			toSubmit();
			break;
		}
	}

	/** 
	* @Title: toSubmit 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void toSubmit() {
		address = etAddress.getText().toString().trim();
		postNum = etPostalCode.getText().toString().trim();
		realName = etName.getText().toString().trim();
		phone = etPhone.getText().toString().trim();
		if (TextUtils.isEmpty(address)) {
			ToastUtil.show(context, R.string.collect_address_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(postNum)) {
			ToastUtil.show(context, R.string.postalcode_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(realName)) {
			ToastUtil.show(context, R.string.collect_name_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(phone)) {
			ToastUtil.show(context, R.string.collect_phone_hint, ToastUtil.LENGTH_SHORT);
		} else {
			postValueSpikeExchangeData();
		}
	}

	/**
	 * 
	* @Title: postExchangeAddressData 
	* @Description: 提交收货地址
	* @param @param context
	* @param @param handler
	* @param @param name
	* @param @param phone
	* @param @param address    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postValueSpikeExchangeData() {
		Utils.showProgressDialog(this); // 显示dialog
		ValueSpikeExchangeRequest request = new ValueSpikeExchangeRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		request.setAddress(address);
		request.setPhone(phone);
		request.setPostNum(postNum);
		request.setProId(id);
		request.setRealName(realName);
		DataFetcher.getInstance().postValueSpikeExchangeResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				ValueSpikeExchangeResponse mValueSpikeExchangeResponse = (ValueSpikeExchangeResponse) JsonUtil
						.jsonToBean(response, ValueSpikeExchangeResponse.class);

				// 数据响应状态
				int stateCode = mValueSpikeExchangeResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mValueSpikeExchangeResponse.status.msg, ToastUtil.LENGTH_LONG);
					startActivity(new Intent(context, SeckillRecordActivity.class));
					finish();
					break;
				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mValueSpikeExchangeResponse.status.msg, ToastUtil.LENGTH_SHORT);
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
}
