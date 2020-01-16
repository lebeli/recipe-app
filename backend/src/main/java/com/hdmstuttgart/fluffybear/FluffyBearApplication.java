package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.Storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Starting point of the spring boot java backend application for the recipes app develop for mobile
 * application development.
 */
@SpringBootApplication
public class FluffyBearApplication {

	/**
	 * Starting point of the spring boot application.
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FluffyBearApplication.class, args);
	}

	/**
	 * Method is invoked after startup of the application.
	 * The storage service, which is used for persisting files/images, is initialized.
	 * For developing purposes the delete all method is invoked.
	 * @param storageService
	 * @return
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
