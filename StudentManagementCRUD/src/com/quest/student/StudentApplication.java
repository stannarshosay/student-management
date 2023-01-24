package com.quest.student;

import java.util.Scanner;

import com.quest.student.models.Student;
import com.quest.student.repos.StudentArrayRepository;

public class StudentApplication {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int option;
		
		StudentArrayRepository repo = new StudentArrayRepository();

		if(repo instanceof StudentArrayRepository) {
			
			System.out.println("Student Management\n=================");
			System.out.println("Enter the number of records");	
			
			repo.setLength(scanner.nextInt());
		}
		
		while(true) {
			
			System.out.println("\nSELECT AN OPTION\n=================");
			System.out.println("1) Add a student");
			System.out.println("2) List a student");
			System.out.println("3) List all students");
			System.out.println("4) Update a student");
			System.out.println("5) Delete a student");
			System.out.println("*) Quit");
			
			option = scanner.nextInt();
			scanner.nextLine();
			
			switch(option) {
				case 1:{
					
					if(repo instanceof StudentArrayRepository) {
						
						if(repo.getPosition() > repo.getLength()) {
							System.out.println("Oops! No more records can be added (Length : "+repo.getLength()+")");
							break;
						}
						
						System.out.println("Adding Student to Position : "+repo.getPosition()+"/"+repo.getLength());
						
					}
					
					System.out.println("Enter Name : ");
					String name = scanner.nextLine();
					System.out.println("Enter Id : ");
					int id = scanner.nextInt();
					
					//skip line after nextInt
					scanner.nextLine();
					
					System.out.println("Enter Address : ");
					String address = scanner.nextLine();
					System.out.println("Enter Blood Group : ");
					String bloodGroup = scanner.nextLine();
					
					Student student = new Student();
					student.setId(id);
					student.setName(name);
					student.setAddress(address);
					student.setBloodGroup(bloodGroup);
					
					repo.add(student);
					
					break;
				}
				case 2:{
					
					System.out.println("Enter the id of Student : ");
					int id = scanner.nextInt();
					
					Student student = repo.getById(id);
					
					if(student == null) {
						
						System.out.println("Oops! No such record found");
						
					}else {
						
						student.printDetails();
						
					}
					
					break;
				}
				case 3:{
					
					System.out.println("All Students\n-------------");
					
					Student students[] = repo.getAll();
					
					for(Student student:students) {
						
						if(student == null) break;						
						student.printDetails();
						
					}
					
					break;
				}
				case 4:{
					
					System.out.println("Enter the id of Student : ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					Student student = repo.getById(id);
					
					if(student == null) {
						
						System.out.println("Oops! No such record found");
						
					} else {
						
						student.printDetails();
						
						System.out.println("\nEnter the new name : ");
						String name = scanner.nextLine();
						System.out.println("Enter the new address : ");
						String address = scanner.nextLine();
						System.out.println("Enter the new blood group : ");
						String bloodGroup = scanner.nextLine();
						
						Student newStudent = new Student();
						newStudent.setId(student.getId());
						newStudent.setName(name);
						newStudent.setAddress(address);
						newStudent.setBloodGroup(bloodGroup);
						
						repo.update(newStudent);
						
					}
					
					break;
				}
				case 5:{
					
					System.out.println("Enter the id of Student : ");
					int id = scanner.nextInt();
					
					Student student = repo.getById(id);
					
					if(student == null) {
						
						System.out.println("Oops! No such record found");
						
					}else {
						
						System.out.println("The student with below details have been deleted :");						
						student.printDetails();
						
						repo.delete(student.getId());
						
					}
					
					
					break;
				}
			}
			
		}


	}

}
