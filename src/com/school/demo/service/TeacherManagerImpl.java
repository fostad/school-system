package com.school.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.demo.dao.TeacherDAO;
import com.school.demo.model.TeacherEntity;

@Service
public class TeacherManagerImpl implements TeacherManager{

	@Autowired
	TeacherDAO dao;
	
	public List<TeacherEntity> getAllTeachers() {
		return dao.getAllTeachers();
	}

	public void addTeacher(TeacherEntity teacher) {
		dao.addTeacher(teacher);
	}

	public void updateTeacher(TeacherEntity teacherEntity) {
		dao.updateTeacher(teacherEntity);		
	}

	public boolean deleteTeacher(int id) {
		return dao.deleteTeacher(id);
	}

	public TeacherEntity getTeacher(int id) {
		return dao.getTeacher(id);
		
	}


}
