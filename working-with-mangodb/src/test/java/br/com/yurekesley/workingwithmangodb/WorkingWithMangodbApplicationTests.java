package br.com.yurekesley.workingwithmangodb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.yurekesley.workingwithmangodb.entity.User;
import br.com.yurekesley.workingwithmangodb.service.UserService;

@SpringBootTest
class WorkingWithMangodbApplicationTests {

	@Autowired
	private UserService userService;
	
	@Test
	void contextLoads() {
		List<User> users = userService.getUsers();
	}

}
