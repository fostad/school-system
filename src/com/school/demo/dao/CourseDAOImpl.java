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
public class CourseDAOImpl implements CourseDAO{
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<CourseEntity> getAllCourses() 
	{
		List<CourseEntity> courses = manager.createQuery("Select a From CourseEntity a", CourseEntity.class).getResultList();
        return courses;
	}
	
	public void addCourse(CourseEntity course) 
	{
		//Use null checks and handle them
		manager.persist(course);
	}

	public List<TeacherEntity> getAllTeachers() {
		List<TeacherEntity> teachers = manager.createQuery("Select a From TeacherEntity a",TeacherEntity.class).getResultList();
		return teachers;
	}

	public void updateCourse(CourseEntity course) {
		manager.merge(course);
		manager.flush();
	}

	public void deleteCourse(int id) {
		CourseEntity courseEntity = manager.find(CourseEntity.class, id);
		manager.remove(courseEntity);
	}

	public CourseEntity getCourse(int id) {
		return manager.find(CourseEntity.class, id);
	}

	

}
