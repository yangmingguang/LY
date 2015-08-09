/**   
* @Title: FloatWindowManager.java
* @Package com.yj.ecard.business.phone
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-2 下午12:15:25
* @version V1.0   
*/

package com.yj.ecard.business.phone;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.model.TelAdBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.SharedPrefsUtil;
import com.yj.ecard.publics.utils.Utils;

/**
* @ClassName: FloatWindowManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-2 下午12:15:25
* 
*/

public class FloatWindowManager {

	private int advId;
	private Timer timer;
	private int count = 11;
	private Context context;
	private boolean isCalling;
	private View mFloatLayout;
	private ImageView imageView;
	private TelAdBean mTelAdBean;
	private WindowManager mWindowManager;
	private TextView textView, tvPhone, tvName;
	private WindowManager.LayoutParams wmParams;
	private static volatile FloatWindowManager mFloatWindowManager;

	/******************************单例开始********************************/

	private FloatWindowManager() {
		// TODO Auto-generated constructor stub
	}

	public static FloatWindowManager getInstance() {
		if (mFloatWindowManager == null) {
			synchronized (FloatWindowManager.class) {
				if (mFloatWindowManager == null) {
					mFloatWindowManager = new FloatWindowManager();
				}
			}
		}
		return mFloatWindowManager;
	}

	/******************************单例结束********************************/

	public void setLargeLocalPath(Context context, String largeLocalPath) {
		SharedPrefsUtil.putValue(context, Constan.LARGE_LOCAL_PATH, largeLocalPath);
	}

	public String getLargeLocalPath(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.LARGE_LOCAL_PATH, "");
	}

	/**
	 * 
	* @Title: showFloatView 
	* @Description: 显示悬浮窗 
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void showFloatView(final Context context, String phoneNumber, boolean isFullScreen, boolean isCalling) {

		// 更新通话状态--正在通话中
		DBService.getInstance(context).updateCall(1);

		this.context = context;
		this.isCalling = isCalling;
		hideFloatView(); // 先清除原有的悬浮框
		if (null == mFloatLayout) {
			// 悬浮窗layout
			if (isFullScreen) {
				// 全屏显示
				mFloatLayout = LayoutInflater.from(context).inflate(R.layout.float_fullscreen_layout, null);
			} else {
				// 半屏显示
				mFloatLayout = LayoutInflater.from(context).inflate(R.layout.float_halfscreen_layout, null);
				tvPhone = (TextView) mFloatLayout.findViewById(R.id.tv_phone);
				tvName = (TextView) mFloatLayout.findViewById(R.id.tv_name);
			}
			textView = (TextView) mFloatLayout.findViewById(R.id.tv_seconds);
			imageView = (ImageView) mFloatLayout.findViewById(R.id.imageView);

			// WindowManager
			mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			//获取LayoutParams对象
			wmParams = new WindowManager.LayoutParams();
			// 设置窗体显示类型——TYPE_SYSTEM_ALERT(系统提示)
			wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
			wmParams.format = PixelFormat.RGBA_8888;
			wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
			wmParams.gravity = Gravity.LEFT | Gravity.TOP;
			wmParams.x = 0;
			wmParams.y = 0;

			wmParams.width = WindowManager.LayoutParams.MATCH_PARENT; // 窗口宽
			if (isFullScreen) {
				wmParams.height = WindowManager.LayoutParams.MATCH_PARENT;// 窗口高
			} else {
				int height = 3 * (mWindowManager.getDefaultDisplay().getHeight()) / 5;
				wmParams.height = height;// 窗口高
			}

			if (!isFullScreen) {
				int count = DBService.getInstance(context).getCount();
				for (int i = 0; i < count; i++) {
					// 随机获取一张图片显示
					mTelAdBean = DBService.getInstance(context).random();
					if (null != mTelAdBean) {
						if (addRecord(context, count, mTelAdBean.smallUrl)) {
							break;
						}
					}
				}
			}

			String path = null;
			if (isFullScreen) {
				path = getLargeLocalPath(context); // 使用大图片路径
			} else {
				advId = mTelAdBean.id;
				path = mTelAdBean.smallLocalPath;
				setLargeLocalPath(context, mTelAdBean.largeLocalPath); // 设置大图片路径
				tvPhone.setText(phoneNumber);// 设置来电去电号码显示
				tvName.setText(Utils.getContactNameByPhoneNumber(context, phoneNumber));// 设置来电去电名称显示
			}

			// 判断文件是否存在
			File file = new File(path);
			if (file.exists()) {
				String imagePath = path + ".jpg";
				File newFile = new File(imagePath);
				boolean isSuccess = Utils.copyFile(file, newFile, true);

				// 判断复制文件是否成功.
				if (isSuccess) {
					Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
					// 倒计时
					updateText(context);
					// 图片加载完成才显示
					imageView.setImageBitmap(bitmap);
					// 弹出悬浮窗
					mWindowManager.addView(mFloatLayout, wmParams);
				}
			}

			// 点击事件
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					hideFloatView(); // 隐藏悬浮窗
				}
			});

		}
	}

	/** 
	* @Title: updateText 
	* @Description: 更新秒数倒计时
	* @param @param context
	* @param @param textView2    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void updateText(final Context context) {
		if (null == timer) {
			timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					handler.sendEmptyMessage(0);
				}
			}, 0, 1000);
		}
	}

	/**
	 * 
	* @Title: hideFloatView 
	* @Description: 隐藏悬浮窗，还原参数
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void hideFloatView() {
		if (null != mFloatLayout && null != mWindowManager) {
			try {
				mWindowManager.removeViewImmediate(mFloatLayout);
			} catch (Exception e) {
				mFloatLayout = null;
				mWindowManager = null;
			}
			if (null != timer) {
				timer.cancel();
				timer = null;
				count = 11;
			}
			try {
				mFloatLayout = null;
				mWindowManager = null;
			} catch (Exception e) {
				// 
			}
		}
	}

	/**
	 * handler处理界面
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:

				if (count == 0) {
					hideFloatView(); // 隐藏悬浮窗
					// 发送请求，表示已攒钱
					if (null != mTelAdBean) {
						int sort = 1;
						if (isCalling) {
							sort = 2; // 打电话
						} else {
							sort = 1; // 接电话
						}
						PhoneManager.getInstance().postSeeAdData(context, advId, Constan.TAB_BOMB, sort);
					}
				}
				--count;
				textView.setText(count + "");

				break;
			}
		}
	};

	/**
	 * 
	* @Title: addRecord 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param count
	* @param @param smallUrl
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean addRecord(Context context, int count, String smallUrl) {
		boolean flag = false;

		int totalCount = DBService.getInstance(context).getTotalCount();
		int recordCount = DBService.getInstance(context).getRecordCount(smallUrl);

		if (count <= totalCount) {
			DBService.getInstance(context).clearRecord();
		}

		if (recordCount == 0) {
			DBService.getInstance(context).save(smallUrl);
			flag = true;
		}

		return flag;
	}
}
