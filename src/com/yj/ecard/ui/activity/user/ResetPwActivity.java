/**   
* @Title: ResetPwActivity.java
* @Package com.yj.ecard.ui.activity.user
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-25 下午9:36:44
* @version V1.0   
*/

package com.yj.ecard.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.utils.ResourceUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: ResetPwActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-25 下午9:36:44
* 
*/

public class ResetPwActivity extends BaseActivity implements OnClickListener {

	private TextView btnSend;
	private TimeCount mTimeCount;
	private String verifyCode, newPassword;
	private EditText etValidateCode, etPassword;
	private final int[] btns = { R.id.btn_send, R.id.btn_confirm };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_reset_password);
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
		etValidateCode = (EditText) findViewById(R.id.et_validate_code);
		btnSend = (TextView) findViewById(R.id.btn_send);
		TextView tvPhone = (TextView) findViewById(R.id.tv_phone);
		tvPhone.setText(UserManager.getInstance().getUserName(context));

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);

		mTimeCount = new TimeCount(60000, 1000);//构造CountDownTimer对象
		mTimeCount.start();//开始计时
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Utils.dismissProgressDialog(); // 取消dialog
			switch (msg.what) {
			case UserManager.onResetPwSuccess:
				ToastUtil.show(context, R.string.modify_pw_success, ToastUtil.LENGTH_LONG);
				startActivity(new Intent(context, LoginActivity.class));
				Utils.finishActivity();
				break;

			case UserManager.onResetPwEmpty:
				ToastUtil.show(context, R.string.modify_pw_failure, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onResetPwFailure:
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
				break;

			case UserManager.onSuccess:
				mTimeCount.start();//开始计时
				break;

			case UserManager.onEmpty:
				ToastUtil.show(context, R.string.get_validate_code_failure, ToastUtil.LENGTH_SHORT);
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
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_send:
			Utils.showProgressDialog(this); // 显示dialog
			String mobileNum = UserManager.getInstance().getUserName(context);
			UserManager.getInstance().getValidateCode(context, handler, mobileNum);
			break;

		case R.id.btn_confirm:
			submit();
			break;
		}
	}

	/**
	 * 
	* @Title: submit 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void submit() {
		newPassword = etPassword.getText().toString().trim();
		verifyCode = etValidateCode.getText().toString().trim();
		if (TextUtils.isEmpty(verifyCode)) {
			ToastUtil.show(context, R.string.validate_code_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(newPassword)) {
			ToastUtil.show(context, R.string.password_hint, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getResetPwData(context, handler, verifyCode, newPassword);
		}
	}

	/* 定义一个倒计时的内部类 */
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {//计时完毕时触发
			btnSend.setText("重新发送");
			btnSend.setClickable(true);
			btnSend.setBackgroundColor(ResourceUtil.getInteger(context, R.color.red));
		}

		@Override
		public void onTick(long millisUntilFinished) {//计时过程显示
			btnSend.setClickable(false);
			btnSend.setText("重新发送(" + millisUntilFinished / 1000 + ")");
			btnSend.setBackgroundColor(ResourceUtil.getInteger(context, R.color.gray));
		}
	}
}
