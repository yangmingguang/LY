package com.yj.ecard.publics.http.net;

/**
 * 
* @ClassName: WebUrl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-5-24 下午3:38:55
*
 */

public class WebUrl {

	// 0.接口地址
	public static final String NEW_BASE_URL = "http://106.3.44.176/api/";

	// 1.获取广告列表
	public static final String GET_TEL_AD_LIST_URL = NEW_BASE_URL + "Advs/GetAdvList?userName=%s";

	// 2.兑换列表
	public static final String GET_PRODUCT_LIST_URL = NEW_BASE_URL
			+ "Product/GetProductlist?sortId=%s&pageIndex=%s&pageSize=%s";

	// 3.产品详情
	public static final String GET_PRODUCT_DETAIL_URL = NEW_BASE_URL + "Product/productinfos?username=%s&id=%s";

	// 4.优惠详情
	public static final String GET_PREFERENTIAL_DETAIL_URL = NEW_BASE_URL
			+ "youhui/Getyouhuiinfo?userName=%s&password=%s&userId=%s&id=%s";

	// 5.已兑换列表
	public static final String GET_EXCHANGE_RECORD_LIST_URL = NEW_BASE_URL
			+ "Product/GetDuihuanlist?username=%s&pageIndex=%s&pageSize=%s";

	// 6.获取用户信息
	public static final String GET_USER_INFO_URL = NEW_BASE_URL + "Users/getmyearnings?userName=%s&userId=%s";

	// 7.我发的优惠
	public static final String GET_MY_PREFERENTIAL_LIST_URL = NEW_BASE_URL
			+ "youhui/GetMypreferentialList?userId=%s&pageIndex=%s&pageSize=%s";

	//8.逛优惠
	public static final String GET_PREFERENTIAL_LIST_URL = NEW_BASE_URL
			+ "youhui/GetYouhuiList?userId=%s&areaId=%s&pageIndex=%s&pageSize=%s";

	// 9.排行榜
	public static final String GET_RANKING_LIST_URL = NEW_BASE_URL + "Users/Getranking";

	// 10.今日收入
	public static final String GET_INCOME_URL = NEW_BASE_URL
			+ "Users/GetAmontList?userId=%s&userName=%s&pageIndex=%s&pageSize=%s";

	// 11.朋友圈
	public static final String GET_FRIENDS_LIST_URL = NEW_BASE_URL
			+ "Users/Getfriends?userId=%s&userName=%s&pageIndex=%s&pageSize=%s";

	// 12.精彩广告
	public static final String GET_WONDERFUL_AD_LIST_URL = NEW_BASE_URL
			+ "advs/GetAdvDir?sortId=%s&userId=%s&userName=%s&pageIndex=%s&pageSize=%s";

	// 13.精彩广告详情
	public static final String GET_WONDERFUL_AD_DETAIL_URL = NEW_BASE_URL + "advs/GetAdvInfo?sortId=%s&id=%s";

	// 14.秒杀列表
	public static final String GET_VALUE_SPIKE_LIST_URL = NEW_BASE_URL
			+ "miaosha/Getlist?periodTime=%s&province=%s&city=%s&country=%s&areaId=%s&pageIndex=%s&pageSize=%s";

	// 15.秒杀详情
	public static final String GET_VALUE_SPIKE_DETAIL_URL = NEW_BASE_URL + "miaosha/GetMiaoshaInfo?userId=%s&id=%s";

	// 16.商家列表
	public static final String GET_BUSINESS_LIST_URL = NEW_BASE_URL
			+ "shangjia/getlist?areaId=%s&sortId=%s&latitude=%s&longitude=%s&pageSize=%s&pageIndex=%s";

	// 17.商家详情
	public static final String GET_BUSINESS_DETAIL_URL = NEW_BASE_URL
			+ "shangjia/GetNewSjinfo?id=%s&latitude=%s&longitude=%s";

	// 18.用户登录
	public static final String GET_USER_LOGIN_URL = NEW_BASE_URL + "users/Logins";

	// 19.注册-获取验证码
	public static final String GET_VALIDATE_CODE_URL = NEW_BASE_URL + "yzm/GetYzm?phoneNum=%s&mobileImei=%s";

	// 20.注册-下一步
	public static final String GET_CHECK_CODE_URL = NEW_BASE_URL + "Users/Checkcode?mobile=%s&mobileCode=%s";

	// 21.注册-完成
	public static final String GET_REGISTER_URL = NEW_BASE_URL + "users/Regs";

	// 22.首页广告栏
	public static final String GET_BANNER_LIST_URL = NEW_BASE_URL
			+ "ad/GetAdList?top=10&province=%s&city=%s&country=%s&areaId=%s";

	// 23.修改密码
	public static final String GET_MODIFY_PW_URL = NEW_BASE_URL
			+ "Users/Repass?userName=%s&oldPassword=%s&newPassword=%s";

	// 24.获取验证码
	public static final String GET_VALIDATE_CODE = NEW_BASE_URL + "Users/GetRepassmsg?mobileNum=%s";

