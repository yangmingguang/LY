/**   
* @Title: BusinessDetailRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午9:43:55
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: BusinessDetailRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午9:43:55
* 
*/

public class BusinessDetailRequest {

	public int id;
	public double latitude;
	public double longitude;

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
