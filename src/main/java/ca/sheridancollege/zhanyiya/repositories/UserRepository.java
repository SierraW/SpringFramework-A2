package ca.sheridancollege.zhanyiya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhanyiya.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByName(String name);

}
