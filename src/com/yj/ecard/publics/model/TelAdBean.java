/**   
* @Title: TelAdBean.java
* @Package com.yj.ecard.publics.model
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-31 下午3:38:09
* @version V1.0   
*/

package com.yj.ecard.publics.model;

/**
* @ClassName: TelAdBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 下午3:38:09
* 
*/

public class TelAdBean {

	public int id;
	public String smallUrl;
	public String largeUrl;
	public String smallLocalPath;
	public String largeLocalPath;
	public boolean smallPicDownload;
	public boolean largePicDownload;

	/** 
	* @return id 
	*/

	public int getId() {
		return id;
	}

	/** 
	* @param id 要设置的 id 
	*/

	public void setId(int id) {
		this.id = id;
	}

	/** 
	* @return smallUrl 
	*/

	public String getSmallUrl() {
		return smallUrl;
	}

	/** 
	* @param smallUrl 要设置的 smallUrl 
	*/

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	/** 
	* @return largeUrl 
	*/

	public String getLargeUrl() {
		return largeUrl;
	}

	/** 
	* @param largeUrl 要设置的 largeUrl 
	*/

	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}

	/** 
	* @return smallLocalPath 
	*/

	public String getSmallLocalPath() {
		return smallLocalPath;
	}

	/** 
	* @param smallLocalPath 要设置的 smallLocalPath 
	*/

	public void setSmallLocalPath(String smallLocalPath) {
		this.smallLocalPath = smallLocalPath;
	}

	/** 
	* @return largeLocalPath 
	*/

	public String getLargeLocalPath() {
		return largeLocalPath;
	}

	/** 
	* @param largeLocalPath 要设置的 largeLocalPath 
	*/

	public void setLargeLocalPath(String largeLocalPath) {
		this.largeLocalPath = largeLocalPath;
	}

	/** 
	* @return smallPicDownload 
	*/

	public boolean isSmallPicDownload() {
		return smallPicDownload;
	}

	/** 
	* @param smallPicDownload 要设置的 smallPicDownload 
	*/

	public void setSmallPicDownload(boolean smallPicDownload) {
		this.smallPicDownload = smallPicDownload;
	}

	/** 
	* @return largePicDownload 
	*/

	public boolean isLargePicDownload() {
		return largePicDownload;
	}

	/** 
	* @param largePicDownload 要设置的 largePicDownload 
	*/

	public void setLargePicDownload(boolean largePicDownload) {
		this.largePicDownload = largePicDownload;
	}

}
