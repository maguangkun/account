package com.cun;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement(proxyTargetClass=true)
//@EnableTransactionManagement(proxyTargetClass=True)）
@SpringBootApplication
public class SpringBootUrpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUrpApplication.class, args);
	}
}
