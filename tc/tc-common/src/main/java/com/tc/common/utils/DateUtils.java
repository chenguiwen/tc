/**
 * 
 */
package com.tc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 下午3:10:39
 */
public class DateUtils {

	/**
	 * @description 定义几个常见日期格式
	 * */
	public static final String YYYY = "yyyy";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static String getDateNow() {
		return new SimpleDateFormat(YYYY_MM_DD).format(new Date());
	}
	
}
