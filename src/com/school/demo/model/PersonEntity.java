package com.school.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) //JOINED) //SINGLE_TABLE) //TABLE_PER_CLASS)
public abstract class PersonEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.TABLE) //@GeneratedValue( strategy = GenerationType.AUTO )//@GeneratedValue//(strategy = GenerationType.IDENTITY)//@GeneratedValue(strategy=GenerationType.TABLE) 
	private Integer id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String personalNumber;
	@NotEmpty
	private String email;
	
	@Transient
	private String fullName;
	
	public PersonEntity(){
		
	}
	
	public PersonEntity(int id){
		this.id = id;
	}
	
	public PersonEntity(int id,String firstName,String lastName,String personalNumber,String email){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalNumber = personalNumber;
		this.email = email;		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return this.firstName+' '+this.lastName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
}
