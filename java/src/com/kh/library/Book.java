package com.kh.library;

//Book객체를 만들기위한 클레스
public class Book {
	private String title;
	private String author;
	private String code;
	private int isRent = 1;
	
	
	
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;

	}
	
//	public Book(String title, String author, int code, Boolean isRent) {
//		this.title = title;
//		this.author = author;
//		this.code = code;
//		this.isRent = isRent;
//	}
	
	public Book() {
		
		
	}
	

	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public int getIsRent() {
		return isRent;
	}



	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}



	@Override
	public String toString() {
		return this.code + "\t" + this.title + "\t" + this.author + "\t" + (this.isRent == 1 ? "대여가능": "대여불가");
	}
}
