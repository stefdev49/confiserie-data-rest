package fr.stef.jug.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration des repository.
 *
 * @author stef
 *
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "fr.stef.jug.domain" })
@EnableTransactionManagement
public class RepositoryConfiguration {

}
