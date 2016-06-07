package com.byteslounge.spring.tx;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.byteslounge.spring.tx.model.User;
import com.byteslounge.spring.tx.user.UserManager;

// ##   WebUserDetails/UserDetails

@Controller
@RequestMapping("/WebUserDetails")
public class CommonRestController {
	
	static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
	static UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");		
	
	//-------------------Get All Users--------------------------------------------------------

	@RequestMapping(value = "/UserDetails", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userManager.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

	//-------------------CreateUser **    ------------
	  @RequestMapping(value = "/CreateUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Void> createUser(@RequestBody User user) {
	        userManager.insertUser(user); 	  
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
    
  //------------------- Update a User --------------------------------------------------------
    
	     @RequestMapping(value = "/UpdateUser/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userManager.getUserById(id);         
        currentUser.setName(user.getName());
        currentUser.setUsername(user.getUsername());
         
        userManager.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
   
    //------------------- Delete a User --------------------------------------------------------
    
    @RequestMapping(value = "/DeleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
 
        User user = userManager.getUserById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userManager.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

	
	/*public static void main(String args[])
	{
		List<User> users = userManager.findAllUsers();
		for(User U: users)
		{			
			System.out.print("U val :: "+U.getName());
		}	
	}*/

}