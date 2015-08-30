/**   
* @Title: AlipayManager.java
* @Package com.yj.ecard.business.alipay
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-8-30 下午4:12:57
* @version V1.0   
*/

package com.yj.ecard.business.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.yj.ecard.publics.utils.WeakHandler;
import com.yj.ecard.publics.utils.alipay.SignUtils;

/**
* @ClassName: AlipayManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-8-30 下午4:12:57
* 
*/

public class AlipayManager {

	public static final int SDK_PAY_FLAG = 1;
	public static final int SDK_CHECK_FLAG = 2;
	private static volatile AlipayManager mAlipayManager;

	//商户PID
	public static final String PARTNER = "2088912240055925";
	//商户收款账号
	public static final String SELLER = "13803745019@139.com";
	//商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKhJPMSvNmfipOof417MlWCw/AmE6JI54tA8M9CZPYpFOvW1rMuiXvwcYEgJRw2PtbYv06aGEvo1sHzvifK0k0A7e1gSxYpvWc9QJhFJ17T+cl6JJoZMxNZ+iGvnq63KM8oC/wMZDVE9q7jDCHZ7FTv7NzSqrZB+kkMrSKr8CNYhAgMBAAECgYAeamY+UkCuFAukeTD6IFXbTQGwosH6M9YntsnSqO+zfwHCCRz7DRTH0kUgn/SkodXA4PILBe6GJJAKdKzlHfgC9/DEoftagOQ2XWC05TvIMobtVPQfzPimCHZ0qdQB7eA+wlrzujAii+GaAVPiyxfrdEPafb8O3W75+mkdBFWmlQJBANRdwWHVIWkTWPPh7de7AvyeKIDHADzqNsdYLFbE7anvyLdFltV90oYg036KZ2MlKZO01VByNAXzPV7iNf7lWocCQQDK3Oliz2tJ3RiMp64BNyPExV3H2ZirELLA9+bcuaD8JbvdDY79/i2l7NiKEL2HRRrv8Tps5KkIm2eLG12uTawXAkEAnxWUtfr4ykPqENggWckIFcFMyhtI/FjfrDlLDbz75J9xlokU0t0shbm1dUes50KBgNqoKVlDy2lJgfinRkj9pQJBALo42uHC6IlQthWAY+JnvKUITNOFs3jthjpQF5RtrxNojDEbHjWZHExhxFHRljpTvKugtPJ7YZQuHHn9MB0u3hcCQFQfsHTxZLi14aghYdRqqLlbT5o1aXSkbNLkbgiLIAImTUWYcHD5hQhuGNltz0++JGBYxBItc54l8rngM2uS2e4=";
	//支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoSTzErzZn4qTqH+NezJVgsPwJhOiSOeLQPDPQmT2KRTr1tazLol78HGBICUcNj7W2L9OmhhL6NbB874nytJNAO3tYEsWKb1nPUCYRSde0/nJeiSaGTMTWfohr56utyjPKAv8DGQ1RPau4wwh2exU7+zc0qq2QfpJDK0iq/AjWIQIDAQAB";

	/******************************单例开始********************************/

	private AlipayManager() {
		// TODO Auto-generated constructor stub
	}

	public static AlipayManager getInstance() {
		if (mAlipayManager == null) {
			synchronized (AlipayManager.class) {
				if (mAlipayManager == null) {
					mAlipayManager = new AlipayManager();
				}
			}
		}
		return mAlipayManager;
	}

	/******************************单例开始********************************/

	/**
	* @Title: SDKPay 
	* @Description: 调用SDK支付
	* @param @param activity
	* @param @param handler
	* @param @param subject
	* @param @param body
	* @param @param price    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void SDKPay(final Activity activity, final WeakHandler handler, String subject, String body,
			String orderNum, String price) {
		// 订单
		//String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
		String orderInfo = getOrderInfo(subject, body, orderNum, price);

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(activity);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	* @Title: getOrderInfo 
	* @Description: 创建订单信息
	* @param @param subject
	* @param @param body
	* @param @param price
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getOrderInfo(String subject, String body, String orderNum, String price) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNum + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		// orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";
		orderInfo += "&notify_url=" + "\"" + "http://pay.307755.com/notify_url.aspx" + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	* @Title: getOutTradeNo 
	* @Description: 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	* @Title: sign 
	* @Description: 对订单信息进行签名
	* @param @param content 待签名订单信息
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	* @Title: getSignType 
	* @Description: 获取签名方式
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

}
