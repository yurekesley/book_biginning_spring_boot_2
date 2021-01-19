package br.com.yurekesley.features.condicional.by.system_properties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDBDatabaseTypeCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String enabledDBType = System.getProperty("dbType");
		return (enabledDBType != null && enabledDBType.equalsIgnoreCase("MONGODB"));
	}

}
