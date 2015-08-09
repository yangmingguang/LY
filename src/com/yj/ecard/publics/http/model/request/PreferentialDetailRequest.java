/**   
* @Title: PreferentialDetailRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-27 下午2:58:58
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: PreferentialDetailRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-27 下午2:58:58
* 
*/

public class PreferentialDetailRequest {

	public int id;
	public int userId;
	public String userName;
	public String password;

	/** 
	* @return id 
	*/

	public int getId() {
		return id;
	}

	/** 
	* @param id 要设置的 id 
	*/

	public void setId(int id) {
		this.id = id;
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
	* @return password 
	*/

	public String getPassword() {
		return password;
	}

	/** 
	* @param password 要设置的 password 
	*/

	public void setPassword(String password) {
		this.password = password;
	}

}
