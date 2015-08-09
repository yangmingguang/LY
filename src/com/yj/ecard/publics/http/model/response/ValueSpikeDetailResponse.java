/**   
* @Title: ValueSpikeDetailResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午3:15:59
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.ExchangePicBean;

/**
* @ClassName: ValueSpikeDetailResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午3:15:59
* 
*/

public class ValueSpikeDetailResponse extends CommonResponse {

	public String title;
	public String freight;
	public String introduce;
	public double marketPrice;
	public double price;
	public boolean canbuy;
	public List<ExchangePicBean> picList;

}
