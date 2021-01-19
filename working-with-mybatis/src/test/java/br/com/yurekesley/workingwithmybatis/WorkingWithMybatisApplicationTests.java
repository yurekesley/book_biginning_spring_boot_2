package br.com.yurekesley.workingwithmybatis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.yurekesley.workingwithmybatis.domain.User;
import br.com.yurekesley.workingwithmybatis.mappers.UserH2Mapper;
import br.com.yurekesley.workingwithmybatis.mappers.UserMapper;

@SpringBootTest
public class WorkingWithMybatisApplicationTests {


	@Autowired
	private UserH2Mapper userH2Mapper;

	@Test
	void shouldFindAllUsers() {
		List<User> users = userH2Mapper.findAllUsers();
		assertTrue(!users.isEmpty());
	}

	@Test
	void shouldCreateUser() {
		User user = new User(0, "yure", "yure@gmail.com");
		userH2Mapper.insertUser(user);
		User newUser = userH2Mapper.findUserById(user.getId());
		assertEquals("yure", newUser.getName());
		assertEquals("yure@gmail.com", newUser.getEmail());
	}

}
