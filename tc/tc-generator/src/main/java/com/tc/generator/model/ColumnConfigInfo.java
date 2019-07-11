/**
 * 
 */
package com.tc.generator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tc.common.model.BaseEntity;

/**
   *  字段类型配置(参照)  由数据库字段的注释解析而来
   *  注释结构示例:
 *  {
 *  	"title": "状态",
 *  	"type": "dict", 
 *  	"value": "sys_common_status"
 *  },
 *  {
 *  	"title": "登录时间",
 *  	"type": "date"
 *  }
 * 
 * @author chengw
 *
 * @Date 2019年7月10日
 * @Time 下午6:47:00
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColumnConfigInfo extends BaseEntity {

	private static final long serialVersionUID = -2805159124787948227L;

	/**
	 * 属性标题
	 * */
	private String title;
	
	/**
	 * 属性类型名
	 * */
	private String type;
	
	/**
	 * 属性值
	 * */
	private String value;
	
	
	public ColumnConfigInfo() {
		super();
	}
	
	public ColumnConfigInfo(String title) {
		super();
		this.title = title;
	}
	
	public ColumnConfigInfo(String title,String type) {
		super();
		this.title = title;
		this.type = type;
	}
	
	public ColumnConfigInfo(String title,String type,String value) {
		super();
		this.title = title;
		this.type = type;
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
