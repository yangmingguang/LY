/**   
* @Title: RegisterInviteActivity.java
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
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.me.CompleteDataActivity;

/**
* @ClassName: RegisterInviteActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 上午10:47:22
* 
*/

public class RegisterInviteActivity extends BaseActivity implements OnClickListener {

	private EditText etPassword, etInvitePhone;
	private final int[] btns = { R.id.btn_complete };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_register_invite);
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
		etPassword = (EditText) findViewById(R.id.et_password);
		etInvitePhone = (EditText) findViewById(R.id.et_invite_phone_num);

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
				ToastUtil.show(context, R.string.reg_success_tips, ToastUtil.LENGTH_SHORT);
				Intent intent = new Intent(context, CompleteDataActivity.class);
				intent.putExtra("fromRegister", true);
				startActivity(intent);
				Utils.finishActivity(); // 关闭activity
				break;

			case UserManager.onEmpty:
				ToastUtil.show(context, R.string.reg_failure_tips, ToastUtil.LENGTH_SHORT);
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
		case R.id.btn_complete:
			toComplete();
			break;
		}
	}

	/** 
	* @Title: toComplete 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void toComplete() {
		String passWord = etPassword.getText().toString().trim();
		String invitePhone = etInvitePhone.getText().toString().trim();
		String userName = UserManager.getInstance().getUserName(context);

		if (TextUtils.isEmpty(passWord)) {
			ToastUtil.show(context, R.string.password_hint, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getUserRegisterData(context, handler, userName, passWord, invitePhone);
		}
	}

}
