/**   
* @Title: UpdateRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-29 下午2:33:17
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: UpdateRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-29 下午2:33:17
* 
*/

public class UpdateRequest {

	public int versionCode;

	/** 
	* @return versionCode 
	*/

	public int getVersionCode() {
		return versionCode;
	}

	/** 
	* @param versionCode 要设置的 versionCode 
	*/

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

}
