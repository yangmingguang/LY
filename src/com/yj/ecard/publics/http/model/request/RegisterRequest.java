/**   
* @Title: RegisterRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-21 下午4:05:04
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: RegisterRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-21 下午4:05:04
* 
*/

public class RegisterRequest extends UserLoginRequest {

	public String referee;

	/** 
	* @return referee 
	*/

	public String getReferee() {
		return referee;
	}

	/** 
	* @param referee 要设置的 referee 
	*/

	public void setReferee(String referee) {
		this.referee = referee;
	}

}
