package br.com.yurekesley.workingwithmybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import br.com.yurekesley.workingwithmybatis.domain.User;

/**
 * @author yure.placido
 *
 */
public interface UserH2Mapper {

	@Insert("insert into users(name,email) values(#{name},#{email})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Integer.class)
	public void insertUser(User user);

	@Select("select id, name, email from users WHERE id=#{id}")
	public User findUserById(Integer id);

	@Select("select id, name, email from users")
	public List<User> findAllUsers();

}
