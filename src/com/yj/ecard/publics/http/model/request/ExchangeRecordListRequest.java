/**   
* @Title: ExchangeRecordListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-7 上午10:38:51
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: ExchangeRecordListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-7 上午10:38:51
* 
*/

public class ExchangeRecordListRequest extends CommonRequest {

	public String userName;

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

}
