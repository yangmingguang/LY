/**   
* @Title: PushRecordResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang  
* @date Oct 30, 2015 4:30:38 PM
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

/**
* @ClassName: PushRecordResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date Oct 30, 2015 4:30:38 PM
* 
*/

public class PushRecordResponse {

	public int type;
	public int userId;
	public String title;
	public String content;
	public String addTime;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
