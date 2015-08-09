/**   
* @Title: ScreenLockBean.java
* @Package com.yj.ecard.publics.model
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-29 下午11:19:01
* @version V1.0   
*/

package com.yj.ecard.publics.model;

/**
* @ClassName: ScreenLockBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-29 下午11:19:01
* 
*/

public class ScreenLockBean {

	public int id;
	public String title;
	public String imgUrl;
	public String webUrl;
	public String screenLockLocalPath;
	public boolean screenLockPicDownload;

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
	* @return title 
	*/

	public String getTitle() {
		return title;
	}

	/** 
	* @param title 要设置的 title 
	*/

	public void setTitle(String title) {
		this.title = title;
	}

	/** 
	* @return imgUrl 
	*/

	public String getImgUrl() {
		return imgUrl;
	}

	/** 
	* @param imgUrl 要设置的 imgUrl 
	*/

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** 
	* @return webUrl 
	*/

	public String getWebUrl() {
		return webUrl;
	}

	/** 
	* @param webUrl 要设置的 webUrl 
	*/

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	/** 
	* @return screenLockLocalPath 
	*/

	public String getScreenLockLocalPath() {
		return screenLockLocalPath;
	}

	/** 
	* @param screenLockLocalPath 要设置的 screenLockLocalPath 
	*/

	public void setScreenLockLocalPath(String screenLockLocalPath) {
		this.screenLockLocalPath = screenLockLocalPath;
	}

	/** 
	* @return screenLockPicDownload 
	*/

	public boolean isScreenLockPicDownload() {
		return screenLockPicDownload;
	}

	/** 
	* @param screenLockPicDownload 要设置的 screenLockPicDownload 
	*/

	public void setScreenLockPicDownload(boolean screenLockPicDownload) {
		this.screenLockPicDownload = screenLockPicDownload;
	}

}
