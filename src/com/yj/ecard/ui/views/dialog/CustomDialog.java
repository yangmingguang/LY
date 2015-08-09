/**   
* @Title: CustomDialog.java
* @Package com.iframe.source.ui.views.dialog
* @Description: TODO(用一句话描述该文件做什么)
* @author mingguang.yang   
* @date 2015-2-2 下午3:25:32
* @version V1.0   
*/

package com.yj.ecard.ui.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yj.ecard.R;
import com.yj.ecard.publics.utils.Utils;

/**
* @ClassName: CustomDialog
* @Description: Create custom Dialog windows for your application
* @author mingguang.yang
* @date 2015-2-2 下午3:25:32
* 
*/
public class CustomDialog extends Dialog {

	private static View mDialogView;
	private static TextView tvTitle, tvMessage, negativeBtn, positiveBtn;

	public CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	/**
	* Helper class for creating a custom dialog
	*/
	public static class Builder {

		private String title;
		private String message;
		private Context context;
		private String positiveButtonText;
		private String negativeButtonText;
		private DialogInterface.OnClickListener positiveButtonClickListener, negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		 * Set the Dialog title from resource
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
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
		* Set the positive button resource and it's listener
		* @param positiveButtonText
		* @param listener
		* @return
		*/
		public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the positive button text and it's listener
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		* Create the custom dialog
		*/
		public CustomDialog create() {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
			View layout = inflater.inflate(R.layout.common_dialog_layout, null);

			//  instantiate the dialog with components
			tvTitle = (TextView) layout.findViewById(R.id.tv_title);
			tvMessage = (TextView) layout.findViewById(R.id.tv_message);
			positiveBtn = (TextView) layout.findViewById(R.id.btn_positive);
			negativeBtn = (TextView) layout.findViewById(R.id.btn_negative);
			mDialogView = layout.findViewById(R.id.custom_dialog_layout);

			// set the title、message
			tvTitle.setText(title);
			tvMessage.setText(message);
			positiveBtn.setText(positiveButtonText);
			negativeBtn.setText(negativeButtonText);

			// set the confirm button
			positiveBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
				}
			});

			// set the cancel button
			negativeBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
				}
			});

			dialog.setContentView(layout);
			dialog.setCanceledOnTouchOutside(false); // 设置点击对话框以外的区域,对话框不消失
			Utils.setScreenAdapter(context, dialog); // 设置适配手机、平板的屏幕大小

			//Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_from_to_dialog);
			//mDialogView.startAnimation(animation);

			return dialog;
		}
	}
}
