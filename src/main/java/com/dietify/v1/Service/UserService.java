package com.dietify.v1.Service;

import com.dietify.v1.Entity.User;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();

	public boolean existsByEmail(String email);
}
