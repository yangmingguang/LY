/**   
* @Title: PreferentialListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午11:22:48
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: PreferentialListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午11:22:48
* 
*/

public class PreferentialListRequest extends CommonRequest {

	public int userId;
	public int areaId;

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
	* @return areaId 
	*/

	public int getAreaId() {
		return areaId;
	}

	/** 
	* @param areaId 要设置的 areaId 
	*/

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

}
