/**   
* @Title: BusinessDetailResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午9:33:56
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.EnvironmentBean;
import com.yj.ecard.publics.model.ProductBean;

/**
* @ClassName: BusinessDetailResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午9:33:56
* 
*/

public class BusinessDetailResponse extends CommonResponse {

	public int id;
	public String merName;
	public String picUrl;
	public String introduction;
	public String phone;
	public String address;
	public String distance;
	public double latitude;
	public double longitude;
	public String preferential;
	public List<ProductBean> productList;
	public List<EnvironmentBean> envirList;

}
