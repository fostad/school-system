package com.school.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="course")
public class CourseEntity implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id
    @GeneratedValue
	private Integer id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String points;
	
	@OneToOne
	//@NotNull
	private TeacherEntity teacher;

	public CourseEntity() {
	}
	 
	public CourseEntity(Integer id) {
    	super();
    	this.id = id;
    }
	
	public CourseEntity(Integer id,String name, String points) {
    	super();
    	this.id = id;
    	this.name = name;
    	this.points = points;
    }
	
    public CourseEntity(Integer id,String name, String points, TeacherEntity teacherEntity) {
    	super();
    	this.id = id;
        this.name = name;
        this.points = points;
        this.teacher = teacherEntity;
    }
     
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	
	
	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "CourseVO [id=" + id + ", name=" + name
				+ ", points=" + points + ", teacher=" + teacher + "]";
	}
}
