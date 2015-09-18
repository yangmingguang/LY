/**   
* @Title: SortListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-9-19 上午12:16:53
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: SortListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-9-19 上午12:16:53
* 
*/

public class SortListRequest {

	public int type;
	public int areaId;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
