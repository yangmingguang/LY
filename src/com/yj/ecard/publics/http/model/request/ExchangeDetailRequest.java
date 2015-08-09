/**   
* @Title: ProductDetailRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午3:23:54
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ProductDetailRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午3:23:54
* 
*/

public class ExchangeDetailRequest {

	public int id;
	public String username;

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
	* @return username 
	*/

	public String getUsername() {
		return username;
	}

	/** 
	* @param username 要设置的 username 
	*/

	public void setUsername(String username) {
		this.username = username;
	}

}
