/**   
* @Title: ValidateCodeRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-21 上午11:28:07
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ValidateCodeRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-21 上午11:28:07
* 
*/

public class ValidateCodeRequest {

	public String phoneNum;
	public String mobileImei;

	/** 
	* @return phoneNum 
	*/

	public String getPhoneNum() {
		return phoneNum;
	}

	/** 
	* @param phoneNum 要设置的 phoneNum 
	*/

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/** 
	* @return mobileImei 
	*/

	public String getMobileImei() {
		return mobileImei;
	}

	/** 
	* @param mobileImei 要设置的 mobileImei 
	*/

	public void setMobileImei(String mobileImei) {
		this.mobileImei = mobileImei;
	}

}
