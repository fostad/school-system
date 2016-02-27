package com.school.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="student")
public class StudentEntity extends PersonEntity{
	
	private static final long serialVersionUID = 1L;

	@OneToMany( targetEntity=CourseEntity.class )
	private List<CourseEntity> courses = new ArrayList<CourseEntity>();

	public StudentEntity() {
		super();
	}

	public StudentEntity(int id, String firstName, String lastName, String personalNumber, String email) {
		super(id, firstName, lastName, personalNumber, email);
	}

	public List<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseEntity> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		return "StudentVO [id=" + super.getId() + ", firstName=" + super.getFirstName()
				+ ", lastName=" + super.getLastName() + ", email=" + super.getEmail()
				+ ", personalNumber=" + super.getPersonalNumber() + "]";
	}
	

}
