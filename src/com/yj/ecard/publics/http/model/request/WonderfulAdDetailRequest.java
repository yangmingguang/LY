/**   
* @Title: WonderfulAdDetailRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-13 上午9:30:40
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: WonderfulAdDetailRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-13 上午9:30:40
* 
*/

public class WonderfulAdDetailRequest {

	public int id;
	public int sortId;

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

}
