/**   
* @Title: UserDataRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-5 下午6:32:29
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: UserDataRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-5 下午6:32:29
* 
*/

public class UserDataRequest {

	public int userId;
	public String userPwd;
	public int sex;
	public int marriage;
	public int age;
	public int income;
	public int occupation;
	public String field;
	public String urlStream;
	public String picName;

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
	* @return sex 
	*/

	public int getSex() {
		return sex;
	}

	/** 
	* @param sex 要设置的 sex 
	*/

	public void setSex(int sex) {
		this.sex = sex;
	}

	/** 
	* @return marriage 
	*/

	public int getMarriage() {
		return marriage;
	}

	/** 
	* @param marriage 要设置的 marriage 
	*/

	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}

	/** 
	* @return age 
	*/

	public int getAge() {
		return age;
	}

	/** 
	* @param age 要设置的 age 
	*/

	public void setAge(int age) {
		this.age = age;
	}

	/** 
	* @return income 
	*/

	public int getIncome() {
		return income;
	}

	/** 
	* @param income 要设置的 income 
	*/

	public void setIncome(int income) {
		this.income = income;
	}

	/** 
	* @return occupation 
	*/

	public int getOccupation() {
		return occupation;
	}

	/** 
	* @param occupation 要设置的 occupation 
	*/

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	/** 
	* @return urlStream 
	*/

	public String getUrlStream() {
		return urlStream;
	}

	/** 
	* @param urlStream 要设置的 urlStream 
	*/

	public void setUrlStream(String urlStream) {
		this.urlStream = urlStream;
	}

	/** 
	* @return picName 
	*/

	public String getPicName() {
		return picName;
	}

	/** 
	* @param picName 要设置的 picName 
	*/

	public void setPicName(String picName) {
		this.picName = picName;
	}

	/** 
	* @return field 
	*/

	public String getField() {
		return field;
	}

	/** 
	* @param field 要设置的 field 
	*/

	public void setField(String field) {
		this.field = field;
	}

}
