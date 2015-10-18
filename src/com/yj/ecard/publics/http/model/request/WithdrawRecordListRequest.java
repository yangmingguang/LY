/**   
* @Title: WithdrawRecordListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-18 下午4:38:46
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: WithdrawRecordListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-18 下午4:38:46
* 
*/

public class WithdrawRecordListRequest extends CommonRequest {

	public String userPwd;
	public String userName;

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
