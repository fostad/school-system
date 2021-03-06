package com.school.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.demo.dao.StudentDAO;
import com.school.demo.model.CourseEntity;
import com.school.demo.model.StudentEntity;

@Service
public class StudentManagerImpl implements StudentManager {

	@Autowired
	StudentDAO studentDAO;
	
	public List<StudentEntity> getAllStudents() {
		return studentDAO.getAllStudents();
	}

	public void addStudent(StudentEntity studentEntity) {
		studentDAO.addStudent(studentEntity);	
	}
	
	public void updateStudent(StudentEntity studentEntity){
		studentDAO.updateStudent(studentEntity);
	}
	
	public void deleteStudent(int id){
		studentDAO.deleteStudent(id);
	}

	public StudentEntity getStudent(int id){
		return studentDAO.getStudent(id);
	}

	public List<CourseEntity> getAllCourses() {
		return studentDAO.getAllCourses();
	}

	public void addCourses(int studentID, List<CourseEntity> courses) {
		studentDAO.addCourses(studentID, courses);		
	}

	public void deleteCourse(int studentID, int courseID) {
		studentDAO.deleteCourse(studentID,courseID);		
	}

}
