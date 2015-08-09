/**   
* @Title: CommonRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-14 上午8:18:24
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: CommonRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-14 上午8:18:24
* 
*/

public class CommonRequest {

	public Pager pager;

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
