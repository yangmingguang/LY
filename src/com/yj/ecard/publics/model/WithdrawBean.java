/**   
* @Title: WithdrawBean.java
* @Package com.yj.ecard.publics.model
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-10-18 下午4:42:18
* @version V1.0   
*/

package com.yj.ecard.publics.model;

/**
* @ClassName: WithdrawBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-10-18 下午4:42:18
* 
*/

public class WithdrawBean {

	public int id;
	public String realName;
	public String bankCardnum;
	public String cashAmount;
	public int state; // 1=成功、2=处理中
	public String stateMsg;
	public String addTime;

}
