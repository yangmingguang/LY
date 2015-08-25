/**   
* @Title: AddAddressActivity.java
* @Package com.yj.ecard.ui.activity.order
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-24 下午10:53:54
* @version V1.0   
*/

package com.yj.ecard.ui.activity.order;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.AddAddressRequest;
import com.yj.ecard.publics.http.model.response.AddAddressResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: AddAddressActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-24 下午10:53:54
* 
*/

public class AddAddressActivity extends BaseActivity implements OnClickListener {

	private ImageView ivSwitch;
	private boolean isDefault = true;
	private EditText etName, etPhone, etAddress;
	private final int[] btns = { R.id.btn_state, R.id.btn_save };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_add_address);
		initView();
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		etName = (EditText) findViewById(R.id.et_name);
		etPhone = (EditText) findViewById(R.id.et_phone);
		etAddress = (EditText) findViewById(R.id.et_address);
		ivSwitch = (ImageView) findViewById(R.id.iv_switch);
		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/**
	 * 
	* @Title: addAddressData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void addAddressData() {
		Utils.showProgressDialog(this);
		AddAddressRequest request = new AddAddressRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		request.setRealName(etName.getText().toString());
		request.setPhone(etPhone.getText().toString());
		request.setAddress(etAddress.getText().toString());
		int result = (isDefault == true) ? 1 : 0;
		request.setIsDefault(result);
		DataFetcher.getInstance().addAddressResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
				LogUtil.getLogger().d("response==>" + response.toString());
				AddAddressResponse adddAddressResponse = (AddAddressResponse) JsonUtil.jsonToBean(response,
						AddAddressResponse.class);
				ToastUtil.show(context, adddAddressResponse.status.msg, ToastUtil.LENGTH_SHORT);
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_state:
			if (!isDefault) {
				isDefault = true;
				ivSwitch.setBackgroundResource(R.drawable.setting_open);
			} else {
				isDefault = false;
				ivSwitch.setBackgroundResource(R.drawable.setting_close);
			}
			break;

		case R.id.btn_save:
			addAddressData();
			break;
		}

	}

}
