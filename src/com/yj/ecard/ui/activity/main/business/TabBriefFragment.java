/**   
* @Title: TabBriefFragment.java
* @Package com.yj.ecard.ui.activity.main.business
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-14 下午6:14:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;

import com.yj.ecard.R;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.views.scrollview.OverScrollableScrollView;
import com.yj.ecard.ui.views.scrollview.OverScrollableScrollView.OverScrollController;
import com.yj.ecard.ui.views.webview.CustomWebView;
import com.yj.ecard.ui.views.webview.CustomWebView.ScrollInterface;

/**
* @ClassName: TabBriefFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-14 下午6:14:03
* 
*/

public class TabBriefFragment extends BaseFragment implements OverScrollController {

	private View rootView;
	private boolean inited = false;
	private CustomWebView mWebView;
	private boolean mCanScrollUp = true;

	public static Fragment newInstance(Bundle bundle) {
		TabBriefFragment fragment = new TabBriefFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_brief, null);
		initViews();
		return rootView;
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		mWebView = (CustomWebView) rootView.findViewById(R.id.webview);
		mWebView.setScrollBarStyle(0);
		WebSettings settings = mWebView.getSettings();
		settings.setDefaultTextEncodingName("utf-8");// 避免中文乱码  
		settings.setNeedInitialFocus(false);
		settings.setSupportZoom(true);
		settings.setLoadWithOverviewMode(true);//适应屏幕
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setLoadsImagesAutomatically(true);//自动加载图片
		settings.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);

		// 滚动事件监听
		mWebView.setOnCustomScroolChangeListener(new ScrollInterface() {
			@Override
			public void onScrollChanged(int l, int t, int oldl, int oldt) {
				// TODO Auto-generated method stub
				if (t == 0) { // 已经处于顶端           
					mCanScrollUp = true;
				} else {
					mCanScrollUp = false;
				}
			}
		});
	}

	/**
	 * 	
	* @Title: setData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param mList    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setData(OverScrollableScrollView mScrollView, String content) {
		if (!inited) {
			Utils.loadHtmlCode(mWebView, content); // 加载html代码
			mScrollView.setController(this); // 初始化可滚动
			inited = true;
		}
	}

	@Override
	public boolean canScrollUp() {
		// TODO Auto-generated method stub
		return mCanScrollUp;
	}

}
