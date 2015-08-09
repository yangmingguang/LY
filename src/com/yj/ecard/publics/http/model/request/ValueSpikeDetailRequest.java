/**   
* @Title: ValueSpikeDetailRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午3:23:54
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ValueSpikeDetailRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午3:23:54
* 
*/

public class ValueSpikeDetailRequest {

	public int id;
	public int userId;

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

}
