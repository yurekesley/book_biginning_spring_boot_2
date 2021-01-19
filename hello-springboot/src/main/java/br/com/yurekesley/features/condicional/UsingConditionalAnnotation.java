package br.com.yurekesley.features.condicional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import br.com.yurekesley.features.condicional.by.annotation.DatabaseType;
import br.com.yurekesley.features.condicional.by.classpath.CustomMySqlDriver;
import br.com.yurekesley.features.condicional.by.classpath.Driver;
import br.com.yurekesley.features.condicional.by.classpath.MySqlDriver;
import br.com.yurekesley.features.condicional.by.classpath.MySqlDriverNotPresentsCondition;
import br.com.yurekesley.features.condicional.by.classpath.MySqlDriverPresentsCondition;
import br.com.yurekesley.features.condicional.by.context.MyProvide;
import br.com.yurekesley.features.condicional.by.context.Provider;
import br.com.yurekesley.features.condicional.by.context.UserDAOBeanNotPresentsCondition;
import br.com.yurekesley.features.condicional.by.system_properties.JdbcUserDAO;
import br.com.yurekesley.features.condicional.by.system_properties.MongoUserDAO;
import br.com.yurekesley.features.condicional.by.system_properties.UserDAO;

/*
 * Temos que tomar cuidado nessa abordagem,
 *  pois caso duas ou mais implementações 
 *  de {@code matches de org.springframework.context.annotation.Condition }
 *  retorne true para o mesmo tipo de bean, devemos qualifica-lo com 
 *  as instâncias disponíveis no contexto no momento da injeção
 * **/
@Component
public class UsingConditionalAnnotation {

	@Bean
	@DatabaseType("MYSQL")
	public UserDAO jdbcUserDAO() {
		return new JdbcUserDAO();
	}

	@Bean
	@DatabaseType("MONGO")
	public UserDAO mongoUserDAO() {
		return new MongoUserDAO();
	}

	@Bean
	@Conditional(MySqlDriverPresentsCondition.class)
	public Driver mySqlDriver() {
		return new MySqlDriver();
	}

	@Bean
	@Conditional(MySqlDriverNotPresentsCondition.class)
	public Driver customMySqlDriver() {
		return new CustomMySqlDriver();
	}

	@Bean
	@Conditional(UserDAOBeanNotPresentsCondition.class)
	public Provider privider() {
		return new MyProvide();
	}

}
