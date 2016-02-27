package com.school.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.demo.dao.CourseDAO;
import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;

@Service
public class CourseManagerImpl implements CourseManager{

	@Autowired
	CourseDAO dao;
	
	public List<CourseEntity> getAllCourses() {
		return dao.getAllCourses();
	}

	public void addCourse(CourseEntity course) {
		dao.addCourse(course);
	}

	public List<TeacherEntity> getAllTeachers() {
		return dao.getAllTeachers();
	}

	public void updateCourse(CourseEntity course) {
		dao.updateCourse(course);
		
	}

	public void deleteCourse(int id) {
		dao.deleteCourse(id);
	}

	public CourseEntity getCourse(int id) {
		return dao.getCourse(id);
	}

}
