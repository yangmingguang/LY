/**   
* @Title: UserManager.java
* @Package com.yj.ecard.business.user
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-5-31 下午3:13:23
* @version V1.0   
*/

package com.yj.ecard.business.user;

import org.json.JSONObject;

import android.content.Context;

import com.yj.ecard.business.common.CommonManager;
import com.yj.ecard.publics.http.model.request.CheckCodeRequest;
import com.yj.ecard.publics.http.model.request.GetValidateCodeRequest;
import com.yj.ecard.publics.http.model.request.ModifyPwRequest;
import com.yj.ecard.publics.http.model.request.RegisterRequest;
import com.yj.ecard.publics.http.model.request.ResetPwRequest;
import com.yj.ecard.publics.http.model.request.UserLoginRequest;
import com.yj.ecard.publics.http.model.request.ValidateCodeRequest;
import com.yj.ecard.publics.http.model.response.CheckCodeResponse;
import com.yj.ecard.publics.http.model.response.GetValidateCodeResponse;
import com.yj.ecard.publics.http.model.response.ModifyPwResponse;
import com.yj.ecard.publics.http.model.response.RegisterResponse;
import com.yj.ecard.publics.http.model.response.ResetPwResponse;
import com.yj.ecard.publics.http.model.response.UserLoginResponse;
import com.yj.ecard.publics.http.model.response.ValidateCodeResponse;
import com.yj.ecard.publics.http.net.DataFetcher;
import com.yj.ecard.publics.http.volley.Response.ErrorListener;
import com.yj.ecard.publics.http.volley.Response.Listener;
import com.yj.ecard.publics.http.volley.VolleyError;
import com.yj.ecard.publics.utils.Constan;
import com.yj.ecard.publics.utils.JsonUtil;
import com.yj.ecard.publics.utils.LogUtil;
import com.yj.ecard.publics.utils.SharedPrefsUtil;
import com.yj.ecard.publics.utils.Utils;
import com.yj.ecard.publics.utils.WeakHandler;

/**
* @ClassName: UserManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-31 下午3:13:23
* 
*/

public class UserManager {

	public static final int onSuccess = 200;
	public static final int onEmpty = 300;
	public static final int onFailure = 500;
	public static final int onValidateCodeSuccess = 201;
	public static final int onValidateCodeEmpty = 301;
	public static final int onValidateCodeFailure = 501;
	public static final int onCheckCodeSuccess = 202;
	public static final int onCheckCodeEmpty = 302;
	public static final int onCheckCodeFailure = 502;
	public static final int onResetPwSuccess = 203;
	public static final int onResetPwEmpty = 303;
	public static final int onResetPwFailure = 503;

	private static volatile UserManager mUserManager;

	/******************************单例开始********************************/

	private UserManager() {
		// TODO Auto-generated constructor stub
	}

	public static UserManager getInstance() {
		if (mUserManager == null) {
			synchronized (UserManager.class) {
				if (mUserManager == null) {
					mUserManager = new UserManager();
				}
			}
		}
		return mUserManager;
	}

	/******************************单例结束********************************/

