/**   
* @Title: DBHelper.java
* @Package com.yj.ecard.db
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-6-1 下午5:18:43
* @version V1.0   
*/

package com.yj.ecard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
* @ClassName: DBHelper
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-1 下午5:18:43
* 
*/

public class DBHelper extends SQLiteOpenHelper {

	private static DBHelper mDBHelper;
	private static final int DATABASE_VERSION = 1009;
	private static final int FIRST_DATABASE_VERSION = 1000;
	private static final String DATABASE_NAME = "leying.db";

	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public synchronized static DBHelper getInstance(Context context) {
		if (mDBHelper == null) {
			mDBHelper = new DBHelper(context);
		}
		return mDBHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 请不要修改FIRST_DATABASE_VERSION的值，其为第一个数据库版本大小
		onUpgrade(db, FIRST_DATABASE_VERSION, DATABASE_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		initTable(db);
	}

	/** 
	* @Title: initTable 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param db    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initTable(SQLiteDatabase db) {
		db.execSQL("drop table if exists tb_tel_ad");
		db.execSQL("drop table if exists tb_screenlock_ad");
		db.execSQL("drop table if exists tb_ad_record");
		db.execSQL("drop table if exists tb_screenlock_ad_record");
		db.execSQL("drop table if exists tb_call_state");
		db.execSQL("drop table if exists tb_screenlock_state");

		String sql1 = "CREATE TABLE tb_tel_ad(" + "id int(10), " + "smallUrl varchar(100), "
				+ "largeUrl varchar(100), " + "smallLocalPath varchar(100), " + "largeLocalPath varchar(100), "
				+ "smallPicDownload boolean," + "largePicDownload boolean" + ")";

		String sql2 = "CREATE TABLE tb_screenlock_ad(id int(10), imgUrl varchar(100), webUrl varchar(100), screenLockLocalPath varchar(100), screenLockPicDownload boolean)";

		String sql3 = "CREATE TABLE tb_ad_record(smallUrl varchar(100))";

		String sql4 = "CREATE TABLE tb_screenlock_ad_record(imgUrl varchar(100))";

		String sql5 = "CREATE TABLE tb_call_state(id int(4),isCall int(4))";
		String sql6 = "INSERT INTO tb_call_state(id,isCall) VALUES (1,0)";

		String sql7 = "CREATE TABLE tb_screenlock_state(id int(4),isLock int(4))";
		String sql8 = "INSERT INTO tb_screenlock_state(id,isLock) VALUES (1,0)";

		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		db.execSQL(sql5);
		db.execSQL(sql6);
		db.execSQL(sql7);
		db.execSQL(sql8);
	}
}
