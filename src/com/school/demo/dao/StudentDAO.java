package com.school.demo.dao;

import java.util.List;

import com.school.demo.model.StudentEntity;

public interface StudentDAO {
	//public List<CourseEntity> getAllCourses();
	public List<StudentEntity> getAllStudents();
	public void addStudent(StudentEntity studentEntity);
	public void deleteStudent(int id);
	public void updateStudent(StudentEntity studentEntity);
	public StudentEntity getStudent(int id);
}