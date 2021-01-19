package br.com.yurekesley.features.condicional.by.classpath;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySqlDriverNotPresentsCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return false;
		} catch (ClassNotFoundException e) {
			return true;
		}
	}
}
