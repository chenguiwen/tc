/**
 * 
 */
package com.tc.common.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @description JSON解析器
 * @author chengw
 *
 * @Date 2019年7月10日
 * @Time 下午6:55:43
 */
public class JSON {

	/*默认失败*/
	public static final String DEFAULT_FAIL = "Prase failed!";
	/*Mapper*/
	private static final ObjectMapper objectmapper = new ObjectMapper();
	/*Mapper Writer*/
//	private static final ObjectWriter objectwriter = objectmapper.writerWithDefaultPrettyPrinter();
	
	public static <T> T unmarshal(String str,Class<T> valueType) throws Exception {
		
		try {
			return objectmapper.readValue(str, valueType);
		} 
		catch (IOException e) {
			throw new Exception(e);
		}
	}
	
}

