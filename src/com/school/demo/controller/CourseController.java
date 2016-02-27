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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.school.demo.editors.TeacherEditor;
import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;
import com.school.demo.service.CourseManager;

@Controller
@SessionAttributes(types = CourseController.class)
public class CourseController {
	
	@Autowired
	CourseManager manager;
	

	private Validator validator;

	//Bind custom validator for submitted form
	public CourseController()
	{
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	
  @InitBinder
  public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(TeacherEntity.class, new TeacherEditor());
  }
	
	/**
	 * Bind all teachers
	 * */
	@ModelAttribute("allTeachers")
  public List<TeacherEntity> populateTeachers() 
  {
      List <TeacherEntity> teachers = manager.getAllTeachers();
      return teachers;
  }

	/**
	 * Bind all courses list
	 * */
	@ModelAttribute("allCourses")
    public List<CourseEntity> populateCourses() 
    {
        List<CourseEntity> courses = manager.getAllCourses();
        return courses;
    }
	
	/**
	 * Method will be called in initial page load at GET /course-module
	 * */
	@RequestMapping(value="/course-module", method = RequestMethod.GET)
	public String setupForm(Model model) 
	{
		CourseEntity courseVO = new CourseEntity();
		model.addAttribute("course", courseVO);
		return "listCourseView";
	}

	/**
	 * Method will be called on submitting add course form POST /course-module
	 * */
	@RequestMapping(value="/addCourse", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("course") CourseEntity courseVO,
			BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<CourseEntity>> violations = validator.validate(courseVO);
		
		for (ConstraintViolation<CourseEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("course", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listCourseView";
		}
		// Store the employee information in database
		manager.addCourse(courseVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:course-module";
	}
	
	@RequestMapping(value="/updateCourse", method = RequestMethod.POST)
	public String updateCourse(@ModelAttribute("course") CourseEntity courseVO,
			BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<CourseEntity>> violations = validator.validate(courseVO);
		
		for (ConstraintViolation<CourseEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("course", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listCourseView";
		}
		// Store the employee information in database
		manager.updateCourse(courseVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:course-module";
	}
	
	@RequestMapping(value="/deleteCourse", method = RequestMethod.GET)
	public String deleteCourse(@RequestParam int id){
		manager.deleteCourse(id);
		return "redirect:course-module";
	}
	
	@RequestMapping(value="editCourse", method = RequestMethod.GET)  
	public String editCourse(@RequestParam int id, Model model) {  
	  model.addAttribute("course", manager.getCourse(id));
		return "courseView"; 
	}
	
}
