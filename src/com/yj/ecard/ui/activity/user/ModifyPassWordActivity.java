/**   
* @Title: ModifyPassWordActivity.java
* @Package com.yj.ecard.ui.activity.user
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-25 下午6:04:04
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
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: ModifyPassWordActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-25 下午6:04:04
* 
*/

public class ModifyPassWordActivity extends BaseActivity implements OnClickListener {

	private final int[] btns = { R.id.btn_cancel, R.id.btn_confirm };
	private EditText etOldPassword, etNewPassword, etConfirmPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_modify_password);
		Utils.addFinishAct(this);
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
		etOldPassword = (EditText) findViewById(R.id.et_old_pw);
		etNewPassword = (EditText) findViewById(R.id.et_new_pw);
		etConfirmPassword = (EditText) findViewById(R.id.et_confirm_pw);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cancel:
			finish();
			break;

		case R.id.btn_confirm:
			toModify();
			break;
		}
	}

	/** 
	* @Title: toModify 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void toModify() {
		String oldPassword = etOldPassword.getText().toString().trim();
		String newPassword = etNewPassword.getText().toString().trim();
		String confirmPassword = etConfirmPassword.getText().toString().trim();
		if (TextUtils.isEmpty(oldPassword)) {
			ToastUtil.show(context, R.string.old_pw_check_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(newPassword)) {
			ToastUtil.show(context, R.string.new_pw_check_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(confirmPassword)) {
			ToastUtil.show(context, R.string.confirm_pw_check_tips, ToastUtil.LENGTH_SHORT);
		} else if (!newPassword.equals(confirmPassword)) {
			ToastUtil.show(context, R.string.compare_pw_check_tips, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getModifyPwData(context, handler, oldPassword, newPassword);
		}
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Utils.dismissProgressDialog(); // 取消dialog
			switch (msg.what) {
			case UserManager.onSuccess:
				ToastUtil.show(context, R.string.modify_pw_success, ToastUtil.LENGTH_LONG);
				startActivity(new Intent(context, LoginActivity.class));
				UserManager.getInstance().clearUserInfo(context); // 清除数据
				Utils.finishActivity();
				break;

			case UserManager.onEmpty:
				ToastUtil.show(context, R.string.pw_errror, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;
			}

			return true;
		}
	});

}
