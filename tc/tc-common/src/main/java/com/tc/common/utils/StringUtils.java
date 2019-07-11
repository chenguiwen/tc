/**
 * 
 */
package com.tc.common.utils;

/**
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 下午1:23:10
 */
public class StringUtils {

	/**
	 * 将下划线命名方式的字符串变更为驼峰命名方式，例如：hello_world => HelloWorld  HELLO_WORLD => HelloWorld
	 * @param name 需要转换的字符串
	 * @return 转换后的驼峰命名字符串
	 * */
	public static String convertToHumpName(String name) {
		StringBuffer resultName = new StringBuffer();
		//name为空，没必要转换
		if(null == name || "" == name) {
			return name;
		}
		//name不包含下划线，仅将首字母大写即可
		if(!name.contains("_")) {
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		//如果存在下划线
		String[] strs = name.split("_");
		for (int i = 0; i < strs.length; i++) {
			if(strs[i].isEmpty()) {
				continue;
			}
			//将第一个字母大写
			resultName.append(strs[i].substring(0,1).toUpperCase());
			//将其余字母小写
			resultName.append(strs[i].substring(1).toLowerCase());
		}
		return resultName.toString();
	}
}
