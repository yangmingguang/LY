/**   
* @Title: BombAdDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.home.wonderful
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-13 上午9:37:47
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.wonderful;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.business.main.HomeTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.WonderfulAdDetailResponse;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.activity.base.BaseActivity;

/**
* @ClassName: BombAdDetailActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-13 上午9:37:47
* 
*/

public class BombAdDetailActivity extends BaseActivity implements OnClickListener {

	private ImageView ivLogo;
	private View loadingView;
	private String webSite, phoneNumber;
	private final int[] btns = { R.id.btn_webSite, R.id.btn_phone };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_wonderful_ad_detail);
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
		loadingView = findViewById(R.id.l_loading_rl);
		ivLogo = (ImageView) findViewById(R.id.iv_logo);

		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);

	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		int sortId = getIntent().getIntExtra("sortId", 1);
		int id = getIntent().getIntExtra("id", 0);
		HomeTabManager.getInstance().getWonderfulAdDetailData(context, handler, sortId, id);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_webSite:
			if (null != webSite) {
				//Intent intent = new Intent(context, WebViewActivity.class);
				//intent.putExtra("webSite", webSite);
				//startActivity(intent);

				// 使用内置浏览器
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(webSite);
				intent.setData(content_url);
				startActivity(intent);
			}
			break;

		case R.id.btn_phone:
			if (null != phoneNumber)
				Utils.telCall(context, phoneNumber);
			break;
		}
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case MeTabManager.onSuccess:
				setParams((WonderfulAdDetailResponse) msg.obj);
				loadingView.setVisibility(View.GONE);
				break;

			case MeTabManager.onEmpty:

				break;

			case MeTabManager.onFailure:

				break;
			}
			return true;
		}
	});

	/**
	 * 
	* @Title: setParams 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void setParams(WonderfulAdDetailResponse response) {
		// TODO Auto-generated method stub
		webSite = response.webSite;
		phoneNumber = response.phone;
		ImageLoaderUtil.load(context, ImageType.NETWORK, response.picUrl, R.drawable.banner_default_vertical,
				R.drawable.banner_default_vertical, ivLogo);
	}

}
