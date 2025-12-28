package com.project.community;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication implements CommandLineRunner {

	@Value("${spring.datasource.url}")
	private  String url;

	@Value("${spring.datasource.username}")
	private String uname;


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Database URL: " + url);
		System.out.println("Database Username: " + uname);
	}

	public static void main(String[] args) {

		SpringApplication.run(CommunityApplication.class, args);

	}
}