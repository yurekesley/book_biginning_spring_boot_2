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
@EnableJpaRepositories(basePackages = OrderDBConfig.REPOSITORIES_PACKAGE, entityManagerFactoryRef = "ordersEntityManagerFactory", transactionManagerRef = "ordersTransactionManager")
public class OrderDBConfig {

	static final String REPOSITORIES_PACKAGE = "br.com.yurekesley.workingwithmultipledatabases.order.repositories";
	static final String REPOSITORIES_ENTITIES = "br.com.yurekesley.workingwithmultipledatabases.order.entities";

	@Autowired
	private Environment env;
	
	@Autowired
	private HibernateConfig hibernateConfig; 

	@Bean
	@ConfigurationProperties(prefix = "datasource.orders")
	public DataSourceProperties ordersDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("ordersDataSource")
	public DataSource ordersDataSource() {
		DataSourceProperties securityDataSourceProperties = ordersDataSourceProperties();
		return DataSourceBuilder.create().driverClassName(securityDataSourceProperties.getDriverClassName())
				.url(securityDataSourceProperties.getUrl()).username(securityDataSourceProperties.getUsername())
				.password(securityDataSourceProperties.getPassword()).build();
	}

	@Bean("ordersTransactionManager")
	public PlatformTransactionManager ordersTransactionManager() {
		EntityManagerFactory factory = ordersEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean("ordersEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ordersEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(ordersDataSource());
		factory.setPackagesToScan(OrderDBConfig.REPOSITORIES_ENTITIES);
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateConfig.getHbm2ddl().getAuto());
		jpaProperties.put("hibernate.show-sql", hibernateConfig.getShowSql());
		factory.setJpaProperties(jpaProperties);
		return factory;
	}

	@Bean("ordersDataSourceInitializer")
	public DataSourceInitializer ordersDataSourceInitializer() {
		DataSourceInitializer dsInitializer = new DataSourceInitializer();
		dsInitializer.setDataSource(ordersDataSource());
		ResourceDatabasePopulator dbPopulator = new ResourceDatabasePopulator();
		dbPopulator.addScript(new ClassPathResource("orders-data.sql"));
		dsInitializer.setDatabasePopulator(dbPopulator);
		dsInitializer.setEnabled(env.getProperty("datasource.orders.initialize", Boolean.class, false));
		return dsInitializer;
	}
}
