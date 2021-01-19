package br.com.yurekesley.mappers;

import java.util.List;

import br.com.yurekesley.domain.User;

/**
 * MyBatis xml-based configuration
 * 
 * @author yure.placido
 * @see UserMapper.xml
 *
 */
public interface UserMapperXml {

	public void insertUser(User user);

	public User findUserById(Integer id);

	public List<User> findAllUsers();
}
