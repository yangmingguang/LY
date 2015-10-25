/**   
* @Title: CommonManager.java
* @Package com.yj.ecard.business.main.common
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-31 上午11:28:48
* @version V1.0   
*/

package com.yj.ecard.business.common;

import java.io.File;

import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yj.ecard.R;
import com.yj.ecard.business.download.DownloadService;
import com.yj.ecard.business.notification.CustomNotificationManager;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.publics.http.model.request.AreaIdRequest;
import com.yj.ecard.publics.http.model.request.ShareRequest;
import com.yj.ecard.publics.http.model.request.UpdateRequest;
import com.yj.ecard.publics.http.model.request.WelcomeRequest;
import com.yj.ecard.publics.http.model.response.AreaIdResponse;
import com.yj.ecard.publics.http.model.response.ShareResponse;
import com.yj.ecard.publics.http.model.response.UpdateResponse;
import com.yj.ecard.publics.http.model.response.WelcomeResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.SharedPrefsUtil;
import com.yj.ecard.publics.utils.StorageUtils;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.service.LocationService;

/**
* @ClassName: CommonManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 上午11:28:48
* 
*/

public class CommonManager {

	private static volatile CommonManager mCommonManager;

	/******************************单例开始********************************/

	private CommonManager() {
		// TODO Auto-generated constructor stub
	}

	public static CommonManager getInstance() {
		if (mCommonManager == null) {
			synchronized (CommonManager.class) {
				if (mCommonManager == null) {
					mCommonManager = new CommonManager();
				}
			}
		}
		return mCommonManager;
	}

	/******************************单例结束********************************/

	public void setLocationAddress(Context context, String address) {
		SharedPrefsUtil.putValue(context, Constan.LOCATION_ADDRESS, address);
	}

