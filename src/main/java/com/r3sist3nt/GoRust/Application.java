package com.r3sist3nt.GoRust;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		 ApplicationContext context = SpringApplication.run(Application.class, args);
		 //context.getBean(Init.class).test();
	}
	
}
