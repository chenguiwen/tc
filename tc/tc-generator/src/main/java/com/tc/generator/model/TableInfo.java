/**
 * 
 */
package com.tc.generator.model;

import java.util.List;

import com.tc.common.model.BaseEntity;

/**
 * @author chengw
 *
 * @Date 2019年7月10日
 * @Time 下午5:12:58
 */
public class TableInfo extends BaseEntity {

	private static final long serialVersionUID = -4459045347036695919L;
	
	/**
	 * 表名称
	 * */
	private String TableName;
	
	/**
	 * 表备注
	 * */
	private String TableComment;
	
	/**
	 * 表中主键列
	 * */
	private ColumnInfo primaryKey;

	/**
	 * 表中其他列
	 * */
	private List<ColumnInfo> columns;
	
	/**
	 * 类名
	 * */
	private String className;

	
	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getTableNote() {
		return TableComment;
	}

	public void setTableNote(String tableComment) {
		TableComment = tableComment;
	}

	public ColumnInfo getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(ColumnInfo primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}

	public ColumnInfo getLastColumn() {
		ColumnInfo column = null;
		if(null != columns && 0 != columns.size()) {
			column = columns.get(0);
		}
		return column;
	}
	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
