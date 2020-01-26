package com.hdmstuttgart.fluffybear.configuration;

import com.hdmstuttgart.fluffybear.repository.RecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.hdmstuttgart.fluffybear.repository", "com.hdmstuttgart.fluffybear.storage"}) // load instances of classes in repository package
public class RepositoryTestConfiguration {
}
