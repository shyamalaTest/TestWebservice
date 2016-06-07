package com.byteslounge.spring.tx.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.byteslounge.spring.tx.dao.UserDAO;
import com.byteslounge.spring.tx.model.User;
import com.byteslounge.spring.tx.user.UserManager;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}	

	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}
	
	public User getUserById(int id){
		return userDAO.getUserById(id);
	}
	
	public void updateUser(User user){
		  userDAO.updateUser(user);
	}
	
	public void deleteUserById(int id){
		 userDAO.deleteUserById(id);
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return userDAO.isUserExist(user);
	}


}
