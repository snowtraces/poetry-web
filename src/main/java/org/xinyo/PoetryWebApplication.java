package org.xinyo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("org.xinyo.dao")
public class PoetryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoetryWebApplication.class, args);
	}
}
