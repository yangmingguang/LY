/**   
* @Title: BannerInfoResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-21 下午1:02:27
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.BannerInfoBean;

/**
* @ClassName: BannerInfoResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-21 下午1:02:27
* 
*/

public class BannerResponse extends CommonResponse {

	public int count;
	public List<BannerInfoBean> list;

}
