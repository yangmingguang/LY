/**   
* @Title: ExchangeRecordListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-7 上午10:36:51
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.ExchangeRecordBean;

/**
* @ClassName: ExchangeRecordListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-7 上午10:36:51
* 
*/

public class ExchangeRecordListResponse extends CommonResponse {

	public int count;
	public boolean isLast;
	public List<ExchangeRecordBean> data;

}
