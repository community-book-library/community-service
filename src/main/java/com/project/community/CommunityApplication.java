package com.project.community;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
        "com.project.community"
})
public class CommunityApplication {

	@Value("${spring.datasource.url}")
	private  String url;

	@Value("${spring.datasource.username}")
	private String uname;

	public static void main(String[] args) {

		SpringApplication.run(CommunityApplication.class, args);

	}
}