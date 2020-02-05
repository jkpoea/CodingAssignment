package se.ecutb.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.PersonRepositoryImpl;

@Configuration
@ComponentScan("se.ecutb")
public class AppConfig {

}
