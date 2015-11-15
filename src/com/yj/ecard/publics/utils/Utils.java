/**   
* @Title: Utils.java
* @Package com.yj.ecard.publics.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-25 下午10:52:45
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.RenrenShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.RenrenSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.business.main.HomeTabManager;
import com.yj.ecard.ui.views.dialog.CustomDialog;
import com.yj.ecard.ui.views.dialog.CustomProgressDialog;

/**
* @ClassName: Utils
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-25 下午10:52:45
* 
*/

public class Utils {

	private static List<Activity> actList = new ArrayList<Activity>();
	private static List<Activity> finishList = new LinkedList<Activity>();
	public static final int[] levels = { R.drawable.ic_level1, R.drawable.ic_level2, R.drawable.ic_level3,
			R.drawable.ic_level4, R.drawable.ic_level5 };

	/**
	* @Title: telCall 
	* @Description: 直接拨打电话
	* @param @param number    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void telCall(Context context, String number) {
		if (null != number && !number.equals("")) {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
	}

	/**
	 * 
	* @Title: showTelPage 
	* @Description: 调用拨打电话界面
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void showTelPage(Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_DIAL); //android.intent.action.DIAL
		context.startActivity(intent);
	}

	/**
	 * 
	* @Title: getScreenWidth 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param activity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static int getScreenWidth(Activity activity) {
		Window window = activity.getWindow();
		DisplayMetrics dm = new DisplayMetrics();
		window.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;//宽度
		return screenWidth;
	}

	/**
	 * 
	* @Title: setBackgroundResource 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param view
	* @param @param resId
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void setBackgroundResource(Context context, int resId, View view) {
		if (view == null)
			return;
		try {
			Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resId);
			BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
			view.setBackgroundDrawable(bd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: recycleBackgroundResource 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param view    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void recycleBackgroundResource(View view) {
		if (view == null)
			return;
		try {
			BitmapDrawable bd = (BitmapDrawable) view.getBackground();
			view.setBackgroundResource(0);// 别忘了把背景设为null，避免onDraw刷新背景时候出现used a recycled bitmap错误
			bd.setCallback(null);
			bd.getBitmap().recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: recycleImageView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param view    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void recycleImageView(ImageView view) {
		if (view == null)
			return;
		try {
			Drawable bd = view.getDrawable();
			view.setImageBitmap(null);
			view.setBackgroundDrawable(null);
			view.setBackgroundResource(0);//别忘了把背景设为null，避免onDraw刷新背景时候出现used a recycled bitmap错误
			bd.setCallback(null);
			view = null;
			//			bd.getBitmap().recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: getLevelDrawable 
	* @Description: 获取等级图片 
	* @param @param level
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public static int getLevelDrawable(int level) {
		int index = level - 1;
		return levels[index];
	}

	/**
	 * @Title: isTablet
	 * @Description: 判断是否属于平板
	 * @param @param context
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	* @Title: setScreenAdapter 
	* @Description: auto adapter phone、tablet
	* @param @param dialog    
	* @return void
	 */
	public static void setScreenAdapter(Context context, Dialog dialog) {
		Window window = dialog.getWindow();
		DisplayMetrics dm = new DisplayMetrics();
		window.getWindowManager().getDefaultDisplay().getMetrics(dm);
		WindowManager.LayoutParams params = window.getAttributes();
		int screenWidth = dm.widthPixels;//宽度
		//int height = dm.heightPixels ;//高度

		// 判断设备是平板、手机
		if (isTablet(context)) {
			// Log.i("is Tablet!");
			params.width = (int) (screenWidth * 0.70); // 宽度设置为屏幕的0.70
		} else {
			// Log.i("is phone!");
			params.width = (int) (screenWidth * 0.85); // 宽度设置为屏幕的0.85
		}

		window.setAttributes(params);
	}

