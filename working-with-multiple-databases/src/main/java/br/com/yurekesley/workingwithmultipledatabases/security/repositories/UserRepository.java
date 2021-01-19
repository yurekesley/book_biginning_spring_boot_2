package br.com.yurekesley.workingwithmultipledatabases.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yurekesley.workingwithmultipledatabases.security.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
