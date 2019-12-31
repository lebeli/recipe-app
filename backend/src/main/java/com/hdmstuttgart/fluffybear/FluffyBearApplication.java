package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.Storage.StorageProperties;
import com.hdmstuttgart.fluffybear.Storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FluffyBearApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluffyBearApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
