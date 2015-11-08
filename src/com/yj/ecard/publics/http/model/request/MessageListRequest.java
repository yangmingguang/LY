/**   
* @Title: MessageListRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-11-7 下午11:08:46
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: MessageListRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-11-7 下午11:08:46
* 
*/

public class MessageListRequest extends CommonRequest {

	public int id;
	public int type;
	public int userId;
	public String userPwd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
