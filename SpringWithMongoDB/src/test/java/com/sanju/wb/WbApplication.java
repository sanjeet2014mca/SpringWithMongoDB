package com.sanju.wb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
@Configuration
@EnableAutoConfiguration
**@ComponentScan({"com.example.demo", "controller", "service"})**

*/
@SpringBootApplication
public class WbApplication {

	public static void main(String[] args) {
		SpringApplication.run(WbApplication.class, args);
	}
}
