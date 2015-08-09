/**   
* @Title: ResetPwRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-25 下午10:10:44
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ResetPwRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-25 下午10:10:44
* 
*/

public class ResetPwRequest {

	public String mobileNum;
	public String verifyCode;
	public String newPassword;

	/** 
	* @return mobileNum 
	*/

	public String getMobileNum() {
		return mobileNum;
	}

	/** 
	* @param mobileNum 要设置的 mobileNum 
	*/

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	/** 
	* @return verifyCode 
	*/

	public String getVerifyCode() {
		return verifyCode;
	}

	/** 
	* @param verifyCode 要设置的 verifyCode 
	*/

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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
