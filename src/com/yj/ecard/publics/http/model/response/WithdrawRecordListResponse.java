/**   
* @Title: WithdrawRecordListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-18 下午4:41:10
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.WithdrawBean;

/**
* @ClassName: WithdrawRecordListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-18 下午4:41:10
* 
*/

public class WithdrawRecordListResponse extends CommonResponse {

	public int count;
	public boolean isLast;
	public List<WithdrawBean> data;

}
