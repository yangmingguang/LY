/**   
* @Title: TabProductFragment.java
* @Package com.yj.ecard.ui.activity.main.business
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-14 下午6:14:03
* @version V1.0   
*/

package com.yj.ecard.ui.activity.main.business;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.yj.ecard.R;
import com.yj.ecard.publics.model.ProductBean;
import com.yj.ecard.ui.activity.base.BaseFragment;
import com.yj.ecard.ui.adapter.DetailProductListAdapter;
import com.yj.ecard.ui.views.scrollview.OverScrollableScrollView.OverScrollController;

/**
* @ClassName: TabProductFragment
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-14 下午6:14:03
* 
*/

public class TabProductFragment extends BaseFragment implements OverScrollController {

	private ListView mListView;
	private View rootView, loadingView;
	private boolean inited, mCanScrollUp;
	private DetailProductListAdapter mAdapter;

	public static Fragment newInstance(Bundle bundle) {
		TabProductFragment fragment = new TabProductFragment();
		if (bundle != null)
			fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_tab_product, null);
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
		loadingView = LayoutInflater.from(context).inflate(R.layout.loading, null);
		mListView = (ListView) rootView.findViewById(R.id.lv_product);
		mListView.setEmptyView(loadingView);
		mAdapter = new DetailProductListAdapter(context);
		mListView.setAdapter(mAdapter);

		// 滚动事件监听
		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int childTop = 0;
				if (view.getChildCount() > 0) {
					childTop = view.getChildAt(0).getTop();
					if (firstVisibleItem == 0 && childTop == 0) {
						mCanScrollUp = true;
					} else {
						mCanScrollUp = false;
					}
				} else {
					mCanScrollUp = true;
				}
			}
		});
	}

	/**
	 * 
	* @Title: setData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param content    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setData(List<ProductBean> mList) {
		if (!inited) {
			mAdapter.setList(mList);
			inited = true;
		}
	}

	@Override
	public boolean canScrollUp() {
		// TODO Auto-generated method stub
		return mCanScrollUp;
	}

}
