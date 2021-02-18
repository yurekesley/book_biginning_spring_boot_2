package br.com.yurekesley.workingwithjpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import br.com.yurekesley.workingwithjpa.entity.User;
import br.com.yurekesley.workingwithjpa.repository.UserRepository;

@SpringBootTest
class WorkingWithJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAllUsers() {
		List<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}

	@Test
	public void findUserById() {
		Optional<User> user = userRepository.findById(1);
		assertNotNull(user.get());
	}

	@Test
	public void findUserByName() {
		User user = userRepository.findByName("Becky");
		assertNotNull(user);
	}

	@Test
	public void searchByName() {
		List<User> users = userRepository.searchByName("Becky");
		assertNotNull(users);
	}

	@Test
	public void sort() {
		Sort sort = Sort.by(Direction.ASC, "name");
		List<User> users = userRepository.findAll(sort);
		assertNotNull(users);
	}

	@Test
	public void sort2() {
		Order order1 = new Order(Direction.ASC, "name");
		Order order2 = new Order(Direction.DESC, "id");
		Sort sort = Sort.by(order1, order2);
		List<User> users = userRepository.findAll(sort);
		assertNotNull(users);
	}

	@Test
	public void pagination() {
		int size = 1;
		int page = 0;
		Pageable pageable = PageRequest.of(page, size);
		Page<User> usersPage = userRepository.findAll(pageable);
		assertNotNull(usersPage.getContent());

	}

	public void createUser() {
		User user = new User(null, "Paul", "paul@gmail.com", false);
		User savedUser = userRepository.save(user);
		User newUser = userRepository.findById(savedUser.getId()).get();
		assertEquals("SivaPrasad", newUser.getName());
		assertEquals("sivaprasad@gmail.com", newUser.getEmail());
	}

}
