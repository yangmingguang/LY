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
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.yj.ecard.R;
import com.yj.ecard.business.address.AddressManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.AddAddressRequest;
import com.yj.ecard.publics.http.model.response.AddAddressResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.AddressBean;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: AddAddressActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-24 下午10:53:54
* 
*/

public class AddAddressActivity extends BaseActivity implements OnClickListener {

	private int id;
	private View loadingView;
	private ImageView ivSwitch;
	private boolean isDefault = true;
	private String name, address, phone;
	private EditText etName, etPhone, etAddress;
	private final int[] btns = { R.id.btn_state, R.id.btn_save };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_add_address);
		initView();
		loadAllData();
	}

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		id = getIntent().getIntExtra("id", 0);
		etName = (EditText) findViewById(R.id.et_name);
		etPhone = (EditText) findViewById(R.id.et_phone);
		etAddress = (EditText) findViewById(R.id.et_address);
		ivSwitch = (ImageView) findViewById(R.id.iv_switch);
		loadingView = findViewById(R.id.l_loading_rl);
		if (id != 0) {
			loadingView.setVisibility(View.VISIBLE);
		} else {
			loadingView.setVisibility(View.GONE);
		}
		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		if (id != 0) {
			AddressManager.getInstance().getDefaultAddress(context, handler, id);
		}
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case AddressManager.onSuccess:
				AddressBean bean = (AddressBean) msg.obj;
				etName.setText(bean.realName);
				etPhone.setText(bean.phone);
				etAddress.setText(bean.address);
				if (bean.isDefault == 1) { // 0为不设置  1为设置默认
					isDefault = true;
					ivSwitch.setBackgroundResource(R.drawable.setting_open);
				} else {
					isDefault = false;
					ivSwitch.setBackgroundResource(R.drawable.setting_close);
				}

				loadingView.setVisibility(View.GONE);
				break;

			case AddressManager.onEmpty:
			case AddressManager.onFailure:

				break;
			}

			return true;
		}
	});

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
		request.setRealName(name);
		request.setPhone(phone);
		request.setAddress(address);
		int result = (isDefault == true) ? 1 : 0;
		request.setIsDefault(result);
		if (id != 0) {
			request.setId(id);
			request.setUpdate(true);
		} else {
			request.setUpdate(false);
		}
		DataFetcher.getInstance().addAddressResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog();
				LogUtil.getLogger().d("response==>" + response.toString());
				AddAddressResponse adddAddressResponse = (AddAddressResponse) JsonUtil.jsonToBean(response,
						AddAddressResponse.class);
				ToastUtil.show(context, adddAddressResponse.status.msg, ToastUtil.LENGTH_SHORT);
				finish();
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
			name = etName.getText().toString();
			phone = etPhone.getText().toString();
			address = etAddress.getText().toString();
			if (TextUtils.isEmpty(name)) {
				ToastUtil.show(context, R.string.name_tips, ToastUtil.LENGTH_SHORT);
			} else if (TextUtils.isEmpty(phone)) {
				ToastUtil.show(context, R.string.phone_tips, ToastUtil.LENGTH_SHORT);
			} else if (TextUtils.isEmpty(address)) {
				ToastUtil.show(context, R.string.address_tips, ToastUtil.LENGTH_SHORT);
			} else {
				addAddressData();
			}
			break;
		}
	}

}
