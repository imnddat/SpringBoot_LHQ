package com.datnd.todo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
		TokenUtil tokenUtil = context.getBean(TokenUtil.class);
		System.out.println("[Token] : " + tokenUtil.generateToken());

		User user = User.builder().id(1).name("datnd").age(20).build();
		System.out.println(user.toString());
	}

}
