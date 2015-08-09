/**   
* @Title: ViewPagerAdapter.java
* @Package cn.cty.saletool.ui.adapter
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-22 上午11:41:18
* @version V1.0   
*/

package com.yj.ecard.ui.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.model.ExchangePicBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: ViewPagerAdapter
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-22 上午11:41:18
* 
*/

public class ViewPagerAdapter extends PagerAdapter {

	private Context context;
	private List<ExchangePicBean> imgList;
	private LayoutInflater layoutInflater;

	public ViewPagerAdapter(Context context, List<ExchangePicBean> imgList) {
		this.context = context;
		this.imgList = imgList;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return imgList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((View) object);
	}

	@Override
	public Object instantiateItem(View container, final int position) {
		View rootView = layoutInflater.inflate(R.layout.viewpager_product_picture, null);
		ImageView imageView = (ImageView) rootView.findViewById(R.id.imageview);
		ExchangePicBean productPicBean = imgList.get(position);

		// 加载图片
		ImageLoaderUtil.load(context, ImageType.NETWORK, productPicBean.picUrl, R.drawable.banner_detail_default,
				R.drawable.banner_detail_default, imageView);

		((ViewPager) container).addView(rootView, 0);
		return rootView;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	/**
	* @Title: setList 
	* @Description: 设置数据
	* @param @param urls    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setList(List<ExchangePicBean> imgList) {
		this.imgList = imgList;
		notifyDataSetChanged();
	}
}
