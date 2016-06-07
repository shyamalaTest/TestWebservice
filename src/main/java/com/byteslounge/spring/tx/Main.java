package com.byteslounge.spring.tx;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.byteslounge.spring.tx.model.User;
import com.byteslounge.spring.tx.user.UserManager;


public class Main {
	public static void main(String[] args) {
		
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
		 UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
		
		//##### Get All Data From table ########//
		List<User> list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());
		for(User user : list)
		{
			System.out.println(user.getId()+" user 01 "+user.getName()+" user 02 "+user.getUsername());
		}
		
		//##### Insert into table ########//
		User user = new User();
		user.setUsername("johndoe");
		user.setName("John Doe");
		userManager.insertUser(user);
		System.out.println("User inserted!");

		list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());
		
		//##### Upadate User ####//
		//userManager.updateUser(2, "test55");
		 	 	
		//##### Delete User ####//
		//userManager.deleteUserById(34);
		
		// #### GET User By Id ####//
		User ByIs = userManager.getUserById(37);
		System.out.println("getUserBy Ids :: "+ByIs.getId()+" NAme "+ByIs.getName());
	}
}
