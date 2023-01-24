package com.quest.student.repos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.quest.student.interfaces.CrudRepository;
import com.quest.student.models.Student;

public class StudentArrayListRepository implements CrudRepository {
	
	ArrayList<Student> students;
	
	public StudentArrayListRepository() {
		
		this.students = new ArrayList<>();
		
	}

	@Override
	public void add(Student student) {		
		this.students.add(student);
	}

	@Override
	public void update(Student student) {
		
		int existingStudentIndex = -1;
		
		for(Student existingStudent : this.students) {
			
			if(existingStudent.getId() == student.getId()) {
				
				existingStudentIndex = this.students.indexOf(existingStudent);
				
			}
			
		}
		
		this.students.set(existingStudentIndex,student);
		
	}

	@Override
	public void delete(int id) {
		
		for(Student student : this.students) {
			
			if(student.getId() == id) {
				
				this.students.remove(student);
				
			}
			
		}
		
		
	}

	@Override
	public Student[] getAll() {	
		return this.students.toArray(new Student[this.students.size()]);
	}

	@Override
	public Student getById(int id) {
				
		for(Student student : this.students) {
			
			if(student.getId() == id) return student;
			
		}
		
		//USING ITERATOR
//		Iterator<Student> studentIterator = this.students.iterator();
//		
//		while(studentIterator.hasNext()) {
//			
//			Student student = studentIterator.next();
//			if(student.getId() == id) return student;
//			
//		}		
		
		return null;
	}

	
	//TODO NOT USED FOR HERE
	public void setLength(int length) {
		System.out.println("Arraylist doesn't require setting length!");
	}

	public int getPosition() {
		System.out.println("Arraylist doesn't have a cursor!");
		return -1;
	}

	public int getLength() {
		return this.students.size();
	}

}
