/**
 * 
 */
package com.tc.generator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description 生成代码的相关配置，如作者，包路径等信息
 * 
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 上午11:01:11
 */
@Component
@ConfigurationProperties(prefix = "generator") //配置成读取配置文件的样式，所有值从配置文件读取
@PropertySource(value = {"classpath:generator.yml"})//指定读取的配置文件
public class GeneratorConfig {

	/**
	 * 作者，从yml配置文件读取
	 * */
	public static String author;
	
	/**
	 * 包路径，从yml配置文件读取
	 * */
	public static String packagePath;
	
	/**
	 * 是否去除前缀（默认true），从yml配置文件读取
	 * */
	public static boolean removePre;
	
	/**
	 * 表前缀，从yml配置文件读取
	 * */
	public static String tablePrefix;
	
	
	public static String getAuthor() {
		return author;
	}
	
	@Value("${author}")
	public void setAuthor(String author) {
		GeneratorConfig.author = author;
	}

	public static String getPackagePath() {
		return packagePath;
	}

	@Value("${packagePath}")
	public static void setPackagePath(String packagePath) {
		GeneratorConfig.packagePath = packagePath;
	}

	public static boolean getRemovePre() {
		return removePre;
	}

	@Value("${removePre}")
	public static void setRemovePre(boolean removePre) {
		GeneratorConfig.removePre = removePre;
	}

	public static String getTablePrefix() {
		return tablePrefix;
	}

	@Value("${tablePrefix}")
	public static void setTablePrefix(String tablePrefix) {
		GeneratorConfig.tablePrefix = tablePrefix;
	}
}
