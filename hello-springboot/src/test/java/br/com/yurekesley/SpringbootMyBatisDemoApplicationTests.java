package br.com.yurekesley;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.yurekesley.config.FlywayMigrationConfig;
import br.com.yurekesley.domain.User;
import br.com.yurekesley.mappers.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
@Import(FlywayMigrationConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringbootMyBatisDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	@Sql("createUser.sql")
	public void findAllUsers() {
		List<User> users = userMapper.findAllUsers();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}

	@Test
	@Sql("createUser.sql")
	public void findUserById() {
		User user = userMapper.findUserById(1);
		assertNotNull(user);
	}

	@Test
	public void createUser() {
		User user = new User("george", "george@gmail.com");
		userMapper.insertUser(user);
		User newUser = userMapper.findUserById(user.getId());
		assertEquals("george", newUser.getName());
	}

}
