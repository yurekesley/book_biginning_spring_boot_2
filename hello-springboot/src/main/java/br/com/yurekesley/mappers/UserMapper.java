package br.com.yurekesley.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import br.com.yurekesley.domain.User;

/**
 * MyBatis annotation-based configuration
 * 
 * @author yure.placido
 *
 */
public interface UserMapper {

	@Insert("insert into users(id, name,email) values(#{id}, #{name},#{email})")
	@SelectKey(statement = "SELECT @@IDENTITY", keyProperty = "id", before = false, resultType = Integer.class)
	public void insertUser(User user);

	@Select("select id, name, email from users WHERE id=#{id}")
	public User findUserById(Integer id);

	@Select("select id, name, email from users")
	public List<User> findAllUsers();
}