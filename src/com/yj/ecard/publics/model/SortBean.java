/**   
* @Title: SortBean.java
* @Package com.yj.ecard.publics.model
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-9-19 上午12:19:14
* @version V1.0   
*/

package com.yj.ecard.publics.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
* @ClassName: SortBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-9-19 上午12:19:14
* 
*/

public class SortBean implements Parcelable {

	public int id;
	public String title;

	public SortBean() {
		// TODO Auto-generated constructor stub
	}

	public SortBean(Parcel source) {
		id = source.readInt();
		title = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(id);
		dest.writeString(title);
	}

	public static final Creator<SortBean> CREATOR = new Creator<SortBean>() {

		@Override
		public SortBean createFromParcel(Parcel source) {
			return new SortBean(source);
		}

		@Override
		public SortBean[] newArray(int size) {
			return new SortBean[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
