/**   
* @Title: AddressListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.AddressBean;

/**
* @ClassName: AddressListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class AddressListViewHolder {

	private boolean hasInited;
	public TextView tvName, tvPhone, tvAddress, btnDelete;

	public AddressListViewHolder(View view) {
		if (view != null) {
			tvName = (TextView) view.findViewById(R.id.tv_name);
			tvPhone = (TextView) view.findViewById(R.id.tv_phone);
			tvAddress = (TextView) view.findViewById(R.id.tv_address);
			btnDelete = (TextView) view.findViewById(R.id.btn_delete);
			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param addressBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, AddressBean addressBean) {
		if (hasInited) {
			tvName.setText(addressBean.realName);
			tvPhone.setText(addressBean.phone);
			tvAddress.setText(addressBean.address);
		}
	}

}
