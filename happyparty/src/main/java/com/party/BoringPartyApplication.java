package com.party;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages="com.party.*.mapper")
@EnableTransactionManagement(proxyTargetClass=true)
//@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
public class BoringPartyApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoringPartyApplication.class, args);
	}
}
