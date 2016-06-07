package com.byteslounge.spring.tx.dao.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.byteslounge.spring.tx.dao.UserDAO;
import com.byteslounge.spring.tx.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;
	private static List<User> users;

	public void insertUser(User user) {
		user.setId(0);		
		entityManager.persist(user);
	}
	public List<User> findAllUsers() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}
	
	@Transactional
	public void updateUser(User user) 
    {
        try
        {
        //	Query q = entityManager.createNamedQuery("updateUserName", User.class);        	
        	Query q = entityManager.createQuery("UPDATE User s SET s.name = :name,s.username =:username WHERE s.id = :id");
        	q.setParameter("id", user.getId());
        	q.setParameter("name", user.getName());
        	q.setParameter("username", user.getUsername());
        	int updated = q.executeUpdate();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }


	public User getUserById(int id_test) {
		// TODO Auto-generated method stub
		try {			
			String qlString = "SELECT a FROM User a WHERE a.id = :id";
			TypedQuery<User> query = entityManager.createQuery(qlString, User.class);	
			query.setParameter("id" , id_test);	 
			return query.getSingleResult();			
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);	 
	}
	 
	 public User findByName(String name) {
	        for(User user : users){
	            if(user.getName().equalsIgnoreCase(name)){
	                return user;
	            }
	        }
	        return null;
	    }
	 
	 public boolean isUserExist(User user) {
	        return findByName(user.getName())!=null;
	    }


}
