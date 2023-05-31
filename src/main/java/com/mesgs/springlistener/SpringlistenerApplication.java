package com.mesgs.springlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringlistenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringlistenerApplication.class, args);
	}

}
