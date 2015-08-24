/**   
* @Title: AddAddressActivity.java
* @Package com.yj.ecard.ui.activity.order
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-24 下午10:53:54
* @version V1.0   
*/

package com.yj.ecard.ui.activity.order;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.yj.ecard.R;
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

			break;
		}

	}

}
