/**
 * 
 */
package com.tc.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author chengw
 *
 * @Date 2019年7月13日
 * @Time 下午2:56:17
 */
public class HttpUtils {

	public static RequestAttributes request() {
		RequestAttributes request = RequestContextHolder.getRequestAttributes();
		return request;
	}
	
	/**
	 * @description 获取HttpServletRequest,该request是为了防止sql注入
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) request()).getRequest();
		return request;
	}
	
	/**
	 * @description 获取HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) request()).getResponse();
		return response;
	}
	
	/**
	 * @description 获取session
	 * */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	
	/**
	 * @description 获取远程IP
	 * */
	public static String getIP() {
		return HttpUtils.getRequest().getRemoteHost();
	}
}
