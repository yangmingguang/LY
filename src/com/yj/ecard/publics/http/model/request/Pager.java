/**   
* @Title: Pager.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-6 上午10:33:26
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

import com.yj.ecard.publics.utils.Constan;

/**
* @ClassName: Pager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-6 上午10:33:26
* 
*/

public class Pager {

	public int pageSize;
	public int pageIndex;

	public Pager(int pageIndex) {
		this.pageIndex = pageIndex;
		this.pageSize = Constan.PAGE_SIZE;
	}

	/** 
	* @return pageSize 
	*/

	public int getPageSize() {
		return pageSize;
	}

	/** 
	* @param pageSize 要设置的 pageSize 
	*/

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/** 
	* @return pageIndex 
	*/

	public int getPageIndex() {
		return pageIndex;
	}

	/** 
	* @param pageIndex 要设置的 pageIndex 
	*/

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

}
