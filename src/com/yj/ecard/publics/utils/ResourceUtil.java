package com.yj.ecard.publics.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 
* @ClassName: ResourceUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-5 下午10:25:54
*
 */
public class ResourceUtil {

	public static String getString(Context context, int id) {
		return context.getResources().getString(id);
	}

	public static String getString(Context context, int id, String... formatArgs) {
		return context.getResources().getString(id, formatArgs);
	}

	public static int getInteger(Context context, int id) {
		return context.getResources().getInteger(id);
	}

	public static int getDimensionPixelSize(Context context, int id) {
		return context.getResources().getDimensionPixelSize(id);
	}

	public static float getDimension(Context context, int id) {
		return context.getResources().getDimension(id);
	}

	public static Drawable getDrawable(Context context, int id) {
		return context.getResources().getDrawable(id);
	}
}
