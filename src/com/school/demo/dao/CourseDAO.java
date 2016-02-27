package com.school.demo.dao;

import java.util.List;

import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;

public interface CourseDAO {
	public List<CourseEntity> getAllCourses();
	public List<TeacherEntity> getAllTeachers();
	public void addCourse(CourseEntity course);
	public void updateCourse(CourseEntity course);
	public void deleteCourse(int id);
	public CourseEntity getCourse(int id);
}
