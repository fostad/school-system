package com.school.demo.service;

import java.util.List;

import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;

public interface TeacherManager {
	
	public List<TeacherEntity> getAllTeachers();
	public void addTeacher(TeacherEntity teacherEntity);
	public void updateTeacher(TeacherEntity teacherEntity);
	public boolean deleteTeacher(int id);
	public TeacherEntity getTeacher(int id);
}
