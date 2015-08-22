/**   
* @Title: ProductDetailResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-22 下午4:30:11
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

/**
* @ClassName: ProductDetailResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-22 下午4:30:11
* 
*/

public class ProductDetailResponse extends CommonResponse {

	public int canBuy;
	public String title;
	public double price;
	public double marketPrice;
	public String introduce;
	public String merchantsName;
	public String phone;
	public String address;
	public String distance;
	public String imgUrl;

}
