/**   
* @Title: DeletePreferentialRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-29 下午10:17:47
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: DeletePreferentialRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-29 下午10:17:47
* 
*/

public class DeletePreferentialRequest {

	public int id;
	public String userName;
	public String passWord;

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
