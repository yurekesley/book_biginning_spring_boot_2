package br.com.yurekesley.workingwithjooq.config;

import javax.sql.DataSource;

import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class JooqDataSourceConfig {

	@Bean
	public DefaultDSLContext dsl() {
		return new DefaultDSLContext(configuration());
	}

	public DefaultConfiguration configuration() {
		DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
		DataSourceConnectionProvider connectionProvider = connectionProvider();
		DefaultExecuteListenerProvider executeListenerProvider = executeListenerProvider();
		jooqConfiguration.set(connectionProvider);
		jooqConfiguration.set(executeListenerProvider);
		jooqConfiguration.set(org.jooq.SQLDialect.MYSQL);
		return jooqConfiguration;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.jooq")
	public DataSourceProperties jooqDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource jooqDataSource() {
		DataSourceProperties jooqDataSource = jooqDataSourceProperties();
		return DataSourceBuilder.create().driverClassName(jooqDataSource.getDriverClassName())
				.url(jooqDataSource.getUrl()).username(jooqDataSource.getUsername())
				.password(jooqDataSource.getPassword()).build();
	}

	@Bean
	public DataSourceConnectionProvider connectionProvider() {
		TransactionAwareDataSourceProxy dataSource = new TransactionAwareDataSourceProxy(jooqDataSource());
		return new DataSourceConnectionProvider(dataSource);
	}

	private DefaultExecuteListenerProvider executeListenerProvider() {
		DefaultExecuteListener listener = new DefaultExecuteListener();
		return new DefaultExecuteListenerProvider(listener);
	}

	@Bean("jooqDataSourceInitializer")
	public DataSourceInitializer jooqDataSourceInitializer() {
		DataSourceInitializer dsInitializer = new DataSourceInitializer();
		dsInitializer.setDataSource(jooqDataSource());
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("schema.sql"));
		populator.addScript(new ClassPathResource("jooq-data.sql"));
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);
		dsInitializer.setDatabasePopulator(populator);
		return dsInitializer;
	}

}
