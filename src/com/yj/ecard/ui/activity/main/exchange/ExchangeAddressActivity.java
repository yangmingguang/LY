/**   
* @Title: ExchangeAddressActivity.java
* @Package com.yj.ecard.ui.activity.main.exchange
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-27 上午12:34:42
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.yj.ecard.R;
import com.yj.ecard.business.main.ExchangeTabManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.response.ExchangeAddressResponse;
import com.yj.ecard.publics.utils.MD5Util;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.me.ExchangeRecordActivity;

/**
* @ClassName: ExchangeAddressActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-27 上午12:34:42
* 
*/

public class ExchangeAddressActivity extends BaseActivity implements OnClickListener {

	private int id;
	private EditText etAddress, etPostalCode, etName, etPhone;
	private final int[] btns = { R.id.btn_cancel, R.id.btn_confirm };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exchange_address);
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
		String address = etAddress.getText().toString().trim();
		String postalCode = etPostalCode.getText().toString().trim();
		String name = etName.getText().toString().trim();
		String phone = etPhone.getText().toString().trim();
		if (TextUtils.isEmpty(address)) {
			ToastUtil.show(context, R.string.collect_address_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(postalCode)) {
			ToastUtil.show(context, R.string.postalcode_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(name)) {
			ToastUtil.show(context, R.string.collect_name_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(phone)) {
			ToastUtil.show(context, R.string.collect_phone_hint, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			String imei = Utils.getIMEI(context);
			int userId = UserManager.getInstance().getUserId(context);
			String sign = MD5Util.getMD5(imei + userId + id + name); // MD5加密
			ExchangeTabManager.getInstance().postExchangeAddressData(context, handler, id, name, phone, address,
					postalCode, sign);
		}
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Utils.dismissProgressDialog(); // 取消dialog
			ExchangeAddressResponse response = (ExchangeAddressResponse) msg.obj;

			switch (msg.what) {
			case ExchangeTabManager.onSuccess:
				ToastUtil.show(context, response.status.msg, ToastUtil.LENGTH_SHORT);
				startActivity(new Intent(context, ExchangeRecordActivity.class));
				finish();
				break;

			case ExchangeTabManager.onEmpty:
				ToastUtil.show(context, response.status.msg, ToastUtil.LENGTH_SHORT);
				break;

			case ExchangeTabManager.onFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;
			}

			return true;
		}
	});

}
