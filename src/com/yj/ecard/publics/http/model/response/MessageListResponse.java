/**   
* @Title: MessageListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-11-7 下午11:07:51
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.MessageBean;

/**
* @ClassName: MessageListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-11-7 下午11:07:51
* 
*/

public class MessageListResponse extends CommonResponse {

	public boolean isLast;
	public List<MessageBean> data;

}
