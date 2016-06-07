package com.byteslounge.spring.tx;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import com.byteslounge.spring.tx.model.User;

public class SpringRestTestClient {
	 public static final String REST_SERVICE_URI = "http://localhost:8080/Test_Webservices";
     
	    /* GET */
	    private static void listAllUsers(){
	        System.out.println("Testing listAllUsers API-----------");
	         
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/WebUserDetails/UserDetails", List.class);
	         
	        if(usersMap!=null){
	            for(LinkedHashMap<String, Object> map : usersMap){
	                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", UserName="+map.get("username"));
	            }
	        }else{
	            System.out.println("No user exist----------");
	        }
	    }
	  /*  
	     POST 
	    private static void createUser() {
	        System.out.println("Testing create User API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        User user = new User(0,"Sarah","Khan");
	        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/WebUserDetails/CreateUser", user, User.class);
	        System.out.println("Location : "+uri.toASCIIString());
	    }
	 */
	    
	    public static void main(String args[]){
	      //  listAllUsers();
	      //  createUser();
	       /* getUser();
	        
	        listAllUsers();
	        updateUser();
	        listAllUsers();
	        deleteUser();
	        listAllUsers();
	        deleteAllUsers();
	        listAllUsers();*/
	    }
	}

