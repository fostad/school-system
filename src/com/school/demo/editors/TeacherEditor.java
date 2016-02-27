package com.school.demo.editors;

import java.beans.PropertyEditorSupport;

import com.school.demo.model.TeacherEntity;

public class TeacherEditor extends PropertyEditorSupport{

	@Override
    public void setAsText(String id) 
    {
        this.setValue(new TeacherEntity(Integer.valueOf(id)));
    }
}
