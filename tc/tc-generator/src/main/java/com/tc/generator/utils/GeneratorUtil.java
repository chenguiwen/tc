/**
 * 
 */
package com.tc.generator.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import com.tc.common.utils.DateUtils;
import com.tc.common.utils.StringUtils;
import com.tc.generator.config.GeneratorConfig;
import com.tc.generator.model.ColumnInfo;
import com.tc.generator.model.TableInfo;

/**
 * @description 代码生成工具类
 * 
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 上午10:58:01
 */
public class GeneratorUtil {
	
	/**
	 * 包路径
	 * */
	private static final String PROJECT_PATH = getProjectPath();
	
    /** 
     * mybatis空间路径 
     * */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /** 
     * html空间路径 
     * */
    private static final String TEMPLATES_PATH = "main/resources/templates";

    /**
	 * 类型转换工具
	 * */
	private static Map<String,String> typeConvertMap = new HashMap<String,String>();
	
	/**
	 * 将表名换成Java类名
	 * */
	public static String tableNameToJavaClassName(String tableName) {
		
		//判断是否自动去除表前缀
		boolean removePre = GeneratorConfig.getRemovePre();
		//获取表前缀
		String tableprefix = GeneratorConfig.getTablePrefix();
		
		if(removePre && tableprefix != null && tableprefix != "") {
			tableName = tableName.replaceFirst(tableprefix, "");
		}
		
		return StringUtils.convertToHumpName(tableName);
	}
	
	/**
	 * 将数据库表中的字段转换成tableInfo类中的属性
	 * @param List<ColumnInfo> 字段集合
	 * @return List<ColumnInfo> 处理之后的字段集合
	 * */
	public static List<ColumnInfo> transColumns(List<ColumnInfo> columns){
		
		List<ColumnInfo> transColumns = new ArrayList<ColumnInfo>();
		//如果没有字段值，没必要转换
		if(null == columns || 0 == columns.size()) {
			return null;
		}
		
		//列名转换为Java类中的属性
		for (ColumnInfo column : columns) {
			
			//获取列名 并作驼峰命名处理
			String attributeName = StringUtils.convertToHumpName(column.getColmunName());
			column.setAttributeName(attributeName);
			
			//获取执行计划 （与索引相关的一些细节信息）
			String extra = column.getExtra();
			column.setExtra(extra);
			
			//获取数据库表中的字段类型并转换成Java类中属性的数据类型
			String attributeType = typeConvertMap.get(column.getDataType());
			column.setAttributeType(attributeType);
			
			transColumns.add(column);
		}
		
		return transColumns;
	}
	
	/**
	 * @description 获取模块名，tc系统中，包名的最后一个即为模块名
	 * */
	public static String getModuleName(String packageName) {
		int lastIndex = packageName.lastIndexOf(".");		
		return packageName.substring(lastIndex);		
	}
	/**
	 * @description 获取基本包路径
	 * */
    public static String getBasePackage(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        String basePackage = packageName.substring(0, lastIndex);
        return basePackage;
    }
	
	
	/**
	 * @description 获取模板信息，将生成模板的维度放入上下文
	 * */
	public static VelocityContext getVelocityContext(TableInfo tableInfo) {
		VelocityContext context = new VelocityContext();
		
		String packageName = GeneratorConfig.getPackagePath();
		context.put("tableName", tableInfo.getTableName());
		context.put("tableComment", GeneratorUtil.replaceKeyword(tableInfo.getTableComment()));
		context.put("primaryKey", tableInfo.getPrimaryKey());
		context.put("className", tableInfo.getClassName());
		context.put("moduleName", GeneratorUtil.getModuleName(packageName));
		context.put("columns", tableInfo.getColumns());
		context.put("basePackage", GeneratorUtil.getBasePackage(packageName));
		context.put("package", packageName);
		context.put("author", GeneratorConfig.getAuthor());
		context.put("datetime", DateUtils.getDateNow());
		return context;
	}
	
	/**
	 * @description 获取模板信息，读取配置文件中的模板文件
	 * */
	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
		
