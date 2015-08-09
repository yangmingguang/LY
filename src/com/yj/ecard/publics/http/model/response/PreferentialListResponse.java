/**   
* @Title: PreferentialListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午11:19:01
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.PreferentialBean;

/**
* @ClassName: PreferentialListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午11:19:01
* 
*/

public class PreferentialListResponse extends CommonResponse {

	public int count;
	public boolean isLast;
	public List<PreferentialBean> data;

}
