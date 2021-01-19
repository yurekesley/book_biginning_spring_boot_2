package br.com.yurekesley.workingwithmultipledatabases.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = SecurityDBConfig.REPOSITORIES_PACKAGE, entityManagerFactoryRef = "securityEntityManagerFactory", transactionManagerRef = "securityTransactionManager")
public class SecurityDBConfig {

	static final String REPOSITORIES_PACKAGE = "br.com.yurekesley.workingwithmultipledatabases.security.repositories";
	static final String REPOSITORIES_ENTITIES = "br.com.yurekesley.workingwithmultipledatabases.security.entities";

	@Autowired
	private Environment env;

	@Autowired
	private HibernateConfig hibernateConfig;

	@Bean
	@ConfigurationProperties(prefix = "datasource.security")
	public DataSourceProperties securityDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("securityDataSource")
	public DataSource securityDataSource() {
		DataSourceProperties securityDataSourceProperties = securityDataSourceProperties();
		return DataSourceBuilder.create().driverClassName(securityDataSourceProperties.getDriverClassName())
				.url(securityDataSourceProperties.getUrl()).username(securityDataSourceProperties.getUsername())
				.password(securityDataSourceProperties.getPassword()).build();
	}

	@Bean("securityTransactionManager")
	public PlatformTransactionManager securityTransactionManager() {
		EntityManagerFactory factory = securityEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean("securityEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(securityDataSource());
		factory.setPackagesToScan(SecurityDBConfig.REPOSITORIES_ENTITIES);
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateConfig.getHbm2ddl().getAuto());
		jpaProperties.put("hibernate.show-sql", hibernateConfig.getShowSql());
		factory.setJpaProperties(jpaProperties);
		return factory;
	}

	@Bean("securityDataSourceInitializer")
	public DataSourceInitializer securityDataSourceInitializer() {
		DataSourceInitializer dsInitializer = new DataSourceInitializer();
		dsInitializer.setDataSource(securityDataSource());
		ResourceDatabasePopulator dbPopulator = new ResourceDatabasePopulator();
		dbPopulator.addScript(new ClassPathResource("security-data.sql"));
		dsInitializer.setDatabasePopulator(dbPopulator);
		dsInitializer.setEnabled(env.getProperty("datasource.security.initialize", Boolean.class, false));
		return dsInitializer;
	}
}
