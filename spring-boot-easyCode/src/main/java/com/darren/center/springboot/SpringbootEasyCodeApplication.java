package com.darren.center.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.darren.center.springboot.dao")
public class SpringbootEasyCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEasyCodeApplication.class, args);
	}

}

