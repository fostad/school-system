package com.school.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.demo.model.UserEntity;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	@PersistenceContext
	private EntityManager manager;
	
	public void addUser(UserEntity userEntity) {
		manager.persist(userEntity);		
	}
	
	public UserEntity findByUsername(String username){
		return (UserEntity) manager.createQuery("from UserEntity where username = :username",UserEntity.class)
				.setParameter("username", username)
                .getSingleResult();
	}
	
}
