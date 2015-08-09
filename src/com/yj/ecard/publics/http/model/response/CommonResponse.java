/**   
* @Title: CommonResponse.java
* @Package com.yj.ecard.publics.http.model.response
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-6 上午11:09:56
* @version V1.0   
*/

package com.yj.ecard.publics.http.model.response;

/**
* @ClassName: CommonResponse
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-6 上午11:09:56
* 
*/

public class CommonResponse {

	public Status status;

	public static class Status {
		public int code;
		public String msg;

		/** 
		* @return code 
		*/

		public int getCode() {
			return code;
		}

		/** 
		* @param code 要设置的 code 
		*/

		public void setCode(int code) {
			this.code = code;
		}

		/** 
		* @return msg 
		*/

		public String getMsg() {
			return msg;
		}

		/** 
		* @param msg 要设置的 msg 
		*/

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	/** 
	* @return status 
	*/

	public Status getStatus() {
		return status;
	}

	/** 
	* @param status 要设置的 status 
	*/

	public void setStatus(Status status) {
		this.status = status;
	}

}
