package br.com.yurekesley.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yurekesley.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}
