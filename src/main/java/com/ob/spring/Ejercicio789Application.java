package com.ob.spring;

import com.ob.spring.entities.Laptop;
import com.ob.spring.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicio789Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Ejercicio789Application.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "Dell", "Latitude", "8GB");
		Laptop laptop2 = new Laptop(null, "HP", "Pavilion", "4GB");
		Laptop laptop3 = new Laptop(null, "Lenovo", "Thinkpad", "16GB");
		Laptop laptop4 = new Laptop(null, "Acer", "Aspire", "32GB");

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);
		repository.save(laptop4);

		System.out.println("Numero laptops en base de datos: " + repository.findAll().size());

	}

}
