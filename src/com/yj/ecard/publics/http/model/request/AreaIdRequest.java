/**   
* @Title: AreaIdRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-17 下午10:46:47
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: AreaIdRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-17 下午10:46:47
* 
*/

public class AreaIdRequest {

	public double lat;
	public double lng;
	public int userId;
	public String userPwd;

	/** 
	* @return lat 
	*/

	public double getLat() {
		return lat;
	}

	/** 
	* @param lat 要设置的 lat 
	*/

	public void setLat(double lat) {
		this.lat = lat;
	}

	/** 
	* @return lng 
	*/

	public double getLng() {
		return lng;
	}

	/** 
	* @param lng 要设置的 lng 
	*/

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
