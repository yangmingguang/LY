/**   
* @Title: WonderfulAdDetailResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-13 上午9:31:33
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

/**
* @ClassName: WonderfulAdDetailResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-13 上午9:31:33
* 
*/

public class WonderfulAdDetailResponse extends CommonResponse {

	public int id;
	public int sortId;
	public String title;
	public String picUrl;
	public String phone;
	public String webSite;

	// 视频广告
	public String voideUrl;
	public String voideTime;
	public String voideIntroduction;

}
