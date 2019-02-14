package com.example.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.tkmybatis.mapper")
public class TkmybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TkmybatisApplication.class, args);
	}

}