	public String getUserName(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.USER_NAME, "");
	}

	public void setUserName(Context context, String userName) {
		SharedPrefsUtil.putValue(context, Constan.USER_NAME, userName);
	}

	public String getHeadUrl(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.HEAD_URL, "");
	}

	public void setHeadUrl(Context context, String url) {
		SharedPrefsUtil.putValue(context, Constan.HEAD_URL, url);
	}

	public int getUserId(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.USER_ID, 0);
	}

	public void setUserId(Context context, int userId) {
		SharedPrefsUtil.putValue(context, Constan.USER_ID, userId);
	}

	public String getPassword(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.PASSWORD, "");
	}

	public void setPassword(Context context, String password) {
		SharedPrefsUtil.putValue(context, Constan.PASSWORD, password);
	}

	public int getLevel(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.LEVEL, 1);
	}

	public void setLevel(Context context, int level) {
		SharedPrefsUtil.putValue(context, Constan.LEVEL, level);
	}

	public boolean isLogin(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.IS_LOGIN, false);
	}

	public void setLogin(Context context, boolean isLogin) {
		SharedPrefsUtil.putValue(context, Constan.IS_LOGIN, isLogin);
	}

	public void setAmount(Context context, String amount) {
		SharedPrefsUtil.putValue(context, Constan.AMOUNT, amount);
	}

	public String getAmount(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.AMOUNT, "0.0元");
	}

	public void setCanAmount(Context context, String canAmount) {
		SharedPrefsUtil.putValue(context, Constan.CAN_AMOUNT, canAmount);
	}

	public String getCanAmount(Context context) {
		return SharedPrefsUtil.getValue(context, Constan.CAN_AMOUNT, "0.0元");
	}

	/**
	 * 
	* @Title: clearUserInfo 
	* @Description: 清除用户信息
	* @param @param context    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void clearUserInfo(Context context) {
		setLogin(context, false);
		setUserName(context, null);
		setPassword(context, null);
	}

	/**
	 * 
	* @Title: getUserLoginData 
	* @Description: 获取登录信息
	* @param @param context
	* @param @param handler
	* @param @param username
	* @param @param password    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getUserLoginData(final Context context, final WeakHandler handler, String userName, String passWord) {
		UserLoginRequest request = new UserLoginRequest();
		request.setUserName(userName);
		request.setUserPwd(passWord);
		request.setLat(Double.parseDouble(CommonManager.getInstance().getLocationlat(context)));
		request.setLng(Double.parseDouble(CommonManager.getInstance().getLocationlng(context)));
		request.setMobileType(Utils.getMobileType());
		request.setMobileImei(Utils.getIMEI(context));
		DataFetcher.getInstance().getUserLoginResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				UserLoginResponse userLoginResponse = (UserLoginResponse) JsonUtil.jsonToBean(response,
						UserLoginResponse.class);

				int stateCode = userLoginResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					// 登录成功，保存数据
					setLevel(context, userLoginResponse.level);
					setUserId(context, userLoginResponse.userId);
					setPassword(context, userLoginResponse.userPwd);
					setUserName(context, userLoginResponse.userName);
					setHeadUrl(context, userLoginResponse.avatar);
					setAmount(context, userLoginResponse.amount + "元");
					CommonManager.getInstance().setCity(context, userLoginResponse.city);
					CommonManager.getInstance().setCountry(context, userLoginResponse.country);
					CommonManager.getInstance().setProvince(context, userLoginResponse.province);
					CommonManager.getInstance().setAreaId(context, userLoginResponse.areaId);
					handler.sendEmptyMessage(onSuccess);
					break;

				case Constan.EMPTY_CODE:
					handler.sendMessage(handler.obtainMessage(onEmpty, userLoginResponse.status.msg));
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				handler.sendEmptyMessage(onFailure);
			}

		});
	}

	/**
	 * 
	* @Title: getUserRegisterData 
	* @Description: 提交用户注册信息
	* @param @param context
	* @param @param handler
	* @param @param userName
	* @param @param passWord
	* @param @param referee    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getUserRegisterData(final Context context, final WeakHandler handler, String userName, String passWord,
			String referee) {
		RegisterRequest request = new RegisterRequest();
		request.setUserName(userName);
		request.setUserPwd(passWord);
		request.setReferee(referee);
		request.setLat(Double.parseDouble(CommonManager.getInstance().getLocationlat(context)));
		request.setLng(Double.parseDouble(CommonManager.getInstance().getLocationlng(context)));
		request.setMobileType(Utils.getMobileType());
		request.setMobileImei(Utils.getIMEI(context));
		DataFetcher.getInstance().getRegisterResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				RegisterResponse registerResponse = (RegisterResponse) JsonUtil.jsonToBean(response,
						RegisterResponse.class);

				int stateCode = registerResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					// 注册成功，保存数据
					setLevel(context, registerResponse.level);
					setUserId(context, registerResponse.userId);
					setPassword(context, registerResponse.userPwd);
					setUserName(context, registerResponse.userName);
					setHeadUrl(context, registerResponse.avatar);
					setAmount(context, registerResponse.amount + "元");
					CommonManager.getInstance().setCity(context, registerResponse.city);
					CommonManager.getInstance().setCountry(context, registerResponse.country);
					CommonManager.getInstance().setProvince(context, registerResponse.province);
					CommonManager.getInstance().setAreaId(context, registerResponse.areaId);
					handler.sendEmptyMessage(onSuccess);
					break;

				case Constan.EMPTY_CODE:
					handler.sendEmptyMessage(onEmpty);
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onFailure);
			}

		});
	}

	/**
	 * 
	* @Title: getValidateCodeData 
	* @Description: 获取手机验证码
	* @param @param context
	* @param @param handler
	* @param @param phoneNum    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValidateCodeData(Context context, final WeakHandler handler, String phoneNum) {
		ValidateCodeRequest request = new ValidateCodeRequest();
		request.setPhoneNum(phoneNum);
		request.setMobileImei(Utils.getIMEI(context));
		DataFetcher.getInstance().getValidateCodeResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ValidateCodeResponse validateCodeResponse = (ValidateCodeResponse) JsonUtil.jsonToBean(response,
						ValidateCodeResponse.class);

				int stateCode = validateCodeResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendMessage(handler.obtainMessage(onValidateCodeSuccess, validateCodeResponse));
					break;

				case Constan.EMPTY_CODE:
					handler.sendMessage(handler.obtainMessage(onValidateCodeEmpty, validateCodeResponse.status.msg));
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onValidateCodeFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onValidateCodeFailure);
			}

		}, true);
	}

	/**
	 * 
	* @Title: getCheckCodeData 
	* @Description: 检测验证码
	* @param @param context
	* @param @param handler
	* @param @param phoneNum
	* @param @param code    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getCheckCodeData(Context context, final WeakHandler handler, String phoneNum, String validateCode) {
		CheckCodeRequest request = new CheckCodeRequest();
		request.setMobile(phoneNum);
		request.setMobileCode(validateCode);
		DataFetcher.getInstance().getCheckCodeResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				CheckCodeResponse checkCodeResponse = (CheckCodeResponse) JsonUtil.jsonToBean(response,
						CheckCodeResponse.class);

				int stateCode = checkCodeResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendEmptyMessage(onCheckCodeSuccess);
					break;

				case Constan.EMPTY_CODE:
					handler.sendMessage(handler.obtainMessage(onCheckCodeEmpty, checkCodeResponse.status.msg));
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onCheckCodeFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onCheckCodeFailure);
			}

		}, true);
	}

	/**
	 * 
	* @Title: getModifyPwData 
	* @Description: 修改用户密码
	* @param @param context
	* @param @param handler
	* @param @param oldPassword
	* @param @param newPassword    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getModifyPwData(Context context, final WeakHandler handler, String oldPassword, String newPassword) {
		ModifyPwRequest request = new ModifyPwRequest();
		request.setUserName(UserManager.getInstance().getUserName(context));
		request.setOldPassword(oldPassword);
		request.setNewPassword(newPassword);
		DataFetcher.getInstance().getModifyPwResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ModifyPwResponse modifyPwResponse = (ModifyPwResponse) JsonUtil.jsonToBean(response,
						ModifyPwResponse.class);

				int stateCode = modifyPwResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendEmptyMessage(onSuccess);
					break;

				case Constan.EMPTY_CODE:
					handler.sendEmptyMessage(onEmpty);
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onFailure);
			}

		}, true);
	}

	/**
	 * 
	* @Title: getValidateCode 
	* @Description: 获取短信
	* @param @param context
	* @param @param handler
	* @param @param mobileNum    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getValidateCode(Context context, final WeakHandler handler, String mobileNum) {
		GetValidateCodeRequest request = new GetValidateCodeRequest();
		request.setMobileNum(mobileNum);
		DataFetcher.getInstance().getValidateCode(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				GetValidateCodeResponse mGetValidateCodeResponse = (GetValidateCodeResponse) JsonUtil.jsonToBean(
						response, GetValidateCodeResponse.class);

				int stateCode = mGetValidateCodeResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendEmptyMessage(onSuccess);
					break;

				case Constan.EMPTY_CODE:
					handler.sendMessage(handler.obtainMessage(onEmpty, mGetValidateCodeResponse.status.msg));
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onFailure);
			}

		}, true);
	}

	/**
	 * 
	* @Title: getResetPwData 
	* @Description: 重置密码
	* @param @param context
	* @param @param handler
	* @param @param verifyCode
	* @param @param newPassword    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getResetPwData(Context context, final WeakHandler handler, String verifyCode, String newPassword) {
		ResetPwRequest request = new ResetPwRequest();
		request.setMobileNum(UserManager.getInstance().getUserName(context));
		request.setVerifyCode(verifyCode);
		request.setNewPassword(newPassword);
		DataFetcher.getInstance().getResetPwResult(request, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				LogUtil.getLogger().d("response==>" + response.toString());
				ResetPwResponse mResetPwResponse = (ResetPwResponse) JsonUtil.jsonToBean(response,
						ResetPwResponse.class);

				int stateCode = mResetPwResponse.status.code;
				switch (stateCode) {
				case Constan.SUCCESS_CODE:
					handler.sendEmptyMessage(onResetPwSuccess);
					break;

				case Constan.EMPTY_CODE:
					handler.sendEmptyMessage(onResetPwEmpty);
					break;

				case Constan.ERROR_CODE:
					handler.sendEmptyMessage(onResetPwFailure);
					break;
				}
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(onResetPwFailure);
			}

		}, true);
	}
}
