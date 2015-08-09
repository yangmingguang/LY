/**   
* @Title: ExchangeListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午12:11:03
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.ExchangeBean;

/**
* @ClassName: ExchangeListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午12:11:03
* 
*/

public class ExchangeListResponse extends CommonResponse {

	public int count;
	public boolean isLast;
	public List<ExchangeBean> data;

}