	public String getLocationAddress(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.LOCATION_ADDRESS, "");
	}

	public void setLocationCity(Context context, String city) {
		SharedPrefsUtil.putValue(context, Constan.LOCATION_CITY, city);
	}

	public String getLocationCity(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.LOCATION_CITY, "");
	}

	public void setLocationLng(Context context, String lng) {
		SharedPrefsUtil.putValue(context, Constan.LOCATION_LNG, lng);
	}

	public String getLocationlng(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.LOCATION_LNG, "0.0");
	}

	public void setLocationLat(Context context, String lat) {
		SharedPrefsUtil.putValue(context, Constan.LOCATION_LAT, lat);
	}

	public String getLocationlat(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.LOCATION_LAT, "0.0");
	}

	public int getProvince(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.PROVINCE, 0);
	}

	public void setProvince(Context context, int province) {
		SharedPrefsUtil.putValue(context, Constan.PROVINCE, province);
	}

	public int getCity(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.CITY, 35);
	}

	public void setCity(Context context, int city) {
		SharedPrefsUtil.putValue(context, Constan.CITY, city);
	}

	public int getCountry(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.COUNTRY, 0);
	}

	public void setCountry(Context context, int country) {
		SharedPrefsUtil.putValue(context, Constan.COUNTRY, country);
	}

	public int getAreaId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.AREA_ID, 0);
	}

	public void setAreaId(Context context, int areaId) {
		SharedPrefsUtil.putValue(context, Constan.AREA_ID, areaId);
	}

	public void setCropImagePath(Context context, String path) {
		SharedPrefsUtil.putValue(context, Constan.CROP_IMAGE_PATH, path);
	}

	public String getCropImagePath(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.CROP_IMAGE_PATH, "");
	}

	public void setApkLoaclPath(Context context, String path) {
		SharedPrefsUtil.putValue(context, Constan.APK_LOCAL_PATH, path);
	}

	public String getApkLoaclPath(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.APK_LOCAL_PATH, "");
	}

	public void setOnClickable(Context context, boolean onClick) {
		SharedPrefsUtil.putValue(context, Constan.IS_ONCLICK, onClick);
	}

	public boolean isOnClickable(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.IS_ONCLICK, false);
	}

	public void setShareTtitle(Context context, String title) {
		SharedPrefsUtil.putValue(context, Constan.SHARE_TITLE, title);
	}

	public String getShareTtitle(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.SHARE_TITLE, "赚钱就这么简单！");
	}

	public void setShareContent(Context context, String content) {
		SharedPrefsUtil.putValue(context, Constan.SHARE_CONTENT, content);
	}

	public String getShareContent(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.SHARE_CONTENT, "赚钱就这么简单，刚才我注册，接打电话真的能挣钱!还能兑话费!");
	}

	public void setShareUrl(Context context, String url) {
		SharedPrefsUtil.putValue(context, Constan.SHARE_URL, url);
	}

	public String getShareUrl(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.SHARE_URL,
				"http://zhuan.307755.com/index.aspx?username=18639719980");
	}

	public void setSharePicUrl(Context context, String picUrl) {
		SharedPrefsUtil.putValue(context, Constan.SHARE_PICURL, picUrl);
	}

	public String getSharePicUrl(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.SHARE_PICURL, "http://webapi.307755.com/Images/120.jpg");
	}

	public void setFieldId(Context context, String fieldId) {
		SharedPrefsUtil.putValue(context, Constan.FIELD_ID, fieldId);
	}

	public String getFieldId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.FIELD_ID, "0");
	}

	public void setFieldText(Context context, String fieldText) {
		SharedPrefsUtil.putValue(context, Constan.FIELD_TEXT, fieldText);
	}

	public String getFieldText(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.FIELD_TEXT, "0");
	}

	public void setSexId(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.SEX_ID, id);
	}

	public int getSexId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.SEX_ID, 0);
	}

	public void setAgeId(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.AGE_ID, id);
	}

	public int getAgeId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.AGE_ID, 0);
	}

	public void setIncomeId(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.INCOME_ID, id);
	}

	public int getIncomeId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.INCOME_ID, 0);
	}

	public void setMarriageId(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.MARRIAGE_ID, id);
	}

	public int getMarriageId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.MARRIAGE_ID, 0);
	}

	public void setProfessionId(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.PROFESSION_ID, id);
	}

	public int getProfessionId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.PROFESSION_ID, 0);
	}

	public void setAreaSortValue(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.AREA_SORT_ID, id);
	}

	public int getAreaSortValue(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.AREA_SORT_ID, 0);
	}

	public void setShopSortValue(Context context, int id) {
		SharedPrefsUtil.putValue(context, Constan.SHOP_SORT_ID, id);
	}

	public int getShopSortValue(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.SHOP_SORT_ID, 0);
	}

	public void setMenuItemClick(Context context, boolean isClick) {
		SharedPrefsUtil.putValue(context, "menuItemClick", isClick);
	}

	public boolean getMenuItemClick(Context context) {
		return SharedPrefsUtil.getValue(context, "menuItemClick", false);
	}

	public void setAreaClick(Context context, boolean isClick) {
		SharedPrefsUtil.putValue(context, "areaClick", isClick);
	}

	public boolean getAreaClick(Context context) {
		return SharedPrefsUtil.getValue(context, "areaClick", false);
	}

	public void setSortClick(Context context, boolean isClick) {
		SharedPrefsUtil.putValue(context, "sortClick", isClick);
	}

	public boolean getSortClick(Context context) {
		return SharedPrefsUtil.getValue(context, "sortClick", false);
	}

	/**
	 * 
	* @Title: initLocation 
	* @Description: 初始化、开启定位服务
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void initLocation(Context context) {
		Intent intent = new Intent(context, LocationService.class);
		intent.putExtra(Constan.LOCATION_FLAG, Constan.START);
		context.startService(intent);
	}

	/**
	 * 
	* @Title: checkNewVersion 
	* @Description: 检测新版本
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void checkNewVersion(final Context context, final boolean isAuto) {
		if (!isAuto)
			Utils.showProgressDialog(context); // 显示dialog
		UpdateRequest request = new UpdateRequest();
		request.setVersionCode(Utils.getVersionCode(context));
		DataFetcher.getInstance().getNewVersionResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				LogUtil.getLogger().d("response==>" + response.toString());
				final UpdateResponse mUpdateResponse = (UpdateResponse) JsonUtil.jsonToBean(response,
						UpdateResponse.class);

				// 数据响应状态
				int stateCode = mUpdateResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					String message = mUpdateResponse.note;
					Utils.showDialog(context, R.string.dialog_title, message, R.string.dialog_positive,
							R.string.dialog_negative, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									startDownload(context, mUpdateResponse.downUrl, mUpdateResponse.versionCode); // 开始下载
									dialog.dismiss();
								}
							}, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							});
					break;

				case Constan.EMPTY_CODE:
					if (!isAuto)
						ToastUtil.show(context, mUpdateResponse.status.msg, ToastUtil.LENGTH_SHORT);
					break;

				case Constan.ERROR_CODE:
					if (!isAuto)
						ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
					break;
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Utils.dismissProgressDialog(); // 取消dialog
				if (!isAuto)
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
			}
		}, true);
	}

	/**
	 * 
	* @Title: startDownload 
	* @Description: 开始下载
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void startDownload(final Context context, String url, int versionCode) {
		// 判断文件目录是否存在
		String path = StorageUtils.DOWNLOAD_PATH;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 判断apk文件是否存在
		String targetPath = path + "leying_" + versionCode + ".apk";
		File apkFile = new File(targetPath);
		if (apkFile.exists()) {
			Utils.installApk(context, targetPath);
			return;
		}
		// 保存文件路径
		CommonManager.getInstance().setApkLoaclPath(context, targetPath);

		try {
			DownloadService.getDownloadManager(context).addNewDownload(url, "leying", targetPath, true, false,
					new RequestCallBack<File>() {

						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							System.out.println("onStart");
							CustomNotificationManager.getInstance().initNotification(context);
						}

						@Override
						public void onLoading(long total, long current, boolean isUploading) {
							// TODO Auto-generated method stub
							System.out.println("total = " + total + ",current = " + current);
							CustomNotificationManager.getInstance().updateProgress((int) total, (int) current);
						}

						@Override
						public void onSuccess(ResponseInfo<File> responseInfo) {
							// TODO Auto-generated method stub
							System.out.println("onSuccess");
							toInstallApk(context); //调用安装apk
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// TODO Auto-generated method stub
							System.out.println("onFailure");
						}
					});
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: toInstallApk 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void toInstallApk(Context context) {
		String path = CommonManager.getInstance().getApkLoaclPath(context);
		Utils.installApk(context, path);
	}

	/**
	 * 
	* @Title: getShareContentData 
	* @Description: 获取分享数据
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getShareContentData(final Context context) {
		ShareRequest request = new ShareRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		DataFetcher.getInstance().getShareContentResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				final ShareResponse mShareResponse = (ShareResponse) JsonUtil.jsonToBean(response, ShareResponse.class);

				// 数据响应状态
				int stateCode = mShareResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					setShareTtitle(context, mShareResponse.title);
					setShareContent(context, mShareResponse.describe);
					setShareUrl(context, mShareResponse.webSite);
					setSharePicUrl(context, mShareResponse.picUrl);
					break;

				case Constan.EMPTY_CODE:

					break;

				case Constan.ERROR_CODE:

					break;
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);
	}

	/**
	 * 
	* @Title: getAreaIdData 
	* @Description: 获取区域ID
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getAreaIdData(final Context context) {
		AreaIdRequest request = new AreaIdRequest();
		request.setLat(Double.parseDouble(getLocationlat(context)));
		request.setLng(Double.parseDouble(getLocationlng(context)));
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setUserPwd(UserManager.getInstance().getPassword(context));
		DataFetcher.getInstance().getAreaIdResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				final AreaIdResponse mAreaIdResponse = (AreaIdResponse) JsonUtil.jsonToBean(response,
						AreaIdResponse.class);

				// 数据响应状态
				int stateCode = mAreaIdResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					setAreaId(context, mAreaIdResponse.areaid); // 保存区域ID
					break;

				case Constan.EMPTY_CODE:

					break;

				case Constan.ERROR_CODE:

					break;
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, true);
	}

	/**
	 * 
	* @Title: getWelcomeData 
	* @Description: 获取欢迎图片
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getWelcomeData(final Context context) {
		WelcomeRequest request = new WelcomeRequest();
		DataFetcher.getInstance().getWelcomeResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				final WelcomeResponse mWelcomeResponse = (WelcomeResponse) JsonUtil.jsonToBean(response,
						WelcomeResponse.class);

				// 数据响应状态
				int stateCode = mWelcomeResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					startDownloadImage(context, mWelcomeResponse.picUrl);
					break;

				case Constan.EMPTY_CODE:

					break;

				case Constan.ERROR_CODE:

					break;
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
			}
		}, true);
	}

	/**
	 * 
	* @Title: startDownloadImage 
	* @Description: 开始下载图片
	* @param @param context
	* @param @param url
	* @param @param isLargeUrl    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void startDownloadImage(final Context context, final String url) {
		// 判断文件目录是否存在
		String path = StorageUtils.IMAGE_PATH;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 文件名称
		final String targetPath = path + "app_welcome.jpg";

		try {
			DownloadService.getDownloadManager(context).addNewDownload(url, "leying", targetPath, false, false,
					new RequestCallBack<File>() {

						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							System.out.println("onStart");
						}

						@Override
						public void onLoading(long total, long current, boolean isUploading) {
							// TODO Auto-generated method stub
							System.out.println("total = " + total + ",current = " + current);
							//CustomNotificationManager.getInstance().updateProgress((int) total, (int) current);
						}

						@Override
						public void onSuccess(ResponseInfo<File> responseInfo) {
							// TODO Auto-generated method stub
							System.out.println("=======欢迎页图片下载成功=======");
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// TODO Auto-generated method stub
							System.out.println("onFailure");
						}
					});
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
