package com.quest.student.models;

//Model class Student
public class Student{

	private int id;
	private String name;
	private String address;
	private String bloodGroup;

	public Student() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public void printDetails() {
		
		System.out.println("\n"+this.id+") "+this.name+"\n--*--*--*--");
		System.out.println("Address : "+this.address);
		System.out.println("Blood Group : "+this.bloodGroup+"\n");
		
	}

}
