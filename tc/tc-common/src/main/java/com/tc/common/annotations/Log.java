/**
 * 
 */
package com.tc.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tc.common.constant.BussinessType;

/**
 * @author chengw
 *
 * @Date 2019年7月13日
 * @Time 下午3:32:03
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

	/**
	 * @description 操作名
	 * */
	public String title() default "";
	
	/**
	 * @description 操作功能：增删改查，导入导出等
	 * */
	public BussinessType bussinessType() default BussinessType.OTHERS;
	
}
