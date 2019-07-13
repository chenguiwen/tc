/**
 * 
 */
package com.tc.generator;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tc.generator.service.IGeneratorService;

/**
 * @description generator单元测试
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 下午5:21:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GeneratorApplicationTest {

	@SuppressWarnings("unused")
	@Autowired
	private IGeneratorService generatorService;
	
}
