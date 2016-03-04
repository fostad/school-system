package com.school.demo.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.school.demo.editors.CourseEditor;
import com.school.demo.editors.TeacherEditor;
import com.school.demo.model.CourseEntity;
import com.school.demo.model.StudentEntity;
import com.school.demo.model.TeacherEntity;
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(CourseEntity.class, new CourseEditor());
	}
	
	@ModelAttribute("allCourses")
	public List<CourseEntity> populateCourses() 
	{
		List <CourseEntity> courses = manager.getAllCourses();
		return courses;
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
	
	@RequestMapping(value="/editStudent", method = RequestMethod.GET)  
	public String editStudent(@RequestParam int id,Model model) { 
		
		StudentEntity student = manager.getStudent(id);
		List <CourseEntity> courses = manager.getAllCourses();
		List <CourseEntity> freeCourses = new ArrayList<CourseEntity>();
		boolean isEqual = false;
		for(CourseEntity course:courses){
			for(CourseEntity studentcourse:student.getCourses()){
				if(course.getId() == studentcourse.getId()){
					isEqual = true;
					break;
				}
			}
			if(!isEqual)
				freeCourses.add(course);
			else
				isEqual = false;
		}
		student.setFreeCourses(freeCourses);
		model.addAttribute("student", student);
		model.addAttribute("studentCourses", student.getCourses());
		model.addAttribute("freeCourses",freeCourses);
		return "studentView";
	}
	
	@RequestMapping(value="addCourses", method = RequestMethod.POST)
	public String addCourses(@ModelAttribute StudentEntity student, BindingResult result){
		if(result.hasErrors())
			return"redirect:editStudent?id="+student.getId();
		manager.addCourses(student.getId(),student.getFreeCourses());
		return "redirect:editStudent?id="+student.getId();
	}
	
	@RequestMapping(value="/deleteStudentCourse", method = RequestMethod.GET)
	public String deleteCourse(@RequestParam int courseID,@RequestParam int studentID){
		manager.deleteCourse(studentID,courseID);
		return "redirect:editStudent?id="+studentID;
	}
}
