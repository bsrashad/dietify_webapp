package com.dietify.v1.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dietify.v1.Entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
	boolean existsByEmail(String email);
}
