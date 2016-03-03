package com.school.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table (name="student")
public class StudentEntity extends PersonEntity{
	
	private static final long serialVersionUID = 1L;

	@Transient
	private List<CourseEntity> freeCourses = new ArrayList<CourseEntity>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity=CourseEntity.class)
	private List<CourseEntity> courses = new ArrayList<CourseEntity>();
	
	

	public StudentEntity() {
		super();
	}

	public StudentEntity(int id, String firstName, String lastName, String personalNumber, String email) {
		super(id, firstName, lastName, personalNumber, email);
	}
	
	public StudentEntity(int id, String firstName, String lastName, String personalNumber, String email, List<CourseEntity> freeCourses) {
		super(id, firstName, lastName, personalNumber, email);
		this.freeCourses = freeCourses;
	}

	public List<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseEntity> courses) {
		this.courses = courses;
	}
	
	
	public List<CourseEntity> getFreeCourses() {
		return freeCourses;
	}

	public void setFreeCourses(List<CourseEntity> freeCourses) {
		this.freeCourses = freeCourses;
	}
	
	public void addCourse(CourseEntity courseEntity){
		this.courses.add(courseEntity);
	}

	@Override
	public String toString() {
		return "StudentVO [id=" + super.getId() + ", firstName=" + super.getFirstName()
				+ ", lastName=" + super.getLastName() + ", email=" + super.getEmail()
				+ ", personalNumber=" + super.getPersonalNumber() + "]";
	}
	

}