        templates.add("vm/java/Model.java.vm");
        templates.add("vm/java/Mapper.java.vm");
        templates.add("vm/java/Service.java.vm");
        templates.add("vm/java/ServiceImpl.java.vm");
        templates.add("vm/java/Controller.java.vm");
        templates.add("vm/xml/Mapper.xml.vm");
        templates.add("vm/html/list.html.vm");
        templates.add("vm/html/add.html.vm");
        templates.add("vm/html/edit.html.vm");
        templates.add("vm/html/view.html.vm");
        templates.add("vm/sql/sql.vm");

		return templates;
	}
	/**
	 * @return String 路径
	 */
	private static String getProjectPath() {
		
		//通过配置类获取包路径
		String packageName = GeneratorConfig.getPackagePath();
		
		//将包路径中的“.”换成“/”并拼装路径
		StringBuffer projectPath = new StringBuffer();
		projectPath.append("main/java/");
		projectPath.append(packageName.replace(".", "/"));
		projectPath.append("/");

		return projectPath.toString();
	}

	/**
	 * @description 关键字置换
	 * */
    public static String replaceKeyword(String keyword){
        String keyName = keyword.replaceAll("(?:表|信息|管理)", "");
        return keyName;
    }	
	
	static {
		typeConvertMap.put("int", "Integer");
		typeConvertMap.put("number", "Integer");
		typeConvertMap.put("integer", "integer");
		typeConvertMap.put("tinyint", "Integer");
		typeConvertMap.put("smallint", "Integer");
		typeConvertMap.put("mediumint", "Integer");
		
		typeConvertMap.put("varchar", "String");
		typeConvertMap.put("varchar2", "String");
		typeConvertMap.put("char", "String");
		typeConvertMap.put("text", "String");
		typeConvertMap.put("longtext", "String");
		typeConvertMap.put("tinytext", "String");
		typeConvertMap.put("mediumtext", "String");

		typeConvertMap.put("double", "Double");
		typeConvertMap.put("decimal", "BigDecimal");

		typeConvertMap.put("time", "Date");
		typeConvertMap.put("date", "Date");
		typeConvertMap.put("datetime", "Date");
		typeConvertMap.put("timestamp", "Date");
		
		typeConvertMap.put("bigint", "Long");
		typeConvertMap.put("float", "Float");
		typeConvertMap.put("bit", "Boolean");
	}

	/**
	 * @description 获取代码模板的文件名
	 * @param templateModel 代码模板
	 * @param tableInfo 类表信息
	 * @param moduleName 模块名
	 * @return 代码模板文件名
	 */
	public static String getFileName(String templateModel, TableInfo tableInfo, String moduleName) {
		//类名
		String className = tableInfo.getClassName();
		String javapath = PROJECT_PATH;
		String mybastispath = MYBATIS_PATH + "/" + moduleName + "/" + className;
		String htmlpath = TEMPLATES_PATH + "/" + moduleName + "/" + className;

		if(templateModel.contains("add.html.vm")) {
			return htmlpath + "/" + "add.html";
		}
		if(templateModel.contains("edit.html.vm")) {
			return htmlpath + "/" + "edit.html";			
		}
		if(templateModel.contains("list.html.vm")) {
			return htmlpath + "/" + "list.html";			
		}
		if(templateModel.contains("view.html.vm")) {
			return htmlpath + "/" + "view.html";			
		}
		if(templateModel.contains("Controller.java.vm")) {
			return javapath + "controller" + "/" + className + "Controller.java";
		}
		if(templateModel.contains("Mapper.java.vm")) {
			return javapath + "mapper" + "/" + className + "Mapper.java";			
		}
		if(templateModel.contains("Model.java.vm")) {
			return javapath + "model" + "/" + className + "Mapper.java";						
		}
		if(templateModel.contains("Service.java.vm")) {
			return javapath + "service" + "/" + "I" + className + "Service.java";			
		}
		if(templateModel.contains("ServiceImpl.java.vm")) {
			return javapath + "service" + "/" + "impl" + "/" + className + "ServiceImpl.java";						
		}
		if(templateModel.contains("Mapper.xml.vm")) {
			return mybastispath + "Mapper.xml";
		}
		if(templateModel.contains("sql.vm")) {
			return className + "Menu.sql";
		}
		
		return null;
	}
	
}
