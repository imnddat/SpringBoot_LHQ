package com.datnd.onetomany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);

		PersonRepository personRepository = context.getBean(PersonRepository.class);

		AddressRepository addressRepository = context.getBean(AddressRepository.class);

		// repository.findByFirstNameContainingAndLastNameContaining("1","2").forEach(t
		// -> System.out.println(t));
		// repository.findByFirstNameContainingOrLastNameContaining("%1%").forEach(System.out::println);

		// for (int i = 1; i <=100; i++) {
		// Person person = Person.builder().firstName("firstName" +
		// i).lastName("lastName" + i).emailAdress("emailAddress" +
		// i).age(i).address_id(i).build();
		// repository.save(person);
		// }

		List<Person> listPersons = new ArrayList<>();

		Person p1 = Person.builder().firstName("Dat").lastName("Nguyen").emailAdress("datnd@gmail.com").age(20).build();

		Person p2 = Person.builder().firstName("Tuan").lastName("Hoang").emailAdress("hoangtuan@gmail.com").age(20).build();

		listPersons.add(p1);
		listPersons.add(p2);

		Address address = Address.builder().address("Ha Noi").persons(listPersons).build();
		p1.setAddress(address);
		p2.setAddress(address);
		addressRepository.save(address);
		// personRepository.save(p1);
		// personRepository.save(p2);

		// List<Person> listPer = personRepository.findAll();

		// for (Person person : listPer) {
		// 	System.out.println(person.getAddress());
		// }

		
	}

}
