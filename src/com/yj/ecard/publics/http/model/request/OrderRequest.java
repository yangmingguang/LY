/**   
* @Title: OrderRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-30 上午10:01:28
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: OrderRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-30 上午10:01:28
* 
*/

public class OrderRequest {

	public int userId;
	public int productId;
	public int orderType;
	public int isAddmyamont;
	public String sign;
	public String phone;
	public String address;
	public String realName;
	public String feedBack;

	/** 
	* @return orderType 
	*/

	public int getOrderType() {
		return orderType;
	}

	/** 
	* @param orderType 要设置的 orderType 
	*/

	public void setOrderType(int orderType) {
		this.orderType = orderType;
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
	* @return productId 
	*/

	public int getProductId() {
		return productId;
	}

	/** 
	* @param productId 要设置的 productId 
	*/

	public void setProductId(int productId) {
		this.productId = productId;
	}

	/** 
	* @return isAddmyamont 
	*/

	public int getIsAddmyamont() {
		return isAddmyamont;
	}

	/** 
	* @param isAddmyamont 要设置的 isAddmyamont 
	*/

	public void setIsAddmyamont(int isAddmyamont) {
		this.isAddmyamont = isAddmyamont;
	}

	/** 
	* @return realName 
	*/

	public String getRealName() {
		return realName;
	}

	/** 
	* @param realName 要设置的 realName 
	*/

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/** 
	* @return feedBack 
	*/

	public String getFeedBack() {
		return feedBack;
	}

	/** 
	* @param feedBack 要设置的 feedBack 
	*/

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	/** 
	* @return sign 
	*/

	public String getSign() {
		return sign;
	}

	/** 
	* @param sign 要设置的 sign 
	*/

	public void setSign(String sign) {
		this.sign = sign;
	}

}
