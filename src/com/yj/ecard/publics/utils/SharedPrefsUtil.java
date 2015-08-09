/**   
* @Title: SharedPrefsUtil.java
* @Package com.iframe.source.publics.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author mingguang.yang   
* @date 2015年1月19日 下午1:50:16
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
* @ClassName: SharedPrefsUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author mingguang.yang
* @date 2015年1月19日 下午1:50:16
* 
*/

public class SharedPrefsUtil {

	private static final int MODE = Context.MODE_PRIVATE;
	private static final String SETTINGS = "leying_config";

	public static void putValue(Context context, String key, int value) {
		Editor sp = context.getSharedPreferences(SETTINGS, MODE).edit();
		sp.putInt(key, value);
		sp.commit();
	}

	public static void putValue(Context context, String key, String value) {
		Editor sp = context.getSharedPreferences(SETTINGS, MODE).edit();
		sp.putString(key, value);
		sp.commit();
	}

	public static void putValue(Context context, String key, float value) {
		Editor sp = context.getSharedPreferences(SETTINGS, MODE).edit();
		sp.putFloat(key, value);
		sp.commit();
	}

	public static void putValue(Context context, String key, long value) {
		Editor sp = context.getSharedPreferences(SETTINGS, MODE).edit();
		sp.putLong(key, value);
		sp.commit();
	}

	public static void putValue(Context context, String key, boolean value) {
		Editor sp = context.getSharedPreferences(SETTINGS, MODE).edit();
		sp.putBoolean(key, value);
		sp.commit();
	}

	public static int getValue(Context context, String key, int defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTINGS, MODE);
		int value = sp.getInt(key, defValue);
		return value;
	}

	public static String getValue(Context context, String key, String defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTINGS, MODE);
		String value = sp.getString(key, defValue);
		return value;
	}

	public static float getValue(Context context, String key, Float defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTINGS, MODE);
		Float value = sp.getFloat(key, defValue);
		return value;
	}

	public static long getValue(Context context, String key, long defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTINGS, MODE);
		long value = sp.getLong(key, defValue);
		return value;
	}

	public static boolean getValue(Context context, String key, boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTINGS, MODE);
		boolean value = sp.getBoolean(key, defValue);
		return value;
	}

}
