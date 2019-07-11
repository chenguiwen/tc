package com.tc.generator.model;

import com.mysql.cj.util.StringUtils;
import com.tc.common.json.JSON;
import com.tc.common.model.BaseEntity;

public class ColumnInfo extends BaseEntity {

	private static final long serialVersionUID = -3149924250623797549L;

	/**
	 * 列名称
	 * */
	private String colmunName;
	
	/**
	 * 列中的数据类型
	 * */
	private String dataType;
	
	/**
	 * 列注释
	 * */
	private String columnComment;
	
    /** 
            * 列配置 
    * */
    private ColumnConfigInfo configInfo;
    
    /**
	 * Java类中对应的数据类型
	 * */
	private String attributeType;
	
	/**
	 * Java类中对应的字段名称
	 * */
	private String attributeName;
	
	/**
	 * 执行计划（包含了与索引相关的一些细节信息）
	 * */
    private String extra;

    
	public String getColmunName() {
		return colmunName;
	}

	public void setColmunName(String colmunName) {
		this.colmunName = colmunName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) throws Exception {
		//解析列注释获得数据库表字段的配置信息
		if(!StringUtils.isNullOrEmpty(columnComment) && columnComment.startsWith("{")) {
			this.configInfo = JSON.unmarshal(columnComment, ColumnConfigInfo.class);
		}else {
			this.columnComment = columnComment;
		}
	}

	public ColumnConfigInfo getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ColumnConfigInfo configInfo) {
		this.configInfo = configInfo;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
