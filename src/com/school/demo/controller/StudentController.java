package com.school.demo.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.school.demo.model.StudentEntity;
import com.school.demo.service.StudentManager;

@Controller
@SessionAttributes(types = StudentController.class)
public class StudentController {
	
	@Autowired
	StudentManager manager;
	
	private Validator validator;
	
	public StudentController(){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	

	@ModelAttribute("allStudents")
    public List<StudentEntity> populateStudents() 
    {
        List<StudentEntity> students = manager.getAllStudents();
        return students;
    }
	
	@RequestMapping(value="/student-module", method = RequestMethod.GET)
	public String setupForm(Model model) 
	{
		StudentEntity studentVO = new StudentEntity();
		model.addAttribute("student", studentVO);
		return "listStudentView";
	}
	

	@RequestMapping(value="/addStudent",method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("student") StudentEntity studentVO,
							 BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<StudentEntity>> violations = validator.validate(studentVO);
		
		
		for (ConstraintViolation<StudentEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("addStudent", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listStudentView";
		}
		// Store the employee information in database
		manager.addStudent(studentVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:student-module";
	}
	
	@RequestMapping(value="/updateStudent", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute StudentEntity studentVO,
											  BindingResult result, SessionStatus status){
		Set<ConstraintViolation<StudentEntity>> violations = validator.validate(studentVO);
		
		
		for (ConstraintViolation<StudentEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("editStudent", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listStudentView";
		}
		
		manager.updateStudent(studentVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:student-module";
	}
	
	@RequestMapping(value="/deleteStudent", method = RequestMethod.GET)
	public String deleteStudent(@RequestParam int id){
		manager.deleteStudent(id);
		return "redirect:student-module";
	}
	
	@RequestMapping(value="editStudent", method = RequestMethod.GET)  
	public String editStudent(@RequestParam int id,Model model) { 
		model.addAttribute("student", manager.getStudent(id));
		return "studentView";
	}
	
}
