/**   
* @Title: StorageUtils.java
* @Package com.yj.ecard.publics.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-26 下午11:08:40
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

import java.io.File;

import android.os.Environment;

/**
* @ClassName: StorageUtils
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-26 下午11:08:40
* 
*/

public class StorageUtils {

	public static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
	public static final String FILE_ROOT = SDCARD_ROOT + File.separator + "leying" + File.separator;
	public static final String CRASH_PATH = FILE_ROOT + "LCrash" + File.separator;
	public static final String DOWNLOAD_PATH = FILE_ROOT + "Download" + File.separator;
	public static final String IMAGE_PATH = FILE_ROOT + "Image" + File.separator;

	/**
	* @Title: initDirs 
	* @Description: 初始化文件夹
	* @param     
	* @return void
	 */
	public static void initDirs() {
		try {

			File crashFile = new File(CRASH_PATH);
			if (!crashFile.exists() || !crashFile.isDirectory())
				crashFile.mkdirs();

			File downloadFile = new File(DOWNLOAD_PATH);
			if (!downloadFile.exists() || !downloadFile.isDirectory())
				downloadFile.mkdirs();

			File imageFile = new File(IMAGE_PATH);
			if (!imageFile.exists() || !imageFile.isDirectory())
				imageFile.mkdirs();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
