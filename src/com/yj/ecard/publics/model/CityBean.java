/**   
* @Title: CityBean.java
* @Package com.yj.ecard.publics.model
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-7 上午8:36:55
* @version V1.0   
*/

package com.yj.ecard.publics.model;

/**
* @ClassName: CityBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-7 上午8:36:55
* 
*/

public class CityBean {

	private int id;
	private String letter;
	private String cityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
