package com.reactiveworks.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

/**
 * Configuration class for hazelcast
 * @author Md Noorshid
 */
@Configuration
public class HazelCastConfig {
	@Bean
public Config hazelcastConfugration(){
	return new Config().setInstanceName("hazelcast-instance")
			.addMapConfig(new MapConfig().setName("employeeCache")
		    .setMaxSizeConfig(new MaxSizeConfig(200,MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
		    .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(200));
}
}
