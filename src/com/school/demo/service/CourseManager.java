package com.school.demo.service;

import java.util.List;

import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;

public interface CourseManager {
	public List<CourseEntity> getAllCourses();
	public List<TeacherEntity> getAllTeachers();
	public void addCourse(CourseEntity course);
	public void updateCourse(CourseEntity course);
	public void deleteCourse(int id);
	public CourseEntity getCourse(int id);
}
