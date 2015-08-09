/**   
* @Title: WonderfulAdListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-11 下午9:49:40
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: WonderfulAdListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-11 下午9:49:40
* 
*/

public class WonderfulAdListRequest {

	public int sortId;
	public int userId;
	public String userName;
	public Pager pager;

	/** 
	* @return sortId 
	*/

	public int getSortId() {
		return sortId;
	}

	/** 
	* @param sortId 要设置的 sortId 
	*/

	public void setSortId(int sortId) {
		this.sortId = sortId;
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
	* @return pager 
	*/

	public Pager getPager() {
		return pager;
	}

	/** 
	* @param pager 要设置的 pager 
	*/

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
