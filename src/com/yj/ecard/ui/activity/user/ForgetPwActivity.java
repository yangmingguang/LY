/**   
* @Title: ForgetPwActivity.java
* @Package com.yj.ecard.ui.activity.user
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-25 下午9:05:08
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
* @ClassName: ForgetPwActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-25 下午9:05:08
* 
*/

public class ForgetPwActivity extends BaseActivity {

	private String mobileNum;
	private EditText etMobileNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_forget_password);
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
		// TODO Auto-generated method stub
		etMobileNum = (EditText) findViewById(R.id.et_mobile_num);
		findViewById(R.id.btn_validate_code).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getValidateCode();
			}
		});
	}

	private void getValidateCode() {
		mobileNum = etMobileNum.getText().toString().trim();
		if (TextUtils.isEmpty(mobileNum)) {
			ToastUtil.show(context, R.string.username_hint, ToastUtil.LENGTH_SHORT);
		} else {
			Utils.showProgressDialog(this); // 显示dialog
			UserManager.getInstance().getValidateCode(context, handler, mobileNum);
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
				ToastUtil.show(context, R.string.get_validate_code_success, ToastUtil.LENGTH_SHORT);
				UserManager.getInstance().setUserName(context, mobileNum);
				startActivity(new Intent(context, ResetPwActivity.class));
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
}
