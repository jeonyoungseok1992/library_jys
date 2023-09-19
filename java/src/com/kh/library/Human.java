package com.kh.library;


public class Human {
	private int key; 
	private String name;
	private String residentNumber;
	private int age;
	private char gender;
	private String admin;

	
	public Human() {
		
	}
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	Human(String name , String residentNumber, int age, char gender, String admin){
		this.age = age;
		this.name = name;
		this.residentNumber = residentNumber;
		this.gender = gender;
		this.admin = admin;
	}



	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResidentNumber() {
		return residentNumber;
	}

	public void setResidentNumber(String residentNumber) {
		this.residentNumber = residentNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}



	@Override
	public String toString() {
		return this.key + "\t" + this.name + "\t" + 
				this.residentNumber + "\t" + this.age + 
				"\t" + this.gender + "\t" 
				;
	}
}
