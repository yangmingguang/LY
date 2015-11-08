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
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.phone.PhoneManager;
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
import com.yj.ecard.publics.utils.NetworkUtils;
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

	private int count = 7;
	private Runnable runnable;
	private boolean inited = true;

	private int id;
	private String picUrl;
	private ImageView ivLogo;
	private TextViewEx tvContent;
	private View contentView, loadingView;
	private TextView tvTitle, tvDate, tvPhone, tvAddress, tvTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_preferential_detail);
		initViews();
		loadAllData();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (handler != null && runnable != null) {
			handler.removeCallbacks(runnable);
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
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
		tvDate = (TextView) findViewById(R.id.tv_date);
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
		id = getIntent().getIntExtra("id", 0);
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
					tvDate.setText("发布日期：" + preferentialDetailResponse.addTime);
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

					// 开始计时
					loadData();
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);
	}

	/**
	 * 
	* @Title: loadData 
	* @Description: 延迟6秒 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void loadData() {
		if (inited && NetworkUtils.isNetworkAvailable(context)) {
			inited = false;
			runnable = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub  
					handler.sendEmptyMessage(0); //要做的事情  
					handler.postDelayed(this, 1000); //每1秒执行一次runnable.  
				}
			};
			handler.postDelayed(runnable, 0);
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				count--;
				if (count < 0) {
					tvTime.setVisibility(View.GONE);
					handler.removeCallbacks(runnable);
					// 1=弹屏、2=划屏、3=逛优惠
					PhoneManager.getInstance().postSeeAdData(context, id, 3, 1);
				} else {
					tvTime.setText(count + "秒可收钱");
					tvTime.setVisibility(View.VISIBLE);
				}
				break;
			}
		}
	};
}
