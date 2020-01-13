package com.springboot.microservices.demo.application;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = { "com.springboot.microservices.demo.repo" })
@ComponentScan(value = "com.springboot.microservices.demo.*")
@EntityScan(basePackages = { "com.springboot.microservices.demo.entities" })
public class DataStoreSetup {

	@Value("${spring.datasource.url}")
	String databaseUrl;

	@Value("${spring.datasource.username}")
	String databaseUser;

	@Value("${spring.datasource.password}")
	String databasePassword;


	@Value("${spring.datasource.driver-class-name}") 
	String databaseDriver;


	@Value("${spring.jpa.properties.hibernate.dialect}")
	String mySQL5Dialect;

	/*
	 * @Value("${spring.datasource.hikari.connection-timeout}") int
	 * conectionTimeout;
	 */

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriver);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUser);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.springboot.microservices.demo.entities");
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", mySQL5Dialect);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}
}
