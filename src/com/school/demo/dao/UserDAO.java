package com.school.demo.dao;

import com.school.demo.model.UserEntity;

public interface UserDAO {
	public void addUser(UserEntity userEntity);
	public UserEntity findByUsername(String username);
}
