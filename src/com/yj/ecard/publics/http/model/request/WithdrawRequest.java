/**   
* @Title: WithdrawRequest.java
* @Package com.yj.ecard.publics.http.model.request
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-27 上午11:32:53
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.request;

/**
* @ClassName: WithdrawRequest
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-27 上午11:32:53
* 
*/

public class WithdrawRequest {

	public String userName;
	public String userPwd;
	public String realName;
	public String bankName;
	public String bankCardnum;
	public double cashAmount;

	/** 
	* @return userName 
	*/

	public String getUserName() {
		return userName;
	}

	/** 
	* @param userName 要设置的 userName 
	*/

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 
	* @return userPwd 
	*/

	public String getUserPwd() {
		return userPwd;
	}

	/** 
	* @param userPwd 要设置的 userPwd 
	*/

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/** 
	* @return realName 
	*/

	public String getRealName() {
		return realName;
	}

	/** 
	* @param realName 要设置的 realName 
	*/

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/** 
	* @return bankName 
	*/

	public String getBankName() {
		return bankName;
	}

	/** 
	* @param bankName 要设置的 bankName 
	*/

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/** 
	* @return bankCardnum 
	*/

	public String getBankCardnum() {
		return bankCardnum;
	}

	/** 
	* @param bankCardnum 要设置的 bankCardnum 
	*/

	public void setBankCardnum(String bankCardnum) {
		this.bankCardnum = bankCardnum;
	}

	/** 
	* @return cashAmount 
	*/

	public double getCashAmount() {
		return cashAmount;
	}

	/** 
	* @param cashAmount 要设置的 cashAmount 
	*/

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

}
