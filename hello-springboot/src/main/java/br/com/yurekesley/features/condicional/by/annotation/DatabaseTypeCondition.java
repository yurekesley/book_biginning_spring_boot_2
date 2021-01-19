package br.com.yurekesley.features.condicional.by.annotation;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/*
 * Qualificando o bean pelo valor passando na annotarion
 * e presente 
 * */
public class DatabaseTypeCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> attributes = metadata.getAnnotationAttributes(DatabaseType.class.getName());
		String type = (String) attributes.get("value");

		String enabledDBType = System.getProperty("dbType");

		if (enabledDBType == null)
			enabledDBType = context.getEnvironment().getProperty("dbType");

		return (enabledDBType != null && type != null && enabledDBType.equalsIgnoreCase(type));
	}

}
