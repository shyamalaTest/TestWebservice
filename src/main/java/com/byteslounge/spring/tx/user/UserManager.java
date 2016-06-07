package com.byteslounge.spring.tx.user;

import java.util.List;

import com.byteslounge.spring.tx.model.User;

public interface UserManager {

	void insertUser(User user);

	List<User> findAllUsers();
	
	User getUserById(int id);
	
	void updateUser(User user);
	
	void deleteUserById(int id);

	boolean isUserExist(User user);

}
