/**   
* @Title: CrashHandler.java
* @Package com.yj.ecard.business.crash
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-26 下午10:34:13
* @version V1.0   
*/

package com.yj.ecard.business.crash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.yj.ecard.publics.http.model.request.CrashRequest;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.utils.StorageUtils;
import com.yj.ecard.publics.utils.Utils;

/**
* @ClassName: CrashHandler
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-26 下午10:34:13
* 
*/

public class CrashHandler implements UncaughtExceptionHandler {

	private Context mContext;
	private UncaughtExceptionHandler mDefaultHandler;
	private static volatile CrashHandler mCrashHandler;
	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	/******************************单例开始********************************/

	private CrashHandler() {
		// TODO Auto-generated constructor stub
	}

	public static CrashHandler getInstance() {
		if (mCrashHandler == null) {
			synchronized (CrashHandler.class) {
				if (mCrashHandler == null) {
					mCrashHandler = new CrashHandler();
				}
			}
		}
		return mCrashHandler;
	}

	/******************************单例结束********************************/

	public void init(Context context) {
		mContext = context;
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该 CrashHandler 为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		//collectDeviceInfo(mContext);
		handleException(ex);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 退出程序
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
			}
		}, 500);

	}

	/**
	 * 
	* @Title: handleException 
	* @Description: 处理错误信息
	* @param @param errorMsg    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void handleException(Throwable ex) {

		StringBuffer sb = new StringBuffer();

		sb.append("==================" + "\n");

		sb.append("VersionCode = " + "v" + Utils.getVersionCode(mContext) + "\n");
		sb.append(" , System = " + Build.VERSION.RELEASE + "\n");
		sb.append(" , Model = " + Utils.getMobileType() + "\n");

		sb.append("=================" + "\n");

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();

		String result = writer.toString();
		sb.append(result);

		// 发送错误报告
		sendErrorInfo2Server(sb.toString());

		try {
			long timestamp = System.currentTimeMillis();
			String time = formatter.format(new Date());
			String fileName = "crash-" + time + "-" + timestamp + ".log";

			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				// 文件存储路径
				String path = StorageUtils.CRASH_PATH;
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}

		} catch (Exception e) {
			// Log.e(TAG, "an error occured while writing file...", e);
		}
	}

	/** 
	* @Title: sendErrorInfo2Server 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param string    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void sendErrorInfo2Server(final String content) {
		CrashRequest request = new CrashRequest();
		request.setContent(content);
		DataFetcher.getInstance().PostCrashResult(request, null, null);
	}
}
