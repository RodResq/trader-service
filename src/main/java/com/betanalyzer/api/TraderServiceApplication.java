package com.betanalyzer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.betanalyzer.api.common"})
public class TraderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderServiceApplication.class, args);
	}

}
