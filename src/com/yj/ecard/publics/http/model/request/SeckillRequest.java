/**   
* @Title: SeckillRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-27 下午4:08:07
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: SeckillRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-27 下午4:08:07
* 
*/

public class SeckillRequest {

	public int city;
	public int country;
	public int areaId;
	public int province;

	/** 
	* @return city 
	*/

	public int getCity() {
		return city;
	}

	/** 
	* @param city 要设置的 city 
	*/

	public void setCity(int city) {
		this.city = city;
	}

	/** 
	* @return country 
	*/

	public int getCountry() {
		return country;
	}

	/** 
	* @param country 要设置的 country 
	*/

	public void setCountry(int country) {
		this.country = country;
	}

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
	* @return province 
	*/

	public int getProvince() {
		return province;
	}

	/** 
	* @param province 要设置的 province 
	*/

	public void setProvince(int province) {
		this.province = province;
	}

}
