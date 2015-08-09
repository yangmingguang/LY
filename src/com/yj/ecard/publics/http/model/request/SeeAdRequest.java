/**   
* @Title: SeeAdRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-10 下午5:55:41
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: SeeAdRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-10 下午5:55:41
* 
*/

public class SeeAdRequest {

	public int advId;
	public int userId;
	public int sortId;
	public int sort;
	public String sign;

	/** 
	* @return advId 
	*/

	public int getAdvId() {
		return advId;
	}

	/** 
	* @param advId 要设置的 advId 
	*/

	public void setAdvId(int advId) {
		this.advId = advId;
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
	* @return sort 
	*/

	public int getSort() {
		return sort;
	}

	/** 
	* @param sort 要设置的 sort 
	*/

	public void setSort(int sort) {
		this.sort = sort;
	}

	/** 
	* @return sign 
	*/

	public String getSign() {
		return sign;
	}

	/** 
	* @param sign 要设置的 sign 
	*/

	public void setSign(String sign) {
		this.sign = sign;
	}

}
