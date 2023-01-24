package com.quest.student.interfaces;

import com.quest.student.models.Student;

public interface CrudRepository {
	
	public void add(Student student);
	
	public void update(Student student);
	
	public void delete(int id);
	
	public Student[] getAll();
	
	public Student getById(int id);

}
