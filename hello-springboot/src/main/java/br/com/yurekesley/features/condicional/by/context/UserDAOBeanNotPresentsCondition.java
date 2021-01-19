package br.com.yurekesley.features.condicional.by.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import br.com.yurekesley.features.condicional.by.system_properties.UserDAO;

/*
 * Condicional por contexto.
 * 
 * */
public class UserDAOBeanNotPresentsCondition implements Condition {

	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
		BeanFactory beanFactory = conditionContext.getBeanFactory();
		UserDAO userDAO = beanFactory.getBean(UserDAO.class);
		return (userDAO == null);
	}

}
