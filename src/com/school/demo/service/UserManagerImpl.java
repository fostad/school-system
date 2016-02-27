package com.school.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.demo.dao.UserDAO;
import com.school.demo.model.UserEntity;

@Service
public class UserManagerImpl implements UserManager{
	
	@Autowired
	UserDAO dao;
	
	public void addUser(UserEntity userEntity){
		dao.addUser(userEntity);
	}
}