	// 25.重置密码
	public static final String GET_RESET_PW_URL = NEW_BASE_URL
			+ "Users/GetNewPassword?mobileNum=%s&verifiCode=%s&newPassword=%s";

	// 26.错误日志
	public static final String POST_CRASH_URL = NEW_BASE_URL + "errLog/PostLog";

	// 27.兑换收货地址
	public static final String POST_EXCHANGE_URL = NEW_BASE_URL + "Product/Duihuan";

	// 28.获取可提现金额
	public static final String GET_WITHDRAW_BALANCE_URL = NEW_BASE_URL + "tixian/canCash?userName=%s&userPwd=%s";

	// 29.提交提现金额
	public static final String POST_WITHDRAW_URL = NEW_BASE_URL + "tixian/GetTixian";

	// 30.首页-获取金额
	public static final String GET_BALANCE_URL = NEW_BASE_URL + "users/GetBalance?userName=%s&passWord=%s&userId=%s";

	// 31.首页-超值秒杀
	public static final String GET_SECKILL_URL = NEW_BASE_URL
			+ "miaosha/getsymiaosha?province=%s&city=%s&country=%s&areaId=%s";

	// 32.超值秒杀抢购
	public static final String POST_VALUE_SPIKE_EXCHANGE_URL = NEW_BASE_URL + "miaosha/BuyOrder";

	// 33.我的秒杀记录
	public static final String POST_SECKILL_RECODR_URL = NEW_BASE_URL
			+ "miaosha/GetMyMiaoshaOrder?userName=%s&userId=%s&pageIndex=%s&pageSize=%s";

	// 34.发布优惠
	public static final String POST_PUBILSH_PREFERENTIAL_URL = NEW_BASE_URL + "youhui/PostYouhui";

	// 35.删除优惠
	public static final String DELETE_MY_PREFERENTIAL_URL = NEW_BASE_URL
			+ "Youhui/DeleteMyYouhui?userName=%s&passWord=%s&id=%s";

	// 36.版本更新
	public static final String GET_NEW_VERSION_URL = NEW_BASE_URL + "upg/getVersion?clients=0&versionCode=%s";

	// 37.广告栏详情
	public static final String GET_BANNER_DETAIL_URL = NEW_BASE_URL + "ad/GetAdInfo?id=%s";

	// 38.每日签到
	public static final String GET_DAILY_ATTENDANCE_URL = NEW_BASE_URL + "qiandao/Qiandaoa";
	// 39.获取完善资料
	public static final String GET_USER_DATA_URL = NEW_BASE_URL + "users/getMyinfo?userId=%s&userPwd=%s";

	// 40.提交修改资料
	public static final String POST_USER_DATA_URL = NEW_BASE_URL + "users/postMyinfo";

	// 41.分享加分
	public static final String GET_SHARE_URL = NEW_BASE_URL + "fenxiang/getshare?userName=%s&userId=%s&userPwd=%s";

	// 42.获取分享内容
	public static final String GET_SHARE_CONTENT_URL = NEW_BASE_URL + "fenxiang/getshareString?userName=%s";

	// 43.看广告收益
	public static final String POST_SEE_AD_URL = NEW_BASE_URL + "advs/seeAdv";

	// 44.获取区域ID
	public static final String GET_AREAID_URL = NEW_BASE_URL + "users/GetAreaid?lng=%s&lat=%s";

	// 45.获取锁屏列表
	public static final String GET_SCREENLOCK_LIST_URL = NEW_BASE_URL + "advs/GetHpList?userName=%s";

	// 46.获取商家产品详情
	public static final String GET_BUSINESS_PRODUCT_DETAIL_URL = NEW_BASE_URL
			+ "shangjia/GetProInfo?id=%s&longitude=%s&latitude=%s";

	// 47.获取默认地址
	public static final String GET_DEFAULT_ADDRESS_URL = NEW_BASE_URL
			+ "address/GetDefaultAddress?userId=%s&userPwd=%s";

	// 48.添加收货地址
	public static final String POST_ADD_ADDRESS_URL = NEW_BASE_URL + "address/addMyAddress";

	// 49.获取收货地址列表
	public static final String GET_ADDRESS_LIST_URL = NEW_BASE_URL + "address/getAddressList?userId=%s&userPwd=%s";

	// 50.获取收货地址列表
	public static final String DELETE_ADDRESS_URL = NEW_BASE_URL + "address/delMyaddress?userId=%s&userPwd=%s&id=%s";

	// 51.根据id获取地址
	public static final String GET_ADDRESS_URL = NEW_BASE_URL + "address/GetAddress?userId=%s&userPwd=%s&id=%s";

	// 52.修改收货地址
	public static final String POST_UPDATE_ADDRESS_URL = NEW_BASE_URL + "address/updateMyAddress";

	// 53.提交订单
	public static final String POST_ORDER_URL = NEW_BASE_URL + "lyorder/AddOrders";

}
