/**   
* @Title: WithdrawActivity.java
* @Package com.yj.ecard.ui.activity.main.me
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午5:00:54
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.me;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.WithdrawRequest;
import com.yj.ecard.publics.http.model.response.WithdrawResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ResourceUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: WithdrawActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午5:00:54
* 
*/

public class WithdrawActivity extends BaseActivity implements OnClickListener {

	private TextView tvRemark;
	private String realName, bankCardnum, bankName, cashAmount;
	private final int[] btns = { R.id.btn_cancel, R.id.btn_confirm };
	private EditText etName, etCardNum, etBank, etBalance, etCashBalance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_withdraw);
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
		tvRemark = (TextView) findViewById(R.id.tv_remark);
		tvRemark.setText(ResourceUtil.getString(context, R.string.withdraw_remark));
		etName = (EditText) findViewById(R.id.et_name);
		etCardNum = (EditText) findViewById(R.id.et_cardnum);
		etBank = (EditText) findViewById(R.id.et_bank);
		etBalance = (EditText) findViewById(R.id.et_balance);
		etCashBalance = (EditText) findViewById(R.id.et_cash_balance);
		etBalance.setText(UserManager.getInstance().getCanAmount(context));

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
		realName = etName.getText().toString().trim();
		bankCardnum = etCardNum.getText().toString().trim();
		bankName = etBank.getText().toString().trim();
		String balance = etBalance.getText().toString().trim();
		cashAmount = etCashBalance.getText().toString().trim();
		if (TextUtils.isEmpty(realName)) {
			ToastUtil.show(context, R.string.withdraw_name_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(bankCardnum)) {
			ToastUtil.show(context, R.string.withdraw_cardnum_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(bankName)) {
			ToastUtil.show(context, R.string.withdraw_bank_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(balance)) {
			ToastUtil.show(context, R.string.withdraw_balance_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(cashAmount)) {
			ToastUtil.show(context, R.string.withdraw_cash_balance_tips, ToastUtil.LENGTH_SHORT);
		} else {
			postWithdrawData();
		}
	}

	/** 
	* @Title: postWithdrawData 
	* @Description: 提交可取现数据
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void postWithdrawData() {
		Utils.showProgressDialog(this); // 显示dialog
		WithdrawRequest request = new WithdrawRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		request.setRealName(realName);
		request.setBankCardnum(bankCardnum);
		request.setBankName(bankName);
		request.setCashAmount(Double.parseDouble(cashAmount));
		DataFetcher.getInstance().postWithdrawResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				WithdrawResponse mWithdrawResponse = (WithdrawResponse) JsonUtil.jsonToBean(response,
						WithdrawResponse.class);
				int stateCode = mWithdrawResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mWithdrawResponse.status.msg, ToastUtil.LENGTH_LONG);
					finish();
					break;

				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mWithdrawResponse.status.msg, ToastUtil.LENGTH_SHORT);
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
