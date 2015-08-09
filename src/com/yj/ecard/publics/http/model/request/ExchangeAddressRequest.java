/**   
* @Title: ExchangeAddressRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-27 上午12:59:30
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ExchangeAddressRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-27 上午12:59:30
* 
*/

public class ExchangeAddressRequest {

	public int proId;
	public int userId;
	public String realName;
	public String mobile;
	public String postNum;
	public String address;
	public String sign;

	/** 
	* @return proId 
	*/

	public int getProId() {
		return proId;
	}

	/** 
	* @param proId 要设置的 proId 
	*/

	public void setProId(int proId) {
		this.proId = proId;
	}

	/** 
	* @return userId 
	*/

	public int getUserId() {
		return userId;
	}

	/** 
	* @param userId 要设置的 userId 
	*/

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/** 
	* @return realName 
	*/

	public String getRealName() {
		return realName;
	}

	/** 
	* @param realName 要设置的 realName 
	*/

	public void setRealName(String realName) {
		this.realName = realName;
	}

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
	* @return postNum 
	*/

	public String getPostNum() {
		return postNum;
	}

	/** 
	* @param postNum 要设置的 postNum 
	*/

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	/** 
	* @return address 
	*/

	public String getAddress() {
		return address;
	}

	/** 
	* @param address 要设置的 address 
	*/

	public void setAddress(String address) {
		this.address = address;
	}

	/** 
	* @return sign 
	*/

	public String getSign() {
		return sign;
	}

	/** 
	* @param sign 要设置的 sign 
	*/

	public void setSign(String sign) {
		this.sign = sign;
	}

}
