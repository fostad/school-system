package com.school.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.demo.model.CourseEntity;
import com.school.demo.model.StudentEntity;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO{

	//@PersistenceContext
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	
	public List<StudentEntity> getAllStudents() {
		List<StudentEntity> students = manager.createQuery("Select a From StudentEntity a", StudentEntity.class).getResultList();
		return students;
	}

	public void addStudent(StudentEntity studentEntity) {
		manager.persist(studentEntity);
	}
	
	public void deleteStudent(int id){
		StudentEntity studentEntity = manager.find(StudentEntity.class, id);
		manager.remove(studentEntity);
	}
	
	@Transactional
	public void updateStudent(StudentEntity studentEntity){
		
	//	StudentEntity studentEntity = manager.find(StudentEntity.class, id);
		//manager.getTransaction().begin();
		manager.merge(studentEntity);
		manager.flush();
		//manager.getTransaction().commit();
	}
	
	public StudentEntity getStudent(int id){
		return manager.find(StudentEntity.class, id);	
	}

	public List<CourseEntity> getAllCourses() {
		List<CourseEntity> coureses = manager.createQuery("Select a From CourseEntity a", CourseEntity.class).getResultList();
		return coureses;
	}

	public void addCourses(int studentID, List<CourseEntity> courses) {
		StudentEntity studentEntity = manager.find(StudentEntity.class, studentID);
		for(CourseEntity course:courses){
			CourseEntity courseDao = manager.find(CourseEntity.class, course.getId());//TODO remove this
			studentEntity.addCourse(courseDao);
		}
		manager.merge(studentEntity);
		manager.flush();
	}	

}
