package com.school.demo.editors;

import java.beans.PropertyEditorSupport;

import com.school.demo.model.CourseEntity;

public class CourseEditor extends PropertyEditorSupport {
	 @Override
	    public void setAsText(String id) 
	    {
	        this.setValue(new CourseEntity(Integer.valueOf(id)));
	    }
}
