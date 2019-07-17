/**
 * 
 */
package com.tc.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.generator.config.GeneratorConfig;
import com.tc.generator.mapper.GeneratorMapper;
import com.tc.generator.model.ColumnInfo;
import com.tc.generator.model.TableInfo;
import com.tc.generator.service.IGeneratorService;
import com.tc.generator.utils.GeneratorUtil;

/**
 * @author chengw
 *
 * @Date 2019年7月10日
 * @Time 下午8:31:42
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService {

	@Autowired
	private GeneratorMapper generatorMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);

    
	/**
	 * @description 查询数据库表信息
	 * */
	@Override
	public List<TableInfo> selectTableList(TableInfo tableInfo) {
		return generatorMapper.selectTableList(tableInfo);
	}

	
	/**
	 * @description 生成代码
	 * */
	@Override
	public byte[] generatorCode(String tableName) {
		/** 将数据写入字节数组的输出流 */
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		/** 实现输出流过滤器，用于以ZIP文件格式写入文件 */
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		/** 通过输出流过滤器，根据表名产生代码*/
		generatorCode(tableName,zip);
		return null;
	}


	/**
	 * @param tableName 表名
	 * @param zip 输出流过滤器
	 */
	private void generatorCode(String tableName, ZipOutputStream zip) {
		
		//查询数据库表信息
		TableInfo tableInfo = this.generatorMapper.selectTableByName(tableName);
		//查询数据库表的列信息
		List<ColumnInfo> columnInfos = this.generatorMapper.selectTableColumnsByTableName(tableName);

		/** 
		 * 表信息转换为Java属性
		 * */
		String className = GeneratorUtil.tableNameToJavaClassName(tableName);
		tableInfo.setClassName(className);
		
		/**
		 * 将数据库表中的列转换为tableInfo类中的属性
		 * */
		tableInfo.setColumns(GeneratorUtil.transColumns(columnInfos));
		
		//设置主键信息
		tableInfo.setPrimaryKey(tableInfo.getLastColumn());
		
//        VelocityInitializer.initVelocity();

		String packageName = GeneratorConfig.getPackagePath();//包名
		String moduleName = GeneratorUtil.getModuleName(packageName);//模块名
		
		VelocityContext context = GeneratorUtil.getVelocityContext(tableInfo);
		
		//获取模板列表
		List<String> templateModels = GeneratorUtil.getTemplates();
		for(String templateModel : templateModels) {
			//渲染模板
			StringWriter writer = new StringWriter();
			Template template = Velocity.getTemplate(templateModel, "UTF-8");
			template.merge(context, writer);
			
			//添加到zip过滤器
			try {
				zip.putNextEntry(new ZipEntry(GeneratorUtil.getFileName(templateModel,tableInfo,moduleName)));
				IOUtils.write(writer.toString(),zip,"UTF-8");
				IOUtils.closeQuietly(zip);
				zip.closeEntry();
			} catch (IOException e) {
				logger.error("渲染模板失败，失败表名：" + tableInfo.getTableName(),e);
			}
		}
	}

}
