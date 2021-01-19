package br.com.yurekesley.features.condicional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yurekesley.features.condicional.by.classpath.Driver;
import br.com.yurekesley.features.condicional.by.context.Provider;
import br.com.yurekesley.features.condicional.by.system_properties.UserDAO;

@Component
public class UseConditionalResult {

	private final UserDAO dao;
	private final Driver driver;
	private final Provider privider;

	@Autowired
	public UseConditionalResult(UserDAO dao, Driver driver, @Autowired(required = false) Provider privider) {
		this.dao = dao;
		this.driver = driver;
		this.privider = privider;
	}

}
