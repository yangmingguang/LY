/**   
* @Title: LoginActivity.java
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
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.MainActivity;

/**
* @ClassName: LoginActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 上午10:47:22
* 
*/

public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText etUsername, etPassword;
	private final int[] btns = { R.id.btn_login, R.id.btn_reg, R.id.btn_forget };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);
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
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);

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
			case UserManager.onSuccess:
				ToastUtil.show(context, R.string.login_success_tips, ToastUtil.LENGTH_SHORT);
				startActivity(new Intent(context, MainActivity.class));
				UserManager.getInstance().setLogin(context, true);
				finish();
				break;

			case UserManager.onEmpty:
				ToastUtil.show(context, (String) msg.obj, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;
			}

			return true;
		}
	});

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 用户登录
		case R.id.btn_login:
			toLogin();
			break;

		// 用户注册	
		case R.id.btn_reg:
			startActivity(new Intent(context, RegisterActivity.class));
			break;

		// 忘记密码	
		case R.id.btn_forget:
			startActivity(new Intent(context, ForgetPwActivity.class));
			break;
		}
	}

	/**
	 * 
	* @Title: toLogin 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void toLogin() {
		String userName = etUsername.getText().toString().trim();
		String passWord = etPassword.getText().toString().trim();

		if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
			ToastUtil.show(context, R.string.login_check_tips, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getUserLoginData(context, handler, userName, passWord);
		}
	}

}
