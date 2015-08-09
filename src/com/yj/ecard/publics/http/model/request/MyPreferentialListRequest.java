/**   
* @Title: MyPreferentialListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-7 下午11:03:49
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: MyPreferentialListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-7 下午11:03:49
* 
*/

public class MyPreferentialListRequest {

	public int userId;
	public Pager pager;

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
