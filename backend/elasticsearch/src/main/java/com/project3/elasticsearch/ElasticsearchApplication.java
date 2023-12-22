package com.project3.elasticsearch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
@SpringBootApplication
//@EnableJpaRepositories("com.project3.elasticsearch.repo")
@EnableElasticsearchRepositories("com.project3.elasticsearch.elasticsearchrepo")
//@ComponentScan("com.project3.elasticsearch.repo")
public class ElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/upload")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
				registry.addMapping("/api/allFile")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
				registry.addMapping("/search")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
				registry.addMapping("/search/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
				registry.addMapping("/exac/search/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
				registry.addMapping("/search/multiword/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}


}
