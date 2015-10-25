/**   
 * @Title: PushResponse.java
 * @Package com.yj.ecard.publics.http.model.response
 * @Description: TODO(用一句话描述该文件做什么)
 * @author YangMingGuang
 * @date 2015-6-6 上午11:09:56
 * @version V1.0   
 */

package com.yj.ecard.publics.http.model.response;

/**
 * @ClassName: PushResponse
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author YangMingGuang
 * @date 2015-6-6 上午11:09:56
 * 
 */

public class PushResponse {

	public Sign sign;

	public static class Sign {
		public int type;
		public int areaId;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getAreaId() {
			return areaId;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
		}

	}

	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

}
