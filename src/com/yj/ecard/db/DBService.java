/**   
* @Title: DBService.java
* @Package com.yj.ecard.db
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-1 下午6:17:35
* @version V1.0   
*/

package com.yj.ecard.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yj.ecard.publics.model.ScreenLockBean;
import com.yj.ecard.publics.model.TelAdBean;

/**
* @ClassName: DBService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-1 下午6:17:35
* 
*/

public class DBService {

	private SQLiteDatabase db;
	public static DBService mDBService;

	private DBService(Context context) {
		// 对于同一个dbHelper对象通过这个方法打开的多个数据对象是同一个
		db = DBHelper.getInstance(context).getWritableDatabase();
	}

	public synchronized static DBService getInstance(Context context) {
		if (mDBService == null) {
			mDBService = new DBService(context);
		}
		return mDBService;
	}

	/**
	 * 
	* @Title: save 
	* @Description: 增加电话广告记录
	* @param @param context
	* @param @param bean    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void save(TelAdBean bean) {
		String sql = "INSERT INTO tb_tel_ad(id,webUrl,smallUrl,largeUrl,smallLocalPath,largeLocalPath,smallPicDownload,largePicDownload) VALUES (?,?,?,?,?,?,?,?)";
		db.execSQL(
				sql,
				new Object[] { bean.getId(), bean.getWebUrl(), bean.getSmallUrl(), bean.getLargeUrl(),
						bean.getSmallLocalPath(), bean.getLargeLocalPath(), bean.isSmallPicDownload(),
						bean.isLargePicDownload() });

		// db.close();// 当应用中只有一处使用数据库时，不关数据库会提高系统系能，因为不用频繁打开数据库
	}

	/**
	 * 
	* @Title: save 
	* @Description: 增加锁屏广告记录 
	* @param @param bean    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void save(ScreenLockBean bean) {
		String sql = "INSERT INTO tb_screenlock_ad(id,imgUrl,webUrl,screenLockLocalPath,screenLockPicDownload) VALUES (?,?,?,?,?)";
		db.execSQL(sql, new Object[] { bean.getId(), bean.getImgUrl(), bean.getWebUrl(), bean.getScreenLockLocalPath(),
				bean.isScreenLockPicDownload() });
	}

	/**
	 * 
	* @Title: updateSmallPic 
	* @Description: 判断小图片是否下载完成更新
	* @param @param smallUrl   设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateSmallPic(String smallLocalPath, String smallUrl) {
		String sql = "UPDATE tb_tel_ad SET smallPicDownload = 1, smallLocalPath = ? WHERE smallUrl = ?";
		db.execSQL(sql, new Object[] { smallLocalPath, smallUrl });
	}

	/**
	 * 
	* @Title: updateScreenLockPic 
	* @Description: 更新锁屏图片下载状态
	* @param @param screenLockLocalPath
	* @param @param imgUrl    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateScreenLockPic(String screenLockLocalPath, String imgUrl) {
		String sql = "UPDATE tb_screenlock_ad SET screenLockPicDownload = 1, screenLockLocalPath = ? WHERE imgUrl = ?";
		db.execSQL(sql, new Object[] { screenLockLocalPath, imgUrl });
	}

	/**
	 * 
	* @Title: updateLargePic 
	* @Description: 判断大图片是否下载完成更新
	* @param @param largeUrl   设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateLargePic(String largeLocalPath, String largeUrl) {
		String sql = "UPDATE tb_tel_ad SET largePicDownload = 1, largeLocalPath = ? WHERE largeUrl = ?";
		db.execSQL(sql, new Object[] { largeLocalPath, largeUrl });
	}

	/**
	 * 
	* @Title: random 
	* @Description: 随机取值
	* @param @return    设定文件 
	* @return TelAdBean    返回类型 
	* @throws
	 */
	public TelAdBean random() {
		String sql = "SELECT * FROM tb_tel_ad where smallPicDownload = 1 and largePicDownload = 1 ORDER BY RANDOM() limit 1";
		Cursor cursor = db.rawQuery(sql, null);

		TelAdBean telAdBean = null;

		if (cursor.moveToFirst()) {
			telAdBean = new TelAdBean();
			telAdBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
			telAdBean.setWebUrl(cursor.getString(cursor.getColumnIndex("webUrl")));
			telAdBean.setSmallUrl(cursor.getString(cursor.getColumnIndex("smallUrl")));
			telAdBean.setLargeUrl(cursor.getString(cursor.getColumnIndex("largeUrl")));
			telAdBean.setSmallLocalPath(cursor.getString(cursor.getColumnIndex("smallLocalPath")));
			telAdBean.setLargeLocalPath(cursor.getString(cursor.getColumnIndex("largeLocalPath")));
		}

		cursor.close();
		return telAdBean;
	}

