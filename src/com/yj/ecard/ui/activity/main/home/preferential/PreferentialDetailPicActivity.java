/**   
* @Title: PreferentialDetailPicActivity.java
* @Package com.yj.ecard.ui.activity.main.home
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-9 上午9:46:39
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.preferential;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: PreferentialDetailPicActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-9 上午9:46:39
* 
*/

public class PreferentialDetailPicActivity extends Activity {

	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_preferential_detail_pic);
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
		String path = getIntent().getStringExtra("picUrl");
		ImageView imageView = (ImageView) findViewById(R.id.imageview);
		ImageLoaderUtil.load(context, ImageType.NETWORK, path, R.drawable.banner_detail_default,
				R.drawable.banner_detail_default, imageView);

		// 图片点击事件
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
