package com.dread9182.BTD6API.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
@RequiredArgsConstructor
public class MongoDBConfig implements InitializingBean {
	
	@Autowired
	@Lazy
	private final MappingMongoConverter mappingMongoConverter;
	
	//Avoids "_class" attribute in persistence
	@Override
	public void afterPropertiesSet() throws Exception {
		mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
	}
}