/**   
* @Title: ModifyPwRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-25 下午8:38:22
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ModifyPwRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-25 下午8:38:22
* 
*/

public class ModifyPwRequest {

	public String userName;
	public String oldPassword;
	public String newPassword;

	/** 
	* @return userName 
	*/

	public String getUserName() {
		return userName;
	}

	/** 
	* @param userName 要设置的 userName 
	*/

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 
	* @return oldPassword 
	*/

	public String getOldPassword() {
		return oldPassword;
	}

	/** 
	* @param oldPassword 要设置的 oldPassword 
	*/

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/** 
	* @return newPassword 
	*/

	public String getNewPassword() {
		return newPassword;
	}

	/** 
	* @param newPassword 要设置的 newPassword 
	*/

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
