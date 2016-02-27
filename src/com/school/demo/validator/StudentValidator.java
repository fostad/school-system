package com.school.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.school.demo.model.StudentEntity;

@Component
public class StudentValidator {

	public boolean supports(Class clazz) {
		return StudentEntity.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) 
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email", "Email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalNumber", "error.personalNumber", "Personal Number is required.");
	}
}
