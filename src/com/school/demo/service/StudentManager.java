package com.school.demo.service;

import java.util.List;

import com.school.demo.model.StudentEntity;

public interface StudentManager {
	public List<StudentEntity> getAllStudents();
	public void addStudent(StudentEntity studentEntity);
	public void deleteStudent(int id);
	public void updateStudent(StudentEntity studentEntity);
	public StudentEntity getStudent(int id);
}
