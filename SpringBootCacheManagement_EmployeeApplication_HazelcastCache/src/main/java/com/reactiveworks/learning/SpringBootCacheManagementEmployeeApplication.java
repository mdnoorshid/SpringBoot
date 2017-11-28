package com.reactiveworks.learning;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;

import com.github.benmanes.caffeine.cache.Caffeine;


/*import com.google.common.cache.CacheBuilder;
*/

@SpringBootApplication
@EnableCaching
public class SpringBootCacheManagementEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheManagementEmployeeApplication.class, args);
	}
   
	/**
	 * We can define explicitly our own Cache Management for Guava
	 * Then we don't need to define anything in application.properties file
	 */
	/*@Bean
	public CacheManager cacheManager(){
		GuavaCacheManager cacheManager=new GuavaCacheManager();
		cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS));
		cacheManager.setCacheNames(Arrays.asList("employeeCache"));
		return cacheManager;
	}*/
	/*@Bean
	public CacheManager cacheManager(){
		CaffeineCache cache=new CaffeineCache("employeeCache",Caffeine.newBuilder().expireAfterAccess(10,TimeUnit.SECONDS)
				.maximumSize(500)
				.build());
       
		return cache;
		
	}*/
	
	
}
