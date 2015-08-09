/**   
* @Title: GetValidateCodeRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-25 下午9:15:53
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: GetValidateCodeRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-25 下午9:15:53
* 
*/

public class GetValidateCodeRequest {

	public String mobileNum;

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

}
