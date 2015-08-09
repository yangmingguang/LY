/**   
* @Title: CustomProgressDialog.java
* @Package com.iframe.source.ui.views.dialog
* @Description: TODO(用一句话描述该文件做什么)
* @author mingguang.yang   
* @date 2015-2-2 下午3:25:32
* @version V1.0   
*/

package com.yj.ecard.ui.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.yj.ecard.R;

/**
* @ClassName: CustomProgressDialog
* @Description: Create custom Progress Dialog windows for your application
* @author mingguang.yang
* @date 2015-2-2 下午3:25:32
* 
*/
public class CustomProgressDialog extends Dialog {

	public static CustomProgressDialog mCustomProgressDialog;

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	/**
	* Helper class for creating a custom dialog
	*/
	public static class Builder {

		private String message;
		private Context context;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		* Set the Dialog message from String
		* @param title
		* @return
		*/
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		* Create the custom dialog
		*/
		public CustomProgressDialog create() {

			// instantiate the dialog with the custom Theme
			mCustomProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialogStyle);
			mCustomProgressDialog.setContentView(R.layout.custom_progress_dialog_loading);
			mCustomProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
			mCustomProgressDialog.setCanceledOnTouchOutside(false); // 设置点击对话框以外的区域,对话框不消失

			// set textView
			TextView tvMessage = (TextView) mCustomProgressDialog.findViewById(R.id.tv_message);
			tvMessage.setText(message);

			return mCustomProgressDialog;
		}
	}

}
