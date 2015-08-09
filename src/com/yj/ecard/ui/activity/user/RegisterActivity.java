/**   
* @Title: RegisterActivity.java
* @Package com.yj.ecard.ui.activity.user
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-31 上午10:47:22
* @version V1.0   
*/

package com.yj.ecard.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.response.ValidateCodeResponse;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: RegisterActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 上午10:47:22
* 
*/

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private EditText etPhoneNum, etValidateCode;
	private final int[] btns = { R.id.btn_validate_code, R.id.btn_next };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_register);
		Utils.addFinishAct(this);
		initViews();
		CommonManager.getInstance().initLocation(context);//开启定位服务
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		etPhoneNum = (EditText) findViewById(R.id.et_phone_num);
		etValidateCode = (EditText) findViewById(R.id.et_validate_code);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Utils.dismissProgressDialog(); // 取消dialog
			switch (msg.what) {
			case UserManager.onValidateCodeSuccess:
				ToastUtil.show(context, R.string.reg_validate_code_success_tips, ToastUtil.LENGTH_SHORT);
				ValidateCodeResponse response = (ValidateCodeResponse) msg.obj;
				etValidateCode.setText(response.messCode);
				break;

			case UserManager.onValidateCodeEmpty:
				ToastUtil.show(context, R.string.reg_validate_failure_tips, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onValidateCodeFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onCheckCodeSuccess:
				ToastUtil.show(context, R.string.checkcode_success_tips, ToastUtil.LENGTH_SHORT);
				startActivity(new Intent(context, RegisterInviteActivity.class));
				break;

			case UserManager.onCheckCodeEmpty:
				ToastUtil.show(context, R.string.checkcode_failure_tips, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onCheckCodeFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;
			}
			return true;
		}
	});

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_validate_code:
			getValidateCode();
			break;

		case R.id.btn_next:
			toNext();
			break;
		}
	}

	/** 
	* @Title: getValidateCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void getValidateCode() {
		String phoneNum = etPhoneNum.getText().toString().trim();
		if (TextUtils.isEmpty(phoneNum)) {
			ToastUtil.show(context, R.string.reg_check_phone_tips, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getValidateCodeData(context, handler, phoneNum);
		}
	}

	/**
	 * 
	* @Title: toNext 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void toNext() {
		String phoneNum = etPhoneNum.getText().toString().trim();
		String validateCode = etValidateCode.getText().toString().trim();
		UserManager.getInstance().setUserName(context, phoneNum); // 存储用户名

		if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(validateCode)) {
			ToastUtil.show(context, R.string.reg_check_tips, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getCheckCodeData(context, handler, phoneNum, validateCode);
		}
	}

}
