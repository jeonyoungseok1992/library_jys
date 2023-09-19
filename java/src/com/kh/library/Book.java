package com.kh.library;

//Book객체를 만들기위한 클레스
public class Book {
	private int code;
	private String title;
	private String author;
	private int stock ;
	private int isRent ;

	
	
	
	
	public Book(int code, String title, String author, int stock, int isRent ) {
		this.code = code;
		this.title = title;
		this.author = author;
		this.stock = stock;
		this.isRent = isRent;

	}
	
	public Book(String title, String author, int stock ) {
		this.title = title;
		this.author = author;
		this.stock = stock;

	}
	
//	public Book(String title, String author, int code, Boolean isRent) {
//		this.title = title;
//		this.author = author;
//		this.code = code;
//		this.isRent = isRent;
//	}
	
	public Book() {
		
		
	}
	

	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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



	public int getIsRent() {
		return isRent;
	}



	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}



	@Override
	public String toString() {
		return this.code + "\t" + this.title + "\t" + this.author + "  \t  " + this.stock + "       \t" + (this.isRent == 0 ? "대여가능": "대여불가");
	}
}
