/**   
* @Title: Base64Util.java
* @Package com.yj.ecard.publics.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-29 上午9:48:20
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
* @ClassName: Base64Util
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-29 上午9:48:20
* 
*/

public class Base64Util {

	/**
	 * 
	* @Title: imgaeToBase64 
	* @Description: 图片转base64字符串
	* @param @param imgPath
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String imgaeToBase64(String imgPath) {
		Bitmap bitmap = null;
		if (imgPath != null && imgPath.length() > 0) {
			try {
				bitmap = BitmapFactory.decodeFile(imgPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		if (bitmap == null) {
			//bitmap not found!!
			return "";
		}
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

			out.flush();
			out.close();

			byte[] imgBytes = out.toByteArray();
			String str = Base64.encodeToString(imgBytes, Base64.DEFAULT);
			return str.replace("+", "%2B");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
