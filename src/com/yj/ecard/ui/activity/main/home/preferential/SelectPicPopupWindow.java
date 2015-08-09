package com.yj.ecard.ui.activity.main.home.preferential;

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import com.yj.ecard.R;
import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.utils.ToastUtil;

/**
 * 
* @ClassName: SelectPicPopupWindow
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-6-29 上午11:01:18
*
 */
public class SelectPicPopupWindow extends Activity implements OnClickListener {

	private File PHOTO_DIR = null;// 拍照的照片存储位置 
	private File mCurrentPhotoFile;// 照相机拍照得到的图片
	private static final int CAMERA_WITH_DATA = 3023;// 拍照 
	private static final int PHOTO_PICKED_WITH_DATA = 3021;// 相册 
	private static final int CAMERA_CROP_DATA = 3022;// 裁剪 

	private final int[] btns = { R.id.btn_take_photo, R.id.btn_pick_photo, R.id.btn_cancel };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_popup_window);
		// dialog样式默认不全屏，要设置全屏显示
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		initViews();
	}

	// 实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	/** 
	* @Title: initViews 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initViews() {
		// 添加按钮监听
		for (int btn : btns)
			findViewById(btn).setOnClickListener(this);

		//初始化图片保存路径
		String photoDir = AbFileUtil.getDefaultImageDownPathDir();
		if (AbStrUtil.isEmpty(photoDir)) {
			ToastUtil.show(this, "存储卡不存在!", ToastUtil.LENGTH_LONG);
		} else {
			PHOTO_DIR = new File(photoDir);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		// 拍照
		case R.id.btn_take_photo:
			toTakePhoto();
			break;

		// 相册	
		case R.id.btn_pick_photo:
			try {
				//选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
				//有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
			} catch (ActivityNotFoundException e) {
				ToastUtil.show(this, "没有找到相片!", ToastUtil.LENGTH_LONG);
			}
			break;

		// 取消	
		case R.id.btn_cancel:
			finish();
			break;
		}
	}

	/**
	 * 
	* @Title: toTakePhoto 
	* @Description: 调用相机进行拍照
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void toTakePhoto() {
		String status = Environment.getExternalStorageState();
		//判断是否有SD卡,如果有sd卡存入sd卡在说，没有sd卡直接转换为图片
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				String mFileName = System.currentTimeMillis() + ".jpg";
				mCurrentPhotoFile = new File(PHOTO_DIR, mFileName);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
				startActivityForResult(intent, CAMERA_WITH_DATA);
			} catch (Exception e) {
				ToastUtil.show(this, "未找到系统相机程序", ToastUtil.LENGTH_LONG);
			}
		} else {
			ToastUtil.show(this, "没有可用的存储卡", ToastUtil.LENGTH_LONG);
		}
	}

	/**
	 * 
	* @Title: getPath 
	* @Description: 从相册得到的url转换为SD卡中图片路径
	* @param @param uri
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getPath(Uri uri) {
		if (AbStrUtil.isEmpty(uri.getAuthority())) {
			return null;
		}
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		String path = cursor.getString(column_index);
		return path;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
		if (resultCode != RESULT_OK) {
			return;
		}
		//选择完或者拍完照后会在这里处理，然后我们继续使用setResult返回Intent以便可以传递数据和调用
		switch (requestCode) {

		case PHOTO_PICKED_WITH_DATA:
			Uri uri = mIntent.getData();
			String currentFilePath = getPath(uri);
			if (!AbStrUtil.isEmpty(currentFilePath)) {
				Intent intent1 = new Intent(this, CropImageActivity.class);
				intent1.putExtra("PATH", currentFilePath);
				startActivityForResult(intent1, CAMERA_CROP_DATA);
			} else {
				ToastUtil.show(this, "未在存储卡中找到这个文件", ToastUtil.LENGTH_SHORT);
			}
			break;

		case CAMERA_WITH_DATA:
			System.out.println("将要进行裁剪的图片的路径是 = " + mCurrentPhotoFile.getPath());
			String currentFilePath2 = mCurrentPhotoFile.getPath();
			Intent intent2 = new Intent(this, CropImageActivity.class);
			intent2.putExtra("PATH", currentFilePath2);
			startActivityForResult(intent2, CAMERA_CROP_DATA);
			break;

		case CAMERA_CROP_DATA:
			String path = mIntent.getStringExtra("PATH");
			System.out.println("裁剪后得到的图片的路径是 = " + path);
			// 保存裁剪图片路径到sp
			CommonManager.getInstance().setCropImagePath(this, path);
			finish();
			break;
		}
	}

}
