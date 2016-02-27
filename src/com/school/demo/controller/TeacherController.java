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

import com.school.demo.model.TeacherEntity;
import com.school.demo.service.TeacherManager;

@Controller
@SessionAttributes(types = TeacherController.class)
public class TeacherController {
	
	@Autowired
	TeacherManager manager;
	
	private Validator validator;

	//Bind custom validator for submitted form
	public TeacherController()
	{
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(CourseEntity.class, new CourseEditor());
//    }
	
	/**
	 * Bind all courses
	 * */
//	@ModelAttribute("allCourses")
//    public List<CourseEntity> populateCourses() 
//    {
//        List <CourseEntity> courses = manager.getAllCourses();
//        return courses;
//    }
	
	/**
	 * Bind all teachers list
	 * */
	@ModelAttribute("allTeachers")
    public List<TeacherEntity> populateTeachers() 
    {
        List<TeacherEntity> teachers = manager.getAllTeachers();
        return teachers;
    }
	
	
	
	/**
	 * Method will be called in initial page load at GET /teacher-module
	 * */
	@RequestMapping(value="/teacher-module",method = RequestMethod.GET)
	public String setupForm(Model model) 
	{
		TeacherEntity teacherVO = new TeacherEntity();
		model.addAttribute("teacher", teacherVO);
		return "listTeacherView";
	}

	/**
	 * Method will be called on submitting add teacher form POST /teacher-module
	 * */
	@RequestMapping(value="/addTeacher", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("teacher") TeacherEntity teacherVO,
							 BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<TeacherEntity>> violations = validator.validate(teacherVO);
		
		for (ConstraintViolation<TeacherEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("teacher", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listTeacherView";
		}
		//teacherVO.setCourse(courseVO);
		// Store the employee information in database
		manager.addTeacher(teacherVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:teacher-module";
	}
	
	@RequestMapping(value="/updateTeacher", method = RequestMethod.POST)
	public String updateTeacher(@ModelAttribute("teacher") TeacherEntity teacherVO,
							 BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<TeacherEntity>> violations = validator.validate(teacherVO);
		
		for (ConstraintViolation<TeacherEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("teacher", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listTeacherView";
		}
		//teacherVO.setCourse(courseVO);
		// Store the employee information in database
		manager.updateTeacher(teacherVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:teacher-module";
	}
	
	@RequestMapping(value="/deleteTeacher", method = RequestMethod.GET)
	public String deleteTeacher(@RequestParam int id){
		if(manager.deleteTeacher(id))
			return "redirect:teacher-module";
		else
			return "teacherNotDelete";
			
	}
	
	@RequestMapping(value="editTeacher", method = RequestMethod.GET)  
	public String editTeacher(@RequestParam int id, Model model) {  
		model.addAttribute("teacher", manager.getTeacher(id));
		return "teacherView";
	}
	
}
