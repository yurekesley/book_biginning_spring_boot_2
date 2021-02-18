package br.com.yurekesley.workingwithmangodb.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig {

	@Bean
	public MongoClient mongo(MongoProperties properties) {
		String uri = properties.getUri();
		ConnectionString connectionString = new ConnectionString(uri);
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();
		return MongoClients.create(mongoClientSettings);
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoClient mongoClient, MongoProperties properties) throws Exception {
		String database = properties.getDatabase();
		return new MongoTemplate(mongoClient, database);
	}

}
