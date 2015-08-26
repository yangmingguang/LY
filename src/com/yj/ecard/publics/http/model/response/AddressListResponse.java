/**   
* @Title: AddressListResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-26 下午8:06:49
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

import java.util.List;

import com.yj.ecard.publics.model.AddressBean;

/**
* @ClassName: AddressListResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-26 下午8:06:49
* 
*/

public class AddressListResponse extends CommonResponse {

	public List<AddressBean> data;

}
