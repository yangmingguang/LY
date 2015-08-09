/**   
* @Title: SeckillRecordRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-28 下午12:42:27
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: SeckillRecordRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-28 下午12:42:27
* 
*/

public class SeckillRecordRequest extends CommonRequest {

	public int userId;
	public String userName;

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

}
