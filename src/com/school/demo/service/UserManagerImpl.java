package com.school.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.demo.dao.UserDAO;
import com.school.demo.model.UserEntity;

@Service
@Transactional(readOnly=true)
public class UserManagerImpl implements UserManager{
	
	@Autowired
	UserDAO dao;
	
	public void addUser(UserEntity userEntity){
		dao.addUser(userEntity);
	}

	public UserEntity findByUsername(String username) {
		return findByUsername(username);
	}
}
