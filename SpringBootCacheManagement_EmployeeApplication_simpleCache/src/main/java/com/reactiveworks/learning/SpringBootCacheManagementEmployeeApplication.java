package com.reactiveworks.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheManagementEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheManagementEmployeeApplication.class, args);
	}
	/**
	 * This is  explicit way of registering cache 
	 * We can register cache with auto-configuration as well
	 * after mentioning the right property in application.properties file  
	 * @return
	 */
	/*@Bean
	public CacheManager cacheManager(){
		//Recommended only for Testing or POC kind of Project
		return new ConcurrentMapCacheManager("employeeCache");
	}*/
}
