/**   
* @Title: MessageListViewHolder.java
* @Package com.yj.ecard.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.MessageBean;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.views.webview.CustomWebView;

/**
* @ClassName: MessageListViewHolder
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午5:26:13
* 
*/

public class MessageListViewHolder {

	private boolean hasInited;
	private CustomWebView mWebView;
	public TextView tvTitle, btnDelete;

	public MessageListViewHolder(View view) {
		if (view != null) {
			tvTitle = (TextView) view.findViewById(R.id.tv_title);
			btnDelete = (TextView) view.findViewById(R.id.btn_delete);

			// webView 
			mWebView = (CustomWebView) view.findViewById(R.id.webview);
			mWebView.setScrollBarStyle(0);
			WebSettings settings = mWebView.getSettings();
			settings.setDefaultTextEncodingName("utf-8");// 避免中文乱码  
			settings.setNeedInitialFocus(false);
			settings.setSupportZoom(true);
			settings.setLoadWithOverviewMode(true);//适应屏幕
			settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			settings.setLoadsImagesAutomatically(true);//自动加载图片
			settings.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);

			hasInited = true;
		}
	}

	/** 
	* @Title: initData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param messageBean
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void initData(Context context, MessageBean messageBean) {
		if (hasInited) {
			tvTitle.setText(messageBean.title);
			Utils.loadHtmlCode(mWebView, messageBean.content); // 加载html代码
		}
	}

}
