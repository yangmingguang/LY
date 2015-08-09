/**   
* @Title: PreferentialDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-27 下午12:26:20
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.preferential;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.PreferentialDetailRequest;
import com.yj.ecard.publics.http.model.response.PreferentialDetailResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.ui.activity.base.BaseActivity;
import com.yj.ecard.ui.views.justify.TextViewEx;

/**
* @ClassName: PreferentialDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-27 下午12:26:20
* 
*/

public class PreferentialDetailActivity extends BaseActivity {

	private String picUrl;
	private ImageView ivLogo;
	private TextViewEx tvContent;
	private View contentView, loadingView;
	private TextView tvTitle, tvTime, tvPhone, tvAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_preferential_detail);
		initViews();
		loadAllData();
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		tvTime = (TextView) findViewById(R.id.tv_time);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvPhone = (TextView) findViewById(R.id.tv_phone);
		tvAddress = (TextView) findViewById(R.id.tv_address);
		tvContent = (TextViewEx) findViewById(R.id.tv_content);
		contentView = findViewById(R.id.sv_content);
		loadingView = findViewById(R.id.l_loading_rl);

		// 点击查看大图
		ivLogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (null != picUrl) {
					Intent intent = new Intent(PreferentialDetailActivity.this, PreferentialDetailPicActivity.class);
					intent.putExtra("picUrl", picUrl);
					startActivity(intent);
				}
			}
		});
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		int id = getIntent().getIntExtra("id", 0);
		int userId = UserManager.getInstance().getUserId(context);
		String password = UserManager.getInstance().getPassword(context);
		String userName = UserManager.getInstance().getUserName(context);

		PreferentialDetailRequest request = new PreferentialDetailRequest();
		request.setId(id);
		request.setUserId(userId);
		request.setUserName(userName);
		request.setPassword(password);
		DataFetcher.getInstance().getPreferentialDetailResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				PreferentialDetailResponse preferentialDetailResponse = (PreferentialDetailResponse) JsonUtil
						.jsonToBean(response, PreferentialDetailResponse.class);

				if (preferentialDetailResponse.status.code == 1) {

					tvTitle.setText(preferentialDetailResponse.title);
					tvTime.setText("发布日期：" + preferentialDetailResponse.addTime);
					tvContent.setText(preferentialDetailResponse.content);
					tvPhone.setText("联系电话：" + preferentialDetailResponse.phone);
					tvAddress.setText("地址：" + preferentialDetailResponse.address);

					// 图片路径
					picUrl = preferentialDetailResponse.picUrl;
					ImageLoaderUtil.load(context, ImageType.NETWORK, picUrl, R.drawable.banner_detail_default,
							R.drawable.banner_detail_default, ivLogo);

					// 显示内容
					loadingView.setVisibility(View.GONE);
					contentView.setVisibility(View.VISIBLE);
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);
	}
}
