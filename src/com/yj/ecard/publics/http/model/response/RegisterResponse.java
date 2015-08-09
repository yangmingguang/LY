/**   
* @Title: RegisterResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-21 下午4:06:07
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

/**
* @ClassName: RegisterResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-21 下午4:06:07
* 
*/

public class RegisterResponse extends CommonResponse {

	public int userId;
	public String userPwd;
	public String userName;
	public String realName;
	public String token;
	public double amount;
	public int level;
	public int sex;
	public int age;
	public int professional;
	public double income;
	public String field;
	public String avatar;
	public int province;
	public int city;
	public int country;
	public int areaId;

}
