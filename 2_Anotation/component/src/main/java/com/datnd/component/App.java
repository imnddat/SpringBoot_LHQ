package com.datnd.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import com.datnd.component.core.Girl;
import com.datnd.component.external.Boy;

@SpringBootApplication (scanBasePackages = {"com.datnd.component.*"})
//@ComponentScan("com.datnd.component.*")
public class App {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);

		// IOutfit outfit = context.getBean(IOutfit.class);

		// System.out.println("Outfit: " + outfit);

		Girl girl = context.getBean(Girl.class);

		System.out.println("Girl instance: " + girl);

		System.out.println("Outfit of girl: " + girl.outfit);

		girl.outfit.wear();

		((ConfigurableApplicationContext) context).getBeanFactory().destroyBean(girl);

		Boy boy = context.getBean(Boy.class);

		System.out.println("Boy instance: " + boy);
	}

}
