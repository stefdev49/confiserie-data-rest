package fr.cnp.jug.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
@ComponentScan("fr.cnp.jug")
@EnableWebMvc
public class Application {

	  @Autowired
	  @Qualifier("_halObjectMapper")
	  private ObjectMapper springHateoasObjectMapper;

	  @Bean(name = "objectMapper")
	  ObjectMapper objectMapper() {
	    springHateoasObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    return springHateoasObjectMapper;
	  }
	  
    /**
     * http://stackoverflow.com/a/31748398/122441 until https://jira.spring.io/browse/DATAREST-573
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // on fait le bourrin, tout est autorisé
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
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
