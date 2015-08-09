/**   
* @Title: XorPlus.java
* @Package com.iframe.source.publics.http.volley.toolbox
* @Description: TODO(用一句话描述该文件做什么)
* @author mingguang.yang   
* @date 2015年1月22日 上午10:50:55
* @version V1.0   
*/

package com.yj.ecard.publics.http.volley.toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
* @ClassName: XorPlus
* @Description: 协议参数加密类
* @author mingguang.yang
* @date 2015年1月22日 上午10:50:55
* 
*/

public class XorPlus {
	static String secret = "iframesourcesystem";

	/**
	 * 
	* @Title: encrypt 
	* @Description: 协议加密
	* @param @param mingwen 明文
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String encrypt(String mingwen) {
		char str[] = mingwen.toCharArray();
		int k;
		for (k = 0; k < str.length; k++) {
			str[k] = (char) (str[k] ^ secret.charAt(k % 8));
		}
		return new String(str);
	}

	/**
	 * 
	* @Title: decrypt 
	* @Description: 协议解密
	* @param @param miwen 密文
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String decrypt(String miwen) {
		char str[] = miwen.toCharArray();
		for (int k = 0; k < str.length; k++) {
			str[k] = (char) (str[k] ^ secret.charAt(k % 8));
		}
		String t = new String(str);
		return t;
	}

	public static String inputStreamDecrypt(InputStream is) {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decrypt(buffer.toString());
	}
}
