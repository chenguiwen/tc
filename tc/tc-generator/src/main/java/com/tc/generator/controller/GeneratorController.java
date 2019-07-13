/**
 * 
 */
package com.tc.generator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tc.common.annotations.Log;
import com.tc.common.constant.BussinessType;
import com.tc.common.controller.BaseController;
import com.tc.common.model.tips.SuccessTip;
import com.tc.generator.service.IGeneratorService;

/**
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 下午8:32:46
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController extends BaseController{
	
	protected static final String SUCCESS = "success";
	protected static final String FAILED = "failed";

	protected static final String REDIRECT = "redirect:";
	protected static final String FORWARD = "forward:";
	
	protected static final SuccessTip SUCCESSTIP = new SuccessTip();

	private static final String prefix = "/generator";
	
	@Autowired
	private IGeneratorService generatorService;
	
	/**
	 * @description 跳转到生成代码页面
	 * */
	@PostMapping("/view")
	@ResponseBody
	public String generatorView() {
		return prefix + "/view";
	}
	
	/**
	 * @throws IOException 
	 * @description 生成代码
	 * */
	@PostMapping("/generatorcode")
	@ResponseBody
	@Log(title="生成代码",bussinessType = BussinessType.GENERATE)
	public void generateCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
		byte[] data = generatorService.generatorCode(tableName);
		this.generator(response, data);
	}
	
	public void generator(HttpServletResponse response,byte[] data) throws IOException {
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"tcgenerator.zip\"");
		response.addHeader("Content-Length", data.length+"");
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}
}
