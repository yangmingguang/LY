/**   
* @Title: CheckCodeRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-21 下午3:10:06
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: CheckCodeRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-21 下午3:10:06
* 
*/

public class CheckCodeRequest {

	public String mobile;
	public String mobileCode;

	/** 
	* @return mobile 
	*/

	public String getMobile() {
		return mobile;
	}

	/** 
	* @param mobile 要设置的 mobile 
	*/

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 
	* @return mobileCode 
	*/

	public String getMobileCode() {
		return mobileCode;
	}

	/** 
	* @param mobileCode 要设置的 mobileCode 
	*/

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

}
