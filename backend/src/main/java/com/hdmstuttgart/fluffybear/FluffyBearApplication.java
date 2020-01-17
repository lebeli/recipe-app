package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.Storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Starting point of the spring boot java backend application for the recipes app develop for mobile
 * application development. Initiates the application context (retrival of models, services and repositories).
 */
@SpringBootApplication
public class FluffyBearApplication {

	/**
	 * Entrypoint for Fluffy-Bear-Application.
	 *
	 * @param args  spring beans for IoC container.
	 */
	public static void main(String[] args) {
		SpringApplication.run(FluffyBearApplication.class, args);
	}

	/**
	 * Method is invoked after startup of the application.
	 * The storage service, which is used for persisting files/images, is initialized.
	 * For developing purposes the delete all method is invoked.
	 *
	 * @param  storageService  utility instance for storing Images locally on application server's filesystem.
	 * @return  spring bean for repository initialization at application startup.
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
