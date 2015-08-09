/**   
* @Title: BalanceRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-27 下午3:31:07
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: BalanceRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-27 下午3:31:07
* 
*/

public class BalanceRequest {

	public int userId;
	public String userName;
	public String passWord;

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
	* @return passWord 
	*/

	public String getPassWord() {
		return passWord;
	}

	/** 
	* @param passWord 要设置的 passWord 
	*/

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
