/**
 * 
 */
package com.tc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chengw
 *
 * @Date 2019年7月11日
 * @Time 下午8:38:39
 */
@SpringBootApplication
@MapperScan({"com.tc.*.mapper"})
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TCApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TCApplication.class, args);
	}

}
