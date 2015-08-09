/**   
* @Title: PublishPreferentialRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-29 下午1:54:41
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: PublishPreferentialRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-29 下午1:54:41
* 
*/

public class PublishPreferentialRequest {

	public String title;
	public String userName;
	public String content;
	public String phone;
	public String address;
	public String picName;
	public String picStream;

	/** 
	* @return title 
	*/

	public String getTitle() {
		return title;
	}

	/** 
	* @param title 要设置的 title 
	*/

	public void setTitle(String title) {
		this.title = title;
	}

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
	* @return content 
	*/

	public String getContent() {
		return content;
	}

	/** 
	* @param content 要设置的 content 
	*/

	public void setContent(String content) {
		this.content = content;
	}

	/** 
	* @return phone 
	*/

	public String getPhone() {
		return phone;
	}

	/** 
	* @param phone 要设置的 phone 
	*/

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 
	* @return address 
	*/

	public String getAddress() {
		return address;
	}

	/** 
	* @param address 要设置的 address 
	*/

	public void setAddress(String address) {
		this.address = address;
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
	* @return picStream 
	*/

	public String getPicStream() {
		return picStream;
	}

	/** 
	* @param picStream 要设置的 picStream 
	*/

	public void setPicStream(String picStream) {
		this.picStream = picStream;
	}

}
