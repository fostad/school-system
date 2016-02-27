package com.school.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.school.demo.model.CourseEntity;


@Component
public class CourseValidator {
	public boolean supports(Class clazz) {
		return CourseEntity.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) 
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "points", "error.points", "Points is required.");
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teacher","Teacher is required.");
	}
	
}
