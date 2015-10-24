/**   
* @Title: MyApplication.java
* @Package com.yj.ecard
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-24 下午3:18:41
* @version V1.0   
*/

package com.yj.ecard;

import android.content.Context;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaApplication;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yj.ecard.business.crash.CrashHandler;
import com.yj.ecard.db.DBHelper;
import com.yj.ecard.publics.http.volley.toolbox.RequestQueueManager;
import com.yj.ecard.publics.utils.ImageLoaderUtil;

/**
* @ClassName: MyApplication
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午3:18:41
* 
*/

public class MyApplication extends FrontiaApplication {

	/** 
	 * 百度Frontia组件  
	 * App Key : UwIcYepqCItTGSyVLpWfiGLL    
	 * Secret Key : kNi1rUOM8ae8aIXqkWi42XDwziGIhrt4
	 */

	/** 
	 * 百度云推送
	 * App Key : B5yXQAdhC0hIz1rlGZ7nPeqO   
	 * Secret Key : zQCDcRyQM8Hkr6LSlc3Q541wGIghBDFC
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		RequestQueueManager.init(this);
		initDataBase(this);
		initUIL(this);
		CrashHandler.getInstance().init(this);
		// 百度Frontia组件 
		Frontia.init(this.getApplicationContext(), "UwIcYepqCItTGSyVLpWfiGLL");
		// 百度云推送
		PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "B5yXQAdhC0hIz1rlGZ7nPeqO");
	}

	/** 
	* @Title: initDataBase 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param myApplication    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initDataBase(Context context) {
		// TODO Auto-generated method stub
		DBHelper.getInstance(context).getWritableDatabase();
	}

	/**
	 * 
	* @Title: initUIL 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void initUIL(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new LruMemoryCache(ImageLoaderUtil.calculateMemoryCacheSize(context))).threadPoolSize(5)
				/*.memoryCache(new WeakMemoryCache())*/
				.discCacheFileCount(100).memoryCacheSize(ImageLoaderUtil.calculateMemoryCacheSize(context))
				.discCacheSize(50 * 1024 * 1024).memoryCacheExtraOptions(320, 480)/*.writeDebugLogs()*/// Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}

}
