/**
 * 
 */
package com.tc.generator.mapper;

import java.util.List;

import com.tc.generator.model.ColumnInfo;
import com.tc.generator.model.TableInfo;

/**
 * @description 生成代码Mapper类
 * @author chengw
 *
 * @Date 2019年7月10日
 * @Time 下午8:32:24
 */
public interface GeneratorMapper {

	/**
	 * @description 查询数据库表的相关信息
	 * @param 库表信息
	 * */
	public List<TableInfo> selectTableList(TableInfo tableInfo);
	
	
	/**
	 * @description 根据表名查询表信息
	 * */
	public TableInfo selectTableByName(String tableName);
	
	
	/**
	 * @description 根据表名查询列
	 * */
	public List<ColumnInfo> selectColumnInfoByTableName(String tableName);
	
}
