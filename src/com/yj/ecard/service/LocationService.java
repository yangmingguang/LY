/**   
* @Title: LocationService.java
* @Package com.yj.ecard.service
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-21 下午4:38:18
* @version V1.0   
*/

package com.yj.ecard.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.LogUtil;

/**
* @ClassName: LocationService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-21 下午4:38:18
* 
*/

public class LocationService extends Service {

	private Context context;
	private LocationManagerProxy mAMapLocationManager;//位置管理器

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (null != intent) {
			int state = intent.getExtras().getInt(Constan.LOCATION_FLAG);
			switch (state) {
			// 开始定位
			case Constan.START:
				LogUtil.getLogger().d("===开始定位===");
				startLocation();
				break;

			// 停止定位
			case Constan.STOP:
				LogUtil.getLogger().d("===停止定位===");
				stopLocation();
				break;
			}
		}

		return super.onStartCommand(intent, flags, startId);
	}

	/** 
	* @Title: startLocation 
	* @Description: 开始定位
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void startLocation() {
		if (null == mAMapLocationManager) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			mAMapLocationManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 1000, 1,
					new MyLocationListener());
		}
	}

	/**
	 * 
	* @Title: stopLocation 
	* @Description: 停止定位
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void stopLocation() {
		if (null != mAMapLocationManager) {
			mAMapLocationManager.removeUpdates(new MyLocationListener());
			mAMapLocationManager.destory();
			mAMapLocationManager = null;
		}
	}

	/**
	 * 
	* @ClassName: MyLocationListener
	* @Description: 定位Listener
	* @author YangMingGuang
	* @date 2015-5-31 上午11:15:42
	*
	 */
	class MyLocationListener implements AMapLocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onLocationChanged(AMapLocation mLocation) {
			LogUtil.getLogger().d("Location==>" + mLocation.toString());

			// 存储地理位置
			CommonManager.getInstance().setLocationLat(context, mLocation.getLatitude() + ""); // 纬度
			CommonManager.getInstance().setLocationLng(context, mLocation.getLongitude() + "");// 经度
			CommonManager.getInstance().setLocationCity(context, mLocation.getCity()); // 城市
			String address = mLocation.getExtras().getString("desc");
			CommonManager.getInstance().setLocationAddress(context, address);// 详细地址

			// 获取区域ID
			CommonManager.getInstance().getAreaIdData(context);

			// 停止定位
			if (null != mAMapLocationManager) {
				mAMapLocationManager.removeUpdates(this);
				mAMapLocationManager.destory();
				mAMapLocationManager = null;
			}
		}
	}
}
