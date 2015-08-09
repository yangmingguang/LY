package com.yj.ecard.publics.utils;

import android.util.Log;

/**
 * 
* @ClassName: LogUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午3:52:36
*
 */

public class LogUtil {

	private static LogUtil logger;
	private static boolean debug = true;
	public static int logLevel = Log.VERBOSE;
	private static final String tag = "LogUtil";

	private LogUtil() {
		// TODO Auto-generated constructor stub
	}

	public static LogUtil getLogger() {
		if (logger == null)
			logger = new LogUtil();
		return logger;
	}

	private String getFunctionName() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();

		if (sts == null)
			return null;

		for (StackTraceElement st : sts) {
			if (st.isNativeMethod())
				continue;
			if (st.getClassName().equals(Thread.class.getName()))
				continue;
			if (st.getClassName().equals(this.getClass().getName()))
				continue;

			return "[ " + Thread.currentThread().getName() + ": " + st.getFileName() + ":" + st.getLineNumber() + " ]";
		}
		return null;
	}

	public void d(Object str) {
		if (!debug)
			return;
		if (logLevel <= Log.DEBUG) {
			String msg = "";
			String name = getFunctionName();
			if (name != null) {
				msg = name + " - " + str;
			} else {
				msg = str.toString();
			}
			Log.d(tag, msg);
		}
	}
}
