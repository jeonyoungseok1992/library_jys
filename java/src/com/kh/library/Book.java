package com.kh.library;

//Book객체를 만들기위한 클레스
public class Book {
	private String title;
	private String author;
	private int code;
	private boolean isRent;
	
	
	
	
	public Book(String title, String author, int code) {
		this.title = title;
		this.author = author;
		this.code = code;
		this.isRent = true;
	}
	
//	public Book(String title, String author, int code, Boolean isRent) {
//		this.title = title;
//		this.author = author;
//		this.code = code;
//		this.isRent = isRent;
//	}
	
	public Book() {
		this.isRent = true;
		
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



	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public boolean getIsRent() {
		return isRent;
	}



	public void setIsRent(boolean isRent) {
		this.isRent = isRent;
	}



	@Override
	public String toString() {
		return this.code + "\t" + this.title + "\t" + this.author + "\t" + (this.isRent ? "대여가능": "대여불가");
	}
}
