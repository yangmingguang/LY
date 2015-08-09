/**   
* @Title: BusinessListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午2:57:27
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.BusinessBean;

/**
* @ClassName: BusinessListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午2:57:27
* 
*/

public class BusinessListResponse extends ListCommonResponse {

	public List<BusinessBean> data;

}
