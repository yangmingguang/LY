/**   
* @Title: ProductListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午12:12:14
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ProductListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午12:12:14
* 
*/

public class ExchangeListRequest {

	public int sortId;
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
