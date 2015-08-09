package com.yj.ecard.ui.views.dropdownmenu;

/**
 * 
* @ClassName: DropdownItemObject
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-26 下午3:22:24
*
 */
public class DropdownItemObject {

	public int id;
	public String text;
	public String value;
	public String suffix;

	public DropdownItemObject(String text, int id, String value) {
		this.text = text;
		this.id = id;
		this.value = value;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
