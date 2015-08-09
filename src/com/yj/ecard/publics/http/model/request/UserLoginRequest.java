/**   
* @Title: UserLoginRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-19 下午10:22:55
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: UserLoginRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-19 下午10:22:55
* 
*/

public class UserLoginRequest {

	public String userName;
	public String userPwd;
	public double lat;
	public double lng;
	public String mobileType;
	public String mobileImei;

	/** 
	* @return userName 
	*/

	public String getUserName() {
		return userName;
	}

	/** 
	* @param userName 要设置的 userName 
	*/

	public void setUserName(String userName) {
		this.userName = userName;
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

	/** 
	* @return mobileType 
	*/

	public String getMobileType() {
		return mobileType;
	}

	/** 
	* @param mobileType 要设置的 mobileType 
	*/

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	/** 
	* @return mobileImei 
	*/

	public String getMobileImei() {
		return mobileImei;
	}

	/** 
	* @param mobileImei 要设置的 mobileImei 
	*/

	public void setMobileImei(String mobileImei) {
		this.mobileImei = mobileImei;
	}

}
