/**   
* @Title: ScreenLockActivity.java
* @Package com.yj.ecard.ui.activity.screenlock
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-28 下午5:06:46
* @version V1.0   
*/

package com.yj.ecard.ui.activity.screenlock;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.yj.ecard.R;
import com.yj.ecard.business.phone.PhoneManager;
import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.model.ScreenLockBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.MD5Util;
import com.yj.ecard.publics.utils.StorageUtils;
import com.yj.ecard.publics.utils.Utils;

/**
* @ClassName: ScreenLockActivity
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-28 下午5:06:46
* 
*/

public class ScreenLockActivity extends Activity {

	private int advId;
	private int sort = 1;
	private String webUrl;
	private ImageView imageView;
	private Context context = this;
	private ScreenLockBean mScreenLockBean;
	private ImageView ivSliderLeft, ivSliderRight;
	private GestureDetector detector; //定义手势检测器实例
	public static boolean isShow = false; //防止重复开启这个activity
	private AnimationDrawable leftAnimArrowDrawable, rightAnimArrowDrawable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Utils.singleActivity(this);
		setContentView(R.layout.act_screen_lock);
		initView();
		initData();
	}

	//将该activity上的触碰事件交给GestureDetector处理
	public boolean onTouchEvent(MotionEvent me) {
		return detector.onTouchEvent(me);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isShow = false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		//设置动画
		mHandler.postDelayed(AnimationDrawableTask, 300); //开始绘制动画
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		leftAnimArrowDrawable.stop();
		rightAnimArrowDrawable.stop();
	}

	//通过延时控制当前绘制bitmap的位置坐标
	private Runnable AnimationDrawableTask = new Runnable() {

		public void run() {
			leftAnimArrowDrawable.start();
			rightAnimArrowDrawable.start();
			mHandler.postDelayed(AnimationDrawableTask, 300);
		}
	};

	private Handler mHandler = new Handler() {

	};

	/** 
	* @Title: initView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initView() {
		imageView = (ImageView) findViewById(R.id.imageView);
		ivSliderLeft = (ImageView) findViewById(R.id.iv_slider_left);
		ivSliderRight = (ImageView) findViewById(R.id.iv_slider_right);
		leftAnimArrowDrawable = (AnimationDrawable) ivSliderLeft.getBackground();
		rightAnimArrowDrawable = (AnimationDrawable) ivSliderRight.getBackground();
		//创建手势检测器
		detector = new GestureDetector(this, new MyGestureListener());
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initData() {
		isShow = true;

		int count = DBService.getInstance(context).getScreenLockCount();
		for (int i = 0; i < count; i++) {
			// 随机获取一张图片显示
			mScreenLockBean = DBService.getInstance(context).randomValue();
			if (null != mScreenLockBean) {
				if (addRecord(context, count, mScreenLockBean.imgUrl)) {
					break;
				}
			}
		}

		advId = mScreenLockBean.id;
		webUrl = mScreenLockBean.webUrl;
		String path = mScreenLockBean.screenLockLocalPath;
		// 判断文件是否存在
		File file = new File(path);
		if (file.exists()) {

			// 判断.隐藏目录是否存在
			File hideImageDir = new File(StorageUtils.IMAGE_PATH, ".Image");
			if (!hideImageDir.exists()) {
				hideImageDir.mkdirs();
			}

			String imagePath = hideImageDir + File.separator + MD5Util.getMD5(path) + ".jpg";
			File newFile = new File(imagePath);
			boolean isSuccess = Utils.copyFile(file, newFile, true);

			// 判断复制文件是否成功.
			if (isSuccess) {
				Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
				// 图片加载完成才显示
				imageView.setImageBitmap(bitmap);
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
			return false;

		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			return false;

		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			//由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	/*	// 拦截/屏蔽系统Home键
		public void onAttachedToWindow() {
			this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
			super.onAttachedToWindow();
		}*/

	/**
	 * 
	* @ClassName: MyGestureListener
	* @Description: 手势滑动识别
	* @author YangMingGuang
	* @date 2015-8-1 上午10:27:16
	*
	 */
	class MyGestureListener implements OnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			float minMove = 120; //最小滑动距离
			float minVelocity = 0; //最小滑动速度
			float beginX = e1.getX();
			float endX = e2.getX();
			float beginY = e1.getY();
			float endY = e2.getY();

			if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity) {//左滑
				//Toast.makeText(context, velocityX + "左滑", Toast.LENGTH_SHORT).show();

				// 左划执行操作  
				sort = 1;
				Intent intent = new Intent(context, ScreenLockDetailActivity.class);
				intent.putExtra("webUrl", webUrl);
				startActivity(intent);
				PhoneManager.getInstance().postSeeAdData(context, advId, Constan.TAB_DRAW, sort);
				finish();

			} else if (endX - beginX > minMove && Math.abs(velocityX) > minVelocity) {//右滑
				//Toast.makeText(context, velocityX + "右滑", Toast.LENGTH_SHORT).show();

				// 右划执行操作  
				sort = 2;
				PhoneManager.getInstance().postSeeAdData(context, advId, Constan.TAB_DRAW, sort);
				finish();

			} else if (beginY - endY > minMove && Math.abs(velocityY) > minVelocity) {//上滑
				//Toast.makeText(context, velocityX + "上滑", Toast.LENGTH_SHORT).show();

			} else if (endY - beginY > minMove && Math.abs(velocityY) > minVelocity) {//下滑
				//Toast.makeText(context, velocityX + "下滑", Toast.LENGTH_SHORT).show();
			}

			return false;
		}

	}

	/**
	 * 
	* @Title: addRecord 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param count
	* @param @param imgUrl
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean addRecord(Context context, int count, String imgUrl) {
		boolean flag = false;

		int totalCount = DBService.getInstance(context).getScreenLockTotalCount();
		int recordCount = DBService.getInstance(context).getScreenLockRecordCount(imgUrl);

		if (count <= totalCount) {
			DBService.getInstance(context).clearScreenLockRecord();
		}

		if (recordCount == 0) {
			DBService.getInstance(context).saveScrennLock(imgUrl);
			flag = true;
		}

		return flag;
	}

}
