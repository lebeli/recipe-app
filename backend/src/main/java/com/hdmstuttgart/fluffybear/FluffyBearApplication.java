package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.Storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Class for initiating the application context (retrival of models, services and repositories).
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
	 * Inititiates repositories at a cleant state.
	 *
	 * @param storageService  FileSystemStorageService instance.
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
