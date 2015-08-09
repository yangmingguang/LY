/**   
* @Title: PublishPreferentialActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午11:03:20
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.preferential;

import java.io.File;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.PublishPreferentialRequest;
import com.yj.ecard.publics.http.model.response.PublishPreferentialResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Base64Util;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.activity.main.me.MyPreferentialActivity;

/**
* @ClassName: PublishPreferentialActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午11:03:20
* 
*/

public class PublishPreferentialActivity extends BaseActivity implements OnClickListener {

	private ImageView imageView;
	private String title, content, phone, address, imgPath;
	private EditText etTitle, etContent, etPhone, etAddress;
	private final int btns[] = { R.id.btn_take_picture, R.id.btn_pubilsh };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_publish_preferential);
		CommonManager.getInstance().setCropImagePath(context, ""); // 每次进入界面执行清除
		initViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (null != imageView) {
			imgPath = CommonManager.getInstance().getCropImagePath(context);
			ImageLoaderUtil.load(context, ImageType.LOCALPIC, imgPath, R.drawable.app_icon, R.drawable.app_icon,
					imageView);
		}
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		imageView = (ImageView) findViewById(R.id.iv_crop_picture);
		etTitle = (EditText) findViewById(R.id.et_title);
		etContent = (EditText) findViewById(R.id.et_content);
		etPhone = (EditText) findViewById(R.id.et_phone);
		etAddress = (EditText) findViewById(R.id.et_address);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_take_picture:
			Utils.hideSoftInput(this);
			startActivity(new Intent(context, SelectPicPopupWindow.class));
			break;

		case R.id.btn_pubilsh:
			doPublish();
			break;
		}
	}

	/**
	 * 
	* @Title: doPublish 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void doPublish() {
		title = etTitle.getText().toString().trim();
		content = etContent.getText().toString().trim();
		phone = etPhone.getText().toString().trim();
		address = etAddress.getText().toString().trim();
		if (TextUtils.isEmpty(content)) {
			ToastUtil.show(context, R.string.publish_content_tips, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(title)) {
			ToastUtil.show(context, R.string.publish_title_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(phone)) {
			ToastUtil.show(context, R.string.publish_phone_hint, ToastUtil.LENGTH_SHORT);
		} else if (TextUtils.isEmpty(address)) {
			ToastUtil.show(context, R.string.publish_address_hint, ToastUtil.LENGTH_SHORT);
		} else {
			publishPreferentialData();
		}
	}

	/**
	 * 
	* @Title: publishPreferentialData 
	* @Description: 提交发布优惠信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void publishPreferentialData() {
		Utils.showProgressDialog(this); // 显示dialog
		PublishPreferentialRequest request = new PublishPreferentialRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setTitle(title);
		request.setContent(content);
		request.setAddress(address);
		request.setPhone(phone);
		request.setPicName(new File(imgPath).getName());
		request.setPicStream(Base64Util.imgaeToBase64(imgPath));
		DataFetcher.getInstance().postPublishPreferentialResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				PublishPreferentialResponse mPublishPreferentialResponse = (PublishPreferentialResponse) JsonUtil
						.jsonToBean(response, PublishPreferentialResponse.class);

				// 数据响应状态
				int stateCode = mPublishPreferentialResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mPublishPreferentialResponse.status.msg, ToastUtil.LENGTH_LONG);
					startActivity(new Intent(context, MyPreferentialActivity.class));
					finish();
					break;
				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mPublishPreferentialResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;
				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_LONG);
					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_LONG);
			}
		});
	}

}
