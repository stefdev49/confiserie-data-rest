package fr.stef.jug.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Classe server
 *
 * @author stef
 * On n'utilise pas @SpringBootApplication car il ne permet pas d'inquer le basename des package pour le scan.
 * Ce qui empêche de trouver les @Controller advice
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="fr.stef.jug")
@EnableWebMvc
public class Application {

  /**
   * Configure CORS http://stackoverflow.com/a/31748398/122441 until
   * https://jira.spring.io/browse/DATAREST-573
   *
   * @return
   */
  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    // on fait le bourrin, tout est autorisé
    // mais par exemple on devrait filtrer les origines
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**", config);
    final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }

  /**
   * Méthode principale exécutant le serveur
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
