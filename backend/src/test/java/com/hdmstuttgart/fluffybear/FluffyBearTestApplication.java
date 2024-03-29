package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.configuration.RepositoryTestConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Class for integration testing with limited configuration scope.
 */
@SpringBootApplication
@Import(RepositoryTestConfiguration.class)
public class FluffyBearTestApplication {
}
