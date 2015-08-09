/**   
* @Title: ValueSpikeListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-14 上午8:17:44
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ValueSpikeListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-14 上午8:17:44
* 
*/

public class ValueSpikeListRequest extends CommonRequest {

	public int periodTime;
	public int province;
	public int city;
	public int country;
	public int areaId;

	/** 
	* @return periodTime 
	*/

	public int getPeriodTime() {
		return periodTime;
	}

	/** 
	* @param periodTime 要设置的 periodTime 
	*/

	public void setPeriodTime(int periodTime) {
		this.periodTime = periodTime;
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

}
