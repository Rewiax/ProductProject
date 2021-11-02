package ru.test.product;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductProjectApplication {
	
	private final static Logger logger = Logger.getLogger(ProductProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductProjectApplication.class, args);
		
		logger.debug("start");
	}

}
