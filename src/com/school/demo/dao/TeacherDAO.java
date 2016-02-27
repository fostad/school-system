package com.school.demo.dao;

import java.util.List;

import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;


public interface TeacherDAO {
	public List<TeacherEntity> getAllTeachers();
	public void addTeacher(TeacherEntity teacher);
	public void updateTeacher(TeacherEntity teacher);
	public boolean deleteTeacher(int id);
	public TeacherEntity getTeacher(int id);
}
