/**   
* @Title: PhoneManager.java
* @Package com.yj.ecard.business.phone
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-31 下午3:46:05
* @version V1.0   
*/

package com.yj.ecard.business.phone;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yj.ecard.R;
import com.yj.ecard.business.download.DownloadService;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.http.model.request.SeeAdRequest;
import com.yj.ecard.publics.http.model.request.TelAdListRequest;
import com.yj.ecard.publics.http.model.response.SeeAdResponse;
import com.yj.ecard.publics.http.model.response.TelAdListResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.TelAdBean;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.MD5Util;
import com.yj.ecard.publics.utils.StorageUtils;
import com.yj.ecard.publics.utils.ToastUtil;
import com.yj.ecard.publics.utils.Utils;

/**
* @ClassName: PhoneManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 下午3:46:05
* 
*/

public class PhoneManager {

	private boolean isDownload = false;
	private static volatile PhoneManager mPhoneManager;

	/******************************单例开始********************************/

	private PhoneManager() {
		// TODO Auto-generated constructor stub
	}

	public static PhoneManager getInstance() {
		if (mPhoneManager == null) {
			synchronized (PhoneManager.class) {
				if (mPhoneManager == null) {
					mPhoneManager = new PhoneManager();
				}
			}
		}
		return mPhoneManager;
	}

	/******************************单例结束********************************/

	public void getTelAdListData(final Context context) {
		TelAdListRequest request = new TelAdListRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		DataFetcher.getInstance().getTelAdListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				TelAdListResponse telAdListResponse = (TelAdListResponse) JsonUtil.jsonToBean(response,
						TelAdListResponse.class);

				if (null != telAdListResponse.list) {
					saveTelAdList(context, telAdListResponse.list);
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	/**
	 * 
	* @Title: postSeeAdData 
	* @Description: 看广告收益
	* @param @param context
	* @param @param advId
	* @param @param sortId
	* @param @param sort    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void postSeeAdData(final Context context, int advId, int sortId, int sort) {
		SeeAdRequest request = new SeeAdRequest();
		request.setUserId(UserManager.getInstance().getUserId(context));
		request.setAdvId(advId);
		request.setSortId(sortId);
		request.setSort(sort);
		String imei = Utils.getIMEI(context);
		int userId = UserManager.getInstance().getUserId(context);
		String sign = MD5Util.getMD5(imei + userId + advId); // MD5加密
		request.setSign(sign);
		DataFetcher.getInstance().postSeeAdResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				SeeAdResponse mSeeAdResponse = (SeeAdResponse) JsonUtil.jsonToBean(response, SeeAdResponse.class);

				// 数据响应状态
				int stateCode = mSeeAdResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					ToastUtil.show(context, mSeeAdResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;
				case Constan.EMPTY_CODE:
					ToastUtil.show(context, mSeeAdResponse.status.msg, ToastUtil.LENGTH_LONG);
					break;
				case Constan.ERROR_CODE:
					ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
					break;
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				ToastUtil.show(context, R.string.error_tips, ToastUtil.LENGTH_SHORT);
			}
		});
	}

	/**
	 * 
	* @Title: saveData 
	* @Description: 保存数据
	* @param @param list    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void saveTelAdList(final Context context, List<TelAdBean> list) {

		if (!isDownload) {
			isDownload = true; // 标记正在下载

			// 1====清空表数据====
			DBService.getInstance(context).clear();

			for (final TelAdBean bean : list) {
				// 2====保存数据====
				DBService.getInstance(context).save(bean);
				startDownloadImage(context, bean.smallUrl, false);
				startDownloadImage(context, bean.largeUrl, true);
			}

			isDownload = false; // 标记下载结束
		}
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
	private void startDownloadImage(final Context context, final String url, final boolean isLargeUrl) {
		// 判断文件目录是否存在
		String path = StorageUtils.IMAGE_PATH;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 判断文件是否存在
		final String targetPath = path + MD5Util.getMD5(url);
		File imageFile = new File(targetPath);
		if (imageFile.exists()) {
			if (isLargeUrl) {
				System.out.println("=======大图片已经存在,无需下载=======");
				DBService.getInstance(context).updateLargePic(targetPath, url);
			} else {
				System.out.println("=======小图片已经存在，无需下载=======");
				DBService.getInstance(context).updateSmallPic(targetPath, url);
			}
			return;
		}

		try {
			DownloadService.getDownloadManager(context).addNewDownload(url, "leying", targetPath, true, false,
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
							System.out.println("onSuccess");

							if (isLargeUrl) {
								System.out.println("=======大图片下载成功=======");
								DBService.getInstance(context).updateLargePic(targetPath, url);
							} else {
								System.out.println("=======小图片下载成功=======");
								DBService.getInstance(context).updateSmallPic(targetPath, url);
							}
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