	/**
	* @Title: showDialog 
	* @Description: TODO 
	* @param @param context
	* @param @param titleId
	* @param @param messageId
	* @param @param positiveId
	* @param @param negativeId
	* @param @param positiveListener
	* @param @param negativeListener    
	* @return void
	 */
	public static void showDialog(Context context, int titleId, String message, int positiveId, int negativeId,
			DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
		try {
			CustomDialog.Builder builder = new CustomDialog.Builder(context);
			builder.setTitle(titleId);
			builder.setMessage(message);
			builder.setPositiveButton(positiveId, positiveListener);
			builder.setNegativeButton(negativeId, negativeListener);
			builder.create().show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	* @Title: showProgressDialog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void showProgressDialog(Context context) {
		try {
			CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(context);
			builder.setMessage(R.string.loading);
			builder.create().show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	* @Title: dismissProgressDialog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void dismissProgressDialog() {
		try {
			if (null != CustomProgressDialog.mCustomProgressDialog)
				CustomProgressDialog.mCustomProgressDialog.dismiss();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	* @Title: clearAppCache 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void clearAppCache(Context context) {
		clearCacheFolder(context.getFilesDir(), System.currentTimeMillis());
		clearCacheFolder(context.getCacheDir(), System.currentTimeMillis());
		if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
			clearCacheFolder(context.getExternalCacheDir(), System.currentTimeMillis());
		}
	}

	/**
	 * 
	* @Title: clearCacheFolder 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param dir
	* @param @param curTime
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	private static int clearCacheFolder(File dir, long curTime) {
		int deletedFiles = 0;
		if (dir != null && dir.isDirectory()) {
			try {
				for (File child : dir.listFiles()) {
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, curTime);
					}
					if (child.lastModified() < curTime) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}

	/**
	 * 
	* @Title: isMethodsCompat 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param VersionCode
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isMethodsCompat(int VersionCode) {
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}

	/**
	 * 
	* @Title: getHtmlContent 
	* @Description: 获取html图文内容 
	* @param @param source
	* @param @return    设定文件 
	* @return Spanned    返回类型 
	* @throws
	 */
	public static Spanned getHtmlContent(String html) {
		Spanned sp = Html.fromHtml(html, new Html.ImageGetter() {

			@Override
			public Drawable getDrawable(String source) {
				InputStream is = null;
				try {
					is = (InputStream) new URL(source).getContent();
					Drawable d = Drawable.createFromStream(is, "src");
					d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
					is.close();
					return d;
				} catch (Exception e) {
					return null;
				}
			}
		}, null);

		return sp;
	}

	/**
	 * 
	* @Title: getIMEI 
	* @Description: 获取手机IMEI号 
	* @param @param context
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getIMEI(Context context) {
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}

	/**
	 * 
	* @Title: getMobileType 
	* @Description: 获取手机品牌、型号
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getMobileType() {
		return android.os.Build.MANUFACTURER + " - " + android.os.Build.MODEL;
	}

	/**
	* @Title: addFinishAct 
	* @Description: 添加需要finish的activity
	* @param @param activity    
	* @return void
	 */
	public static void addFinishAct(Activity activity) {
		finishList.add(activity);
	}

	/**
	* @Title: finishActivity 
	* @Description: 关闭activity
	* @param     
	* @return void
	 */
	public static void finishActivity() {
		for (Activity act : finishList) {
			act.finish();
		}
		finishList.clear();
	}

	/**
	 * 
	* @Title: loadHtmlCode 
	* @Description: 加载html代码
	* @param @param mWebView
	* @param @param content    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void loadHtmlCode(WebView mWebView, String content) {
		Document doc_Dis = Jsoup.parse(content);
		Elements ele_Img = doc_Dis.getElementsByTag("img");
		if (ele_Img.size() != 0) {
			for (Element e_Img : ele_Img) {
				e_Img.attr("width", "100%");
				e_Img.attr("height", "auto");
			}
		}
		String newHtmlContent = doc_Dis.toString();
		mWebView.loadDataWithBaseURL(null, newHtmlContent, "text/html", "utf-8", null);
	}

	/**
	 * 
	* @Title: hideSoftInput 
	* @Description: 强制隐藏键盘  
	* @param @param activity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void hideSoftInput(Activity activity) {
		try {
			InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
		} catch (Exception e) {
			//ToDo 
		}
	}

	/**
	 * 
	* @Title: sizeFormat 
	* @Description: 文件大小格式
	* @param @param size
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String sizeFormat(long size) {
		if (size / (1024 * 1024 * 1024) > 0) {
			float tmpSize = (float) (size) / (float) (1024 * 1024 * 1024);
			DecimalFormat df = new DecimalFormat("#.##");
			return "" + df.format(tmpSize) + "G";
		} else if (size / (1024 * 1024) > 0) {
			float tmpSize = (float) (size) / (float) (1024 * 1024);
			DecimalFormat df = new DecimalFormat("#.##");
			return "" + df.format(tmpSize) + "MB";
		} else if (size / 1024 > 0) {
			return "" + (size / (1024)) + "KB";
		} else {
			return "" + size + "B";
		}
	}

	/**
	 * 
	* @Title: installApk 
	* @Description: 调用系统普通安装接口
	* @param @param context
	* @param @param path    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void installApk(Context context, String path) {
		Drawable appIcon = Utils.getAppIconByFile(context, path);
		if (null != appIcon) {
			if (context != null && path != null && path.length() > 0) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
				if (context != null) {
					context.startActivity(intent);
				}
			}
		} else {
			ToastUtil.show(context, "apk文件未不完整！", ToastUtil.LENGTH_LONG);
		}
	}

	/**
	 * 
	* @Title: getAppIconByFile 
	* @Description: 获取apk的drawable
	* @param @param context
	* @param @param localPath
	* @param @return    设定文件 
	* @return Drawable    返回类型 
	* @throws
	 */
	public static Drawable getAppIconByFile(Context context, String localPath) {
		Drawable drawable = null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pkgInfo = pm.getPackageArchiveInfo(localPath, PackageManager.GET_ACTIVITIES);
			if (pkgInfo != null) {
				ApplicationInfo appInfo = pkgInfo.applicationInfo;
				appInfo.sourceDir = localPath;
				appInfo.publicSourceDir = localPath;
				drawable = pm.getApplicationIcon(appInfo);// 得到图标信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return drawable;
	}

	/**
	* @Title: getVersionName 
	* @Description: TODO 
	* @param @param context
	* @param @return    
	* @return String
	 */
	public static String getVersionName(Context context) {
		String version = "";
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	* @Title: getVersionCode 
	* @Description: TODO 
	* @param @param context
	* @param @return    
	* @return int
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionCode = packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 
	* @Title: getFieldStr 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param str
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getFieldStr(String str) {
		String[] strArray = str.split(",");
		int len = strArray.length;
		if (len > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < len; i++) {
				sb.append(DataUtil.getFieldById(Integer.parseInt(strArray[i]))).append("、");
			}
			return sb.toString();
		} else {
			return "请选择你关心的领域";
		}
	}

	/**
	 * 
	* @Title: toShare 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param activity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	/*	public static void toSocialShare(final Activity activity) {
			FrontiaSocialShareContent mSocialShareContent = new FrontiaSocialShareContent();
			FrontiaSocialShare mSocialShare = Frontia.getSocialShare();
			mSocialShare.setContext(activity);

			mSocialShare.setClientId(MediaType.QZONE.toString(), "1103533510"); // QQ空间
			mSocialShare.setClientId(MediaType.QQFRIEND.toString(), "1103533510"); // QQ好友
			//mSocialShare.setClientName(MediaType.QQFRIEND.toString(), "乐盈");
			mSocialShare.setClientId(MediaType.WEIXIN.toString(), "wxb6650aec575b269e"); // 微信
			mSocialShare.setClientId(MediaType.RENREN.toString(), "477208"); // 人人网
			mSocialShare.setClientId(MediaType.SINAWEIBO.toString(), "3563448745"); // 新浪微博
			mSocialShare.setClientId(MediaType.KAIXIN.toString(), "100064908"); // 开心网
			mSocialShare.setClientId(MediaType.QQWEIBO.toString(), "1103533510"); // 腾讯微博 

			String title = CommonManager.getInstance().getShareTtitle(activity);
			String content = CommonManager.getInstance().getShareContent(activity);
			String url = CommonManager.getInstance().getShareUrl(activity);
			String picUrl = CommonManager.getInstance().getSharePicUrl(activity);

			mSocialShareContent.setTitle(title);
			mSocialShareContent.setContent(content);
			mSocialShareContent.setLinkUrl(url);
			mSocialShareContent.setImageUri(Uri.parse(picUrl));
			//mSocialShareContent.setImageData(BitmapFactory.decodeResource(activity.getResources(), R.drawable.app_icon));
			mSocialShare.show(activity.getWindow().getDecorView(), mSocialShareContent, FrontiaTheme.LIGHT,
					new FrontiaSocialShareListener() {
						@Override
						public void onSuccess() {
							HomeTabManager.getInstance().getShareData(activity);
						}

						@Override
						public void onFailure(int errCode, String errMsg) {
						}

						@Override
						public void onCancel() {
						}
					});
		}*/

	/**
	 * 
	* @Title: toUmengSocialShare 
	* @Description: 友盟社会化分享 
	* @param @param activity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void toUmengSocialShare(final Activity activity) {
		String url = CommonManager.getInstance().getShareUrl(activity);

		UMSocialService mController = UMServiceFactory.getUMSocialService(url);
		// 添加新浪SSO授权
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// 添加腾讯微博SSO授权
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		// 添加人人网SSO授权
		RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(activity, "477208",
				"0fad99b2b772445eaa387d2c404a7622", "6aabc4d4b65c443f858373f0819aeb8a");
		mController.getConfig().setSsoHandler(renrenSsoHandler);

		// 添加短信
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();

		// 添加QQ支持
		String appId = "1103533510";
		String appKey = "1ROWSpIjlbPW2pBW";
		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, appId, appKey);
		qqSsoHandler.setTargetUrl(url);
		qqSsoHandler.addToSocialSDK();
		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, appId, appKey);
		qZoneSsoHandler.addToSocialSDK();

		// 注意：在微信授权的时候，必须传递appSecret
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId1 = "wxb6650aec575b269e";
		String appSecret = "02c884952d5ac5b9e0987c061bf26988";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(activity, appId1, appSecret);
		wxHandler.addToSocialSDK();
		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(activity, appId1, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();

		// 分享回调
		mController.registerListener(new SnsPostListener() {

			@Override
			public void onStart() {
				// Toast.makeText(activity, "share start...", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
				if (eCode == 200) { // 分享成功
					HomeTabManager.getInstance().getShareData(activity);
				}
			}
		});

		// 设置分享内容
		setShareContent(activity, mController);
		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,
				SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT, SHARE_MEDIA.RENREN, SHARE_MEDIA.SMS);

		mController.openShare(activity, false);
	}

	/**
	 * 
	* @Title: getContactNameByPhoneNumber 
	* @Description: 根据电话号码获取联系人名称
	* @param @param context
	* @param @param phoneNum
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getContactNameByPhoneNumber(Context context, String phoneNum) {
		String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };

		// 将自己添加到 msPeers 中
		Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				projection, // Which columns to return.
				ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + phoneNum + "'", // WHERE clause.
				null, // WHERE clause value substitution
				null); // Sort order.

		if (cursor == null) {
			//Log.d(TAG, "getPeople null");
			return "未知电话";
		}
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);

			// 取得联系人名字
			int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
			String name = cursor.getString(nameFieldColumnIndex);
			return name;
		}
		return "未知电话";
	}

	/**
	 * 
	* @Title: copyFile 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param fromFile
	* @param @param toFile
	* @param @param reWrite
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean copyFile(File fromFile, File toFile, Boolean reWrite) {

		if (!fromFile.exists()) {
			return false;
		}

		if (!fromFile.isFile()) {
			return false;
		}

		if (!fromFile.canRead()) {
			return false;
		}

		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}

		if (toFile.exists() && reWrite) {
			toFile.delete();
		}

		try {
			FileInputStream fosfrom = new FileInputStream(fromFile);
			FileOutputStream fosto = new FileOutputStream(toFile);

			byte[] bt = new byte[20 * 1024];
			int len;
			while ((len = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, len);
			}
			//关闭输入、输出流  
			fosfrom.close();
			fosto.close();

			return true; // 复制成功

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	* @Title: singleActivity 
	* @Description: 只保留单个activity
	* @param @param activity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void singleActivity(Activity activity) {
		actList.add(activity);
		if (actList.size() > 1) {
			actList.get(0).finish();
			actList.remove(0);
		}
	}

	/**
	 * 
	* @Title: clearActivity 
	* @Description: 清除activity
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void clearActivity() {
		for (int i = 0; i < actList.size(); i++) {
			actList.get(i).finish();
		}
		actList.clear();
	}

	/**
	 * 根据不同的平台设置不同的分享内容
	 */
	private static void setShareContent(Activity activity, UMSocialService mController) {

		String title = CommonManager.getInstance().getShareTtitle(activity);
		String content = CommonManager.getInstance().getShareContent(activity);
		String url = CommonManager.getInstance().getShareUrl(activity);
		String picUrl = CommonManager.getInstance().getSharePicUrl(activity);
		UMImage urlImage = new UMImage(activity, picUrl);

		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent(content);
		weixinContent.setTitle(title);
		weixinContent.setTargetUrl(url);
		weixinContent.setShareMedia(urlImage);
		mController.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent(content);
		circleMedia.setTitle(title);
		circleMedia.setShareMedia(urlImage);
		circleMedia.setTargetUrl(url);
		mController.setShareMedia(circleMedia);

		// 设置renren分享内容
		RenrenShareContent renrenShareContent = new RenrenShareContent();
		renrenShareContent.setShareContent(content);
		renrenShareContent.setShareImage(urlImage);
		renrenShareContent.setAppWebSite(url);
		mController.setShareMedia(renrenShareContent);

		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setShareContent(content);
		qzone.setTargetUrl(url);
		qzone.setTitle(title);
		qzone.setShareMedia(urlImage);
		mController.setShareMedia(qzone);

		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setShareContent(content);
		qqShareContent.setTitle(title);
		qqShareContent.setShareMedia(urlImage);
		qqShareContent.setTargetUrl(url);
		mController.setShareMedia(qqShareContent);

		// 腾讯微博
		TencentWbShareContent tencent = new TencentWbShareContent();
		tencent.setShareContent(content);
		tencent.setTitle(title);
		tencent.setShareMedia(urlImage);
		tencent.setTargetUrl(url);
		mController.setShareMedia(tencent);

		// 短信
		SmsShareContent sms = new SmsShareContent();
		sms.setShareContent(content);
		sms.setShareImage(urlImage);
		mController.setShareMedia(sms);

		// 新浪
		SinaShareContent sinaContent = new SinaShareContent();
		sinaContent.setShareContent(content);
		sinaContent.setShareImage(urlImage);
		sinaContent.setTitle(title);
		mController.setShareMedia(sinaContent);
	}

	/**
	* @Title: getMetaValue 
	* @Description: 获取ApiKey
	* @param @param context
	* @param @param metaKey
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {
			// Log.e(TAG, "error " + e.getMessage());
		}
		return apiKey;
	}
}