	/**
	 * 
	* @Title: randomValue 
	* @Description: 随机取值
	* @param @return    设定文件 
	* @return ScreenLockBean    返回类型 
	* @throws
	 */
	public ScreenLockBean randomValue() {
		String sql = "SELECT * FROM tb_screenlock_ad where screenLockPicDownload = 1 ORDER BY RANDOM() limit 1";
		Cursor cursor = db.rawQuery(sql, null);

		ScreenLockBean mScreenLockBean = null;

		if (cursor.moveToFirst()) {
			mScreenLockBean = new ScreenLockBean();
			mScreenLockBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
			mScreenLockBean.setImgUrl(cursor.getString(cursor.getColumnIndex("imgUrl")));
			mScreenLockBean.setWebUrl(cursor.getString(cursor.getColumnIndex("webUrl")));
			mScreenLockBean.setScreenLockLocalPath(cursor.getString(cursor.getColumnIndex("screenLockLocalPath")));
		}

		cursor.close();
		return mScreenLockBean;
	}

	/**
	 * 
	* @Title: clear 
	* @Description: 清空表数据
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void clear() {
		db.execSQL("DELETE FROM tb_tel_ad");
	}

	/**
	 * 
	* @Title: clearScreenLock 
	* @Description: 清空锁屏表数据
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void clearScreenLock() {
		db.execSQL("DELETE FROM tb_screenlock_ad");
	}

	/**
	 * 
	* @Title: clearRecord 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void clearRecord() {
		db.execSQL("DELETE FROM tb_ad_record");
	}

	/**
	 * 
	* @Title: clearScreenLockRecord 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void clearScreenLockRecord() {
		db.execSQL("DELETE FROM tb_screenlock_ad_record");
	}

	/**
	 * 
	* @Title: getRecordCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getRecordCount(String smallUrl) {

		String sql = "select count(*) from tb_ad_record where smallUrl = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { smallUrl });
		int count = 0;
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: getScreenLockRecordCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imgUrl
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getScreenLockRecordCount(String imgUrl) {

		String sql = "select count(*) from tb_screenlock_ad_record where imgUrl = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { imgUrl });
		int count = 0;
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: getCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount() {
		Cursor cursor = db.rawQuery("select count(*) from tb_tel_ad where smallPicDownload = 1", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: getScreenLockCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getScreenLockCount() {
		Cursor cursor = db.rawQuery("select count(*) from tb_screenlock_ad where screenLockPicDownload = 1", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: getTotalCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getTotalCount() {
		Cursor cursor = db.rawQuery("select count(*) from tb_ad_record", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: getScreenLockTotalCount 
	* @Description: 获取锁屏总记录条数
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getScreenLockTotalCount() {
		Cursor cursor = db.rawQuery("select count(*) from tb_screenlock_ad_record", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: save 
	* @Description: 保存已播放的图片
	* @param @param smallUrl    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void save(String smallUrl) {
		String sql = "INSERT INTO tb_ad_record(smallUrl) VALUES (?)";
		db.execSQL(sql, new Object[] { smallUrl });
	}

	/**
	 * 
	* @Title: saveScrennLock 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imgUrl    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveScrennLock(String imgUrl) {
		String sql = "INSERT INTO tb_screenlock_ad_record(imgUrl) VALUES (?)";
		db.execSQL(sql, new Object[] { imgUrl });
	}

	/**
	 * 
	* @Title: updateCall 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param result    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateCall(int result) {
		String sql = "UPDATE tb_call_state SET isCall = ? where id = 1";
		db.execSQL(sql, new Object[] { result });
	}

	/**
	 * 
	* @Title: getCallState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getCallState() {
		Cursor cursor = db.rawQuery("select count(*) from tb_call_state where isCall = 1", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}

	/**
	 * 
	* @Title: setScreenLockState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param result    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setScreenLockState(int result) {
		String sql = "UPDATE tb_screenlock_state SET isLock = ? where id = 1";
		db.execSQL(sql, new Object[] { result });
	}

	/**
	 * 
	* @Title: getScreenLockState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getScreenLockState() {
		Cursor cursor = db.rawQuery("select count(*) from tb_screenlock_state where isLock = 1", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		return count;
	}

}
