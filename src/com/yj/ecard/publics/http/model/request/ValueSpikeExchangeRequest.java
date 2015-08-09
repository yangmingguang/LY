/**   
* @Title: ValueSpikeExchangeRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-28 上午10:43:49
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ValueSpikeExchangeRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-28 上午10:43:49
* 
*/

public class ValueSpikeExchangeRequest {

	public int proId;
	public String phone;
	public String postNum;
	public String address;
	public String userName;
	public String realName;
	public String userPwd;

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
	* @return phone 
	*/

	public String getPhone() {
		return phone;
	}

	/** 
	* @param phone 要设置的 phone 
	*/

	public void setPhone(String phone) {
		this.phone = phone;
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
	* @return userPwd 
	*/

	public String getUserPwd() {
		return userPwd;
	}

	/** 
	* @param userPwd 要设置的 userPwd 
	*/

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
