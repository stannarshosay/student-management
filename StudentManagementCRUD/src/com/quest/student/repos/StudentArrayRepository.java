package com.quest.student.repos;

import com.quest.student.interfaces.CrudRepository;
import com.quest.student.models.Student;

public class StudentArrayRepository implements CrudRepository {
	
	private Student students[];
	private int length;
	private int index;
	
	public StudentArrayRepository() {
	}
	
	public int getPosition() {
		return this.index + 1;
	}

	public void setLength(int length) {
		this.length = length;
		this.initStudents();
	}
	
	public int getLength() {
		return length;
	}
	
	private void initStudents() {
		this.students = new Student[length];
		this.index = 0;
	}

	@Override
	public void add(Student student) {
		
		this.students[this.index] = student;
		++this.index;
		
	}

	@Override
	public void update(Student student) {
		
		int existingStudentIndex = -1;
		
		for (int i = 0; i < this.students.length; i++) {
			
			Student existingStudent = this.students[i];
			
			if(existingStudent.getId() == student.getId()) {
				
				existingStudentIndex = i;
				break;
			}
			
		}
		
		this.students[existingStudentIndex] = student;
		
	}

	@Override
	public void delete(int id) {
		
		Student oldStudents[] = this.students;	
		
		this.initStudents();
		
		for(Student student : oldStudents) {
			
			if(student == null) break;
			
			if(student.getId() == id) continue;
			
			this.add(student);
			
		}		
		
	}

	@Override
	public Student[] getAll() {
		return this.students;
	}

	@Override
	public Student getById(int id) {
		
		for(Student student : this.students) {
			
			if(student == null) break;
			
			if(student.getId() == id) return student;
			
		}
		
		return null;
	}

}
