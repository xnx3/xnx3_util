package com.xnx3;

/**
 * Double工具类
 * @author 管雷鸣
 *
 */
public class DoubleUtil {
	
	/**
	 * 将String格式转化为double格式
	 * @param value 要转化的string格式字符串
	 * @param defaultValue 如果转换失败，则默认返回的double值
	 * @return double值
	 */
	public static double stringToDouble(String value, double defaultValue){
		if(value == null || value.equals("null")){
			return defaultValue;
		}
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
}
