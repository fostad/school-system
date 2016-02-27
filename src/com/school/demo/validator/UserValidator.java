package com.school.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.school.demo.model.TeacherEntity;
import com.school.demo.model.UserEntity;

@Component
public class UserValidator {

	public boolean supports(Class clazz) {
		return UserEntity.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) 
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username", "Username is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is required.");
	}
}
