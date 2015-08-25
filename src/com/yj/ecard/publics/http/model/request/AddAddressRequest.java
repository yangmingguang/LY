/**   
* @Title: AddAddressRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-25 下午11:24:45
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: AddAddressRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-25 下午11:24:45
* 
*/

public class AddAddressRequest {

	public String realName;
	public String phone;
	public String address;
	public int isDefault; // 0为不设置  1为设置默认
	public int userId;
	public String userPwd;

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
	* @return isDefault 
	*/

	public int getIsDefault() {
		return isDefault;
	}

	/** 
	* @param isDefault 要设置的 isDefault 
	*/

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
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
