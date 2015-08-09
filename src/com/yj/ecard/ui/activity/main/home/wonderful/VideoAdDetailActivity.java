/**   
* @Title: VideoAdDetailActivity.java
* @Package com.yj.ecard.ui.activity.main.home.wonderful
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-19 下午5:06:28
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.home.wonderful;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.MediaController;

import com.yj.ecard.R;
import com.yj.ecard.business.main.HomeTabManager;
import com.yj.ecard.business.main.MeTabManager;
import com.yj.ecard.publics.http.model.response.WonderfulAdDetailResponse;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.ui.views.videoview.MyVideoView;
import com.yj.ecard.ui.views.videoview.MyVideoView.OnReadyListener;

/**
* @ClassName: VideoAdDetailActivity
* @Description: 视屏广告详情
* @author YangMingGuang
* @date 2015-7-19 下午5:06:28
* 
*/

public class VideoAdDetailActivity extends Activity {

	private MyVideoView mViewView;
	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_video_ad_detail);
		loadAllData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != mViewView)
			mViewView.stopPlayback();
	}

	/** 
	* @Title: loadAllData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void loadAllData() {
		Utils.showProgressDialog(this);
		int id = getIntent().getIntExtra("id", 0);
		HomeTabManager.getInstance().getWonderfulAdDetailData(this, handler, Constan.TAB_VIDEO, id);
	}

	/** 
	* @Title: playVideo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void playVideo(String voideUrl) {
		mViewView = (MyVideoView) findViewById(R.id.videoView);
		mViewView.setVideoURI(Uri.parse(voideUrl));//网络资源  
		mViewView.setMediaController(new MediaController(this));//设置模式，播放进度条  
		mViewView.requestFocus();
		mViewView.setReadyListener(new OnReadyListener() {

			@Override
			public void onBufferingUpdate(MediaPlayer mp, int percent) {
				// TODO Auto-generated method stub
				if (percent > 20) {
					Utils.dismissProgressDialog();
				}

			}
		});
		mViewView.start();
	}

	/**
	 * Android Weak Handler
	 */
	private WeakHandler handler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case MeTabManager.onSuccess:
				WonderfulAdDetailResponse response = ((WonderfulAdDetailResponse) msg.obj);
				playVideo(response.voideUrl);
				break;

			case MeTabManager.onEmpty:
			case MeTabManager.onFailure:
				Utils.dismissProgressDialog();
				ToastUtil.show(context, "获取视频失败!", ToastUtil.LENGTH_LONG);
				break;
			}
			return true;
		}
	});
}
