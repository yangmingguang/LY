package com.yj.ecard.publics.utils;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.pm.ApplicationInfo.FLAG_LARGE_HEAP;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.HONEYCOMB;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

/**
 * 
* @ClassName: ImageLoaderUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午3:16:26
*
 */

public class ImageLoaderUtil {
	private static DisplayImageOptions options;

	@TargetApi(HONEYCOMB)
	private static class ActivityManagerHoneycomb {
		static int getLargeMemoryClass(ActivityManager activityManager) {
			return activityManager.getLargeMemoryClass();
		}
	}

	public static int calculateMemoryCacheSize(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
		boolean largeHeap = (context.getApplicationInfo().flags & FLAG_LARGE_HEAP) != 0;
		int memoryClass = am.getMemoryClass();
		if (largeHeap && SDK_INT >= HONEYCOMB) {
			memoryClass = ActivityManagerHoneycomb.getLargeMemoryClass(am);
		}
		// Target ~15% of the available heap.
		// LogUtil.getLogger().d("LruCache size ======" + (memoryClass / 7) + "M");
		return 1024 * 1024 * memoryClass / 7;
	}

	public static void load(Context context, ImageType imageType, String path, int placeholderResId, int errorResId,
			ImageView target) {
		options = new DisplayImageOptions.Builder().showImageOnLoading(placeholderResId)
				.bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				.cacheInMemory(true).showImageForEmptyUri(placeholderResId).showImageOnFail(errorResId)
				.cacheOnDisc(true).considerExifParams(false).displayer(new SimpleBitmapDisplayer()).build();
		if (imageType == ImageType.APK || imageType == ImageType.APP || imageType == ImageType.LOCALPIC) {
			if (imageType == ImageType.LOCALPIC) {
				path = Scheme.FILE.wrap(path);
			}
			ImageLoader.getInstance().displayImage(context, true, imageType, path, target, options, null);
		} else {
			ImageLoader.getInstance().displayImage(context, true, imageType, path, target, options, null);
		}
	}

	public static void cancleDisplay(ImageView iamgeView, Context context) {
		ImageLoader.getInstance().cancelDisplayTask(iamgeView);
	}

	public static void pauseDisplay() {
		ImageLoader.getInstance().pause();
	}

	public static void resumeDisplay() {
		ImageLoader.getInstance().resume();
	}

}
