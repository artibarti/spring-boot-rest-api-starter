package spring.boot.rest.api.starter.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "spring.boot.rest.api.starter")
@EnableJpaRepositories(basePackages = "spring.boot.rest.api.starter.persistence.repository")
@EntityScan("spring.boot.rest.api.starter.model")
@EnableAsync
public class SpringBootRestApiStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApiStarterApplication.class, args);
    }
}