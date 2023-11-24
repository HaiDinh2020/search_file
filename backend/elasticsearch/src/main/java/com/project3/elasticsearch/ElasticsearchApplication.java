package com.project3.elasticsearch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
//@EnableJpaRepositories("com.project3.elasticsearch.elasticsearchrepo")
//@EnableElasticsearchRepositories("com.project3.elasticsearch.repo")
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
				registry.addMapping("/api/search")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}

//	@Bean
//	public RestClient getRestClient() {
//		RestClient restClient = RestClient.builder(
//				new HttpHost("localhost", 9200)).build();
//		return restClient;
//	}
//
//	@Bean
//	public ElasticsearchTransport getElasticsearchTransport() {
//		return new RestClientTransport(
//				getRestClient(), new JacksonJsonpMapper());
//	}
//
//
//	@Bean
//	public ElasticsearchClient getElasticsearchClient(){
//		ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
//		return client;
//	}

}
