package com.epoint.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * 该注解用于扫描com.epoint.bbs.model.mapper包下所有的接口文件
 */
@MapperScan(value = "com.epoint.bbs.model.mapper")
public class BbsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BbsApplication.class, args);
	}

}
