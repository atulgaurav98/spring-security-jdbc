package com.example.springsecurityjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringSecurityJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJdbcApplication.class, args);
	}

}
