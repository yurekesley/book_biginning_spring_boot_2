package br.com.yurekesley.workingwithmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("br.com.yurekesley.workingwithmybatis.mappers")
public class WorkingWithMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkingWithMybatisApplication.class, args);
	}

}
