/**   
* @Title: DailyAttendanceRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-2 下午9:14:34
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: DailyAttendanceRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-2 下午9:14:34
* 
*/

public class DailyAttendanceRequest {

	public int userId;
	public String userPwd;
	public double lng;
	public double lat;

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
	* @return userPwd 
	*/

	public String getUserPwd() {
		return userPwd;
	}

	/** 
	* @param userPwd 要设置的 userPwd 
	*/

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

}
