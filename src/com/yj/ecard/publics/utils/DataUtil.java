/**   
* @Title: DataUtil.java
* @Package com.yj.ecard.publics.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author YangMingGuang
* @date 2015-7-23 上午9:43:55
* @version V1.0   
*/

package com.yj.ecard.publics.utils;

/**
* @ClassName: DataUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author YangMingGuang
* @date 2015-7-23 上午9:43:55
* 
*/

public class DataUtil {

	/**
	 * 
	* @Title: getSexById 
	* @Description: 获取性别title
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getSexById(int id) {
		String title = "请选择性别";
		switch (id) {
		case 27:
			title = "男";
			break;

		case 28:
			title = "女";
			break;
		}
		return title;
	}

	/**
	 * 
	* @Title: getAgeById 
	* @Description: 获取年龄title
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getAgeById(int id) {
		String title = "请选择年龄";
		switch (id) {
		case 109:
			title = "18岁以下";
			break;

		case 110:
			title = "18-25岁";
			break;

		case 111:
			title = "26-30岁";
			break;

		case 112:
			title = "31-35岁";
			break;

		case 113:
			title = "36-40岁";
			break;

		case 133:
			title = "41-45岁";
			break;

		case 134:
			title = "46-50岁";
			break;

		case 135:
			title = "50岁以上";
			break;
		}
		return title;
	}

	/**
	 * 
	* @Title: getIncomeById 
	* @Description: 获取收入title
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getIncomeById(int id) {
		String title = "请选择收入";
		switch (id) {
		case 124:
			title = "3000元以内";
			break;

		case 125:
			title = "3001-5000元";
			break;

		case 126:
			title = "5001-10000元";
			break;

		case 127:
			title = "10001-20000元";
			break;

		case 128:
			title = "20001-50000元";
			break;

		case 137:
			title = "5万-10万元以上";
			break;
		}
		return title;
	}

	/**
	 * 
	* @Title: getMarriageById 
	* @Description: 获取婚姻title
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getMarriageById(int id) {
		String title = "请选择婚姻";
		switch (id) {
		case 161:
			title = "未婚";
			break;

		case 162:
			title = "已婚";
			break;

		case 163:
			title = "离异";
			break;
		}
		return title;
	}

	/**
	 * 
	* @Title: getProfessionById 
	* @Description: 获取专业title
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getProfessionById(int id) {
		String title = "请选择职业";
		switch (id) {
		case 136:
			title = "其他";
			break;

		case 71:
			title = "大企业主";
			break;

		case 70:
			title = "公务员";
			break;

		case 69:
			title = "普通职员";
			break;

		case 68:
			title = "学生";
			break;

		case 67:
			title = "中小企业主";
			break;

		case 66:
			title = "企业高管";
			break;

		case 30:
			title = "白领精英";
			break;
		}
		return title;
	}

	/**
	 * 
	* @Title: getFieldById 
	* @Description: 获取领域title 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getFieldById(int id) {
		String title = "请选择关心领域";
		switch (id) {
		case 159:
			title = "新闻信息";
			break;

		case 158:
			title = "读书/音乐型";
			break;

		case 157:
			title = "教育/幼儿";
			break;

		case 156:
			title = "服饰鞋包";
			break;

		case 155:
			title = "电器数码";
			break;

		case 154:
			title = "婚纱摄影";
			break;

		case 153:
			title = "化妆品";
			break;

		case 152:
			title = "美容美体";
			break;

		case 151:
			title = "旅游";
			break;

		case 150:
			title = "酒店";
			break;

		case 149:
			title = "休闲娱乐";
			break;

		case 148:
			title = "美食";
			break;

		case 147:
			title = "房产";
			break;

		case 146:
			title = "IT/电子";
			break;

		case 145:
			title = "运动/健身";
			break;

		case 144:
			title = "时尚/购物";
			break;

		case 143:
			title = "理财/投资";
			break;

		case 142:
			title = "汽车";
			break;

		case 141:
			title = "考试就业";
			break;

		case 140:
			title = "电影/戏剧";
			break;

		case 139:
			title = "演艺/娱乐";
			break;
		}
		return title;
	}

}
