/**   
* @Title: AddressListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-26 下午8:05:03
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: AddressListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-26 下午8:05:03
* 
*/

public class AddressListRequest {

	public int userId;
	public String userPwd;

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
