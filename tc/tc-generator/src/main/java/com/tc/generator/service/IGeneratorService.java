/**
 * 
 */
package com.tc.generator.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tc.generator.model.TableInfo;

/**
 * @description 代码生成服务接口
 * @author chengw
 *
 * @Date 2019年7月10日
 * @Time 下午8:27:39
 */
@Service
public interface IGeneratorService {

	/**
	 * @description 查询数据库表的相关信息
	 * @param 库表信息
	 * */
	public List<TableInfo> selectTableList(TableInfo tableInfo);
	
	
	/**
	 * @description 生成代码
	 * */
	public byte[] generatorCode(String tableName);
}
