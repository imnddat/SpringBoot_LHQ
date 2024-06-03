package com.datnd.one_to_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);

		PersonRepository repository = context.getBean(PersonRepository.class);

		//repository.findByFirstNameContainingAndLastNameContaining("1","2").forEach(t -> System.out.println(t));
		repository.findByFirstNameContainingOrLastNameContaining("%1%").forEach(System.out::println);


		// for (int i = 1; i <=100; i++) {
		// 	Person person = Person.builder().firstName("firstName" + i).lastName("lastName" + i).emailAdress("emailAddress" + i).age(i).address_id(i).build();
		// 	repository.save(person);
		// }
	}

}
