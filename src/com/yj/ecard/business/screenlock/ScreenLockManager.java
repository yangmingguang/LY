/**   
* @Title: ScreenLockManager.java
* @Package com.yj.ecard.business.screenlock
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-30 下午8:52:34
* @version V1.0   
*/

package com.yj.ecard.business.screenlock;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yj.ecard.business.download.DownloadService;
import com.yj.ecard.business.user.UserManager;
import com.yj.ecard.db.DBService;
import com.yj.ecard.publics.http.model.request.ScreenLockRequest;
import com.yj.ecard.publics.http.model.response.ScreenLockResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.model.ScreenLockBean;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.MD5Util;
import com.yj.ecard.publics.utils.StorageUtils;

/**
* @ClassName: ScreenLockManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-30 下午8:52:34
* 
*/

public class ScreenLockManager {

	private boolean isDownload = false;
	private static volatile ScreenLockManager mScreenLockManager;

	/******************************单例开始********************************/

	private ScreenLockManager() {
		// TODO Auto-generated constructor stub
	}

	public static ScreenLockManager getInstance() {
		if (mScreenLockManager == null) {
			synchronized (ScreenLockManager.class) {
				if (mScreenLockManager == null) {
					mScreenLockManager = new ScreenLockManager();
				}
			}
		}
		return mScreenLockManager;
	}

	/******************************单例结束********************************/

	/**
	 * 
	* @Title: getScreenLockListData 
	* @Description: 获取锁屏广告列表
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getScreenLockListData(final Context context) {
		ScreenLockRequest request = new ScreenLockRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		DataFetcher.getInstance().getScreenLockListResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				ScreenLockResponse mScreenLockResponse = (ScreenLockResponse) JsonUtil.jsonToBean(response,
						ScreenLockResponse.class);

				if (null != mScreenLockResponse.data) {
					saveScreenLockAdList(context, mScreenLockResponse.data);
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
	* @Title: saveScreenLockAdList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param picList    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void saveScreenLockAdList(Context context, List<ScreenLockBean> list) {
		// TODO Auto-generated method stub
		if (!isDownload) {
			isDownload = true; // 标记正在下载

			// 1====清空表数据====
			DBService.getInstance(context).clearScreenLock();

			for (final ScreenLockBean bean : list) {
				// 2====保存数据====
				DBService.getInstance(context).save(bean);
				startDownloadImage(context, bean.imgUrl);
			}

			isDownload = false; // 标记下载结束
		}
	}

	/** 
	* @Title: startDownloadImage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context
	* @param @param url  设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void startDownloadImage(final Context context, final String url) {
		// TODO Auto-generated method stub
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
			System.out.println("=======ScreenLock锁屏图片已经存在，无需下载=======");
			DBService.getInstance(context).updateScreenLockPic(targetPath, url);
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
							System.out.println("=======ScreenLock锁屏图片下载成功=======");
							DBService.getInstance(context).updateScreenLockPic(targetPath, url);
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
