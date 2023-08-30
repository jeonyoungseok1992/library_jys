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
	
	public void setIsRent(boolean isRent) {
		this.isRent = isRent;
	}
	
	public int getCode() {
		return this.code;
	}
	
//	public void setCode(int code) {
//		this.code = code;
//	}
	public boolean getIsRent() {
		return this.isRent;
	}
	
	@Override
	public String toString() {
		return this.code + "\t" + this.title + "\t" + this.author + "\t" + (this.isRent ? "대여가능": "대여불가");
	}
}
