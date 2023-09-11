package com.kh.library;

// Human객체를 만들기위한 클레스
public class Human {
	private int key; // 사람을 식별할 수 있는 key
	private String name;
	private String residentNumber;
	private int age;
	
	public Human() {
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private char gender;
	private int rentBookCode;
	
	Human(int key, String name , String residentNumber, int age, char gender){
		this.age = age;
		this.name = name;
		this.residentNumber = residentNumber;
		this.gender = gender;
		this.key = key;
		this.rentBookCode = 0;
	}
	
	public String getResidentNumber() {
		return residentNumber;
	}

	public void setResidentNumber(String residentNumber) {
		this.residentNumber = residentNumber;
	}

	public String getName() {
		return this.name;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public int getRentBookCode() {
		return this.rentBookCode;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRentBookCode(int rentBookCode) {
		this.rentBookCode = rentBookCode;
	}
	
	@Override
	public String toString() {
		return this.key + "\t" + this.name + "\t" + 
				this.residentNumber + "\t" + this.age + 
				"\t" + this.gender + "\t" + 
				(this.rentBookCode == 0 ? "도서 대여 가능" : (this.rentBookCode + "번 대여중"));
	}
}
