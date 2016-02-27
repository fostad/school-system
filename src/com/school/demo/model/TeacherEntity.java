package com.school.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table (name="teacher")
public class TeacherEntity extends PersonEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2730010001761399055L;

	public TeacherEntity(){
		
	}
	
	public TeacherEntity(int id){
		super(id);
	}
	
	public TeacherEntity(int id, String firstName,String lastName,String personalNumber,
						 String email){
		super(id, firstName, lastName, personalNumber, email);
	}


	@Override
	public String toString() {
		return "TeacherVO [id=" + super.getId() + ", firstName=" + super.getFirstName()
				+ ", lastName=" + super.getLastName() + ", email=" + super.getEmail()
				+ ", personalNumber=" + super.getPersonalNumber() + "]";
	}

}
