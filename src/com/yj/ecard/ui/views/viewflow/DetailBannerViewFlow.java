package com.yj.ecard.ui.views.viewflow;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageType;
import com.yj.ecard.R;
import com.yj.ecard.publics.model.ExchangePicBean;
import com.yj.ecard.publics.utils.ImageLoaderUtil;
import com.yj.ecard.ui.activity.main.exchange.ViewPagerActivity;

/**
 * 
* @ClassName: DetailBannerViewFlow
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-21 下午2:48:33
*
 */
public class DetailBannerViewFlow {
	private Context context;
	private ViewFlow viewFlow;
	private ImageAdapter imageAdapter;
	private int viewflowId, viewflowindicId;
	private View rootView;

	public DetailBannerViewFlow(Context context, View rootView, int viewflowId, int viewflowindicId,
			ViewPager parentViewPager) {
		this.context = context;
		this.rootView = rootView;
		this.viewflowId = viewflowId;
		this.viewflowindicId = viewflowindicId;
	}

	public ViewFlow creatMyViewFlow(List<ExchangePicBean> mList) {
		viewFlow = null;
		viewFlow = (ViewFlow) rootView.findViewById(viewflowId);
		imageAdapter = new ImageAdapter(mList);
		viewFlow.setAdapter(imageAdapter);
		int size = mList.size();
		viewFlow.setmSideBuffer(size); // 实际图片张数
		CircleFlowIndicator indic = (CircleFlowIndicator) rootView.findViewById(viewflowindicId);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(10 * 1000); // 设置时间间隔
		viewFlow.setSelection(size * 1000); //设置初始位置，图片总数的倍数
		viewFlow.startAutoFlowTimer(); //启动自动播放 
		return viewFlow;
	}

	class ImageAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		private List<ExchangePicBean> mShow = new ArrayList<ExchangePicBean>();

		public ImageAdapter(List<ExchangePicBean> appList) {
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.mShow = appList;
		}

		public int getCount() {
			return Integer.MAX_VALUE; //返回很大的值使得getView中的position不断增大来实现循环。
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			if (mShow.isEmpty())
				return (ImageView) mInflater.inflate(R.layout.banner_item, null);
			if (convertView == null)
				convertView = (ImageView) mInflater.inflate(R.layout.banner_item, null);
			final ExchangePicBean productPicBean = mShow.get(position % mShow.size());
			convertView.setTag(productPicBean);
			ImageLoaderUtil.load(context, ImageType.NETWORK, productPicBean.picUrl, R.drawable.banner_detail_default,
					R.drawable.banner_detail_default, (ImageView) convertView);

			// listener event
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int index = position % mShow.size();
					Intent intent = new Intent(context, ViewPagerActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("picIndex", index);
					intent.putParcelableArrayListExtra("picList", (ArrayList<? extends Parcelable>) mShow);
					context.startActivity(intent);
				}
			});
			return convertView;
		}
	}
}
