package com.school.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.demo.model.CourseEntity;
import com.school.demo.model.TeacherEntity;

@Repository
@Transactional
public class TeacherDAOImpl implements TeacherDAO{

	@PersistenceContext
	private EntityManager manager;
	
	public List<TeacherEntity> getAllTeachers() 
	{
		List<TeacherEntity> teachers = manager.createQuery("Select a From TeacherEntity a", TeacherEntity.class).getResultList();
        return teachers;
	}
	
	public void addTeacher(TeacherEntity teacher) 
	{
		//Use null checks and handle them
		manager.persist(teacher);
	}

	public void updateTeacher(TeacherEntity teacher) {
		manager.merge(teacher);
		manager.flush();
	}

	public boolean deleteTeacher(int id) {
		TeacherEntity teacherEntity = manager.find(TeacherEntity.class, id);
		List<CourseEntity> courses = manager.createQuery("Select a From CourseEntity a where teacher_id = "+teacherEntity.getId().toString(), CourseEntity.class).getResultList();
		if(courses.size()==0){
			manager.remove(teacherEntity);
			return true;
		}else
			return false;		
	}

	public TeacherEntity getTeacher(int id) {
		return manager.find(TeacherEntity.class, id);
	}


}
