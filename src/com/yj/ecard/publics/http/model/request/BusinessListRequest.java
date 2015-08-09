/**   
* @Title: BusinessListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午3:00:22
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: BusinessListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午3:00:22
* 
*/

public class BusinessListRequest extends CommonRequest {

	public int areaId;
	public int sortId;
	public double latitude;
	public double longitude;

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
	* @return latitude 
	*/

	public double getLatitude() {
		return latitude;
	}

	/** 
	* @param latitude 要设置的 latitude 
	*/

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/** 
	* @return longitude 
	*/

	public double getLongitude() {
		return longitude;
	}

	/** 
	* @param longitude 要设置的 longitude 
	*/

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
