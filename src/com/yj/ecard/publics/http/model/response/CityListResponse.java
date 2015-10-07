/**   
* @Title: CityListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-7 上午8:40:53
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.CityBean;

/**
* @ClassName: CityListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-7 上午8:40:53
* 
*/

public class CityListResponse extends CommonResponse {

	public List<CityBean> data;

}
