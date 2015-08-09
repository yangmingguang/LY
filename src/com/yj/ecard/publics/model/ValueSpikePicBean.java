/**   
* @Title: ValueSpikePicBean.java
* @Package com.yj.ecard.publics.model
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-26 下午3:22:01
* @version V1.0   
*/

package com.yj.ecard.publics.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
* @ClassName: ValueSpikePicBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-26 下午3:22:01
* 
*/

public class ValueSpikePicBean implements Parcelable {

	public int width;
	public int height;
	public String picUrl;

	public ValueSpikePicBean() {
		// TODO Auto-generated constructor stub
	}

	public ValueSpikePicBean(Parcel source) {
		width = source.readInt();
		height = source.readInt();
		picUrl = source.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(width);
		dest.writeInt(height);
		dest.writeString(picUrl);
	}

	public static final Creator<ValueSpikePicBean> CREATOR = new Creator<ValueSpikePicBean>() {

		@Override
		public ValueSpikePicBean createFromParcel(Parcel source) {
			return new ValueSpikePicBean(source);
		}

		@Override
		public ValueSpikePicBean[] newArray(int size) {
			return new ValueSpikePicBean[size];
		}
	};

	/** 
	* @return width 
	*/

	public int getWidth() {
		return width;
	}

	/** 
	* @param width 要设置的 width 
	*/

	public void setWidth(int width) {
		this.width = width;
	}

	/** 
	* @return height 
	*/

	public int getHeight() {
		return height;
	}

	/** 
	* @param height 要设置的 height 
	*/

	public void setHeight(int height) {
		this.height = height;
	}

	/** 
	* @return picUrl 
	*/

	public String getPicUrl() {
		return picUrl;
	}

	/** 
	* @param picUrl 要设置的 picUrl 
	*/

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
