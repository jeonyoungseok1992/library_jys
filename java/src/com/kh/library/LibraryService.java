package com.kh.library;

import java.sql.Connection;
import java.util.ArrayList;



public class LibraryService {
	
	

//	Human hum = new Human();
	static Human hum = null;
//	Human hum;



	public ArrayList<Human> printHumanList(){
		ArrayList<Human> HmList = new LibraryDao().printHumanList();
		
		return HmList;
		
	}
	
	public ArrayList<Book> printBookList(){
		ArrayList<Book> bkList = new LibraryDao().printBookList();
		
		return bkList;
		
	}
	
	public ArrayList<RentLog> printRentLog(){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<RentLog> rlList = new LibraryDao().allRentLog(conn);
		
		return rlList;
		
	}
	
	public int createBook(Book bk) {
		Connection conn = LibraryTemplate.getConnection();
		int result = new LibraryDao().createBook(conn, bk);
		LibraryTemplate.close(conn);
		if(result > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		
		return result;
	}
	
	public int createHuman(Human human) {
		Connection conn = LibraryTemplate.getConnection();
		int result = new LibraryDao().createHuman(conn, human);
		LibraryTemplate.close(conn);
		if(result > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		return result;
		
	}
	
	public int deleteBook(int selectCode) {
		Connection conn = LibraryTemplate.getConnection();
		int result = new LibraryDao().deleteBook(conn, selectCode);
		LibraryTemplate.close(conn);
		if(result > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		return result;
	}
	
	
	public int deleteHuman(int selectCode) {
		Connection conn = LibraryTemplate.getConnection();
		int result = new LibraryDao().deleteHuman(conn, selectCode);
		LibraryTemplate.close(conn);
		if(result > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		return result;
	}
	
	public int rentBook(int selectCode) {
		Connection conn = LibraryTemplate.getConnection();
		int result = new LibraryDao().rentBook(conn, hum, selectCode);
		LibraryTemplate.close(conn);
		if(result > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		return result;
	}
	
	public int returnBook(int selectCode) {
		Connection conn = LibraryTemplate.getConnection();
		int result3 = new LibraryDao().returnBook(conn, hum, selectCode);
		LibraryTemplate.close(conn);
		if(result3 > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		return result3;
	}
	
	public ArrayList<Human> allHuman(){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<Human> hmList = new LibraryDao().allHuman(conn);
		
		LibraryTemplate.close(conn);
		return hmList;
	}

	public ArrayList<Book> allBook(){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<Book> bkList = new LibraryDao().allBook(conn);
		LibraryTemplate.close(conn);
		return bkList;
	}
	
	public ArrayList<Book> allrentBook(){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<Book> bkList = new LibraryDao().allrentBook(conn, hum);
		LibraryTemplate.close(conn);
		return bkList;
	}
	
	public ArrayList<RentLog> allRentLog(){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<RentLog> rlList = new LibraryDao().allRentLog(conn);
		LibraryTemplate.close(conn);
		return rlList;
	}
	
//	public Human adminCheck() {
//		Connection conn = LibraryTemplate.getConnection();
//		hm = new LibraryDao().adminCheck(conn);
//		
//		LibraryTemplate.close(conn);
//		return hm;
//	}
	
	public Human login(String id, String pwd) {
		Connection conn = LibraryTemplate.getConnection();
		Human hu = new LibraryDao().login(conn ,id, pwd);	
		hum = hu;
		
		LibraryTemplate.close(conn);
		
		return hum;
	}
	
	public ArrayList<Book> searchBook(String str){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<Book> bkList = new LibraryDao().searchBook(conn, str);
		LibraryTemplate.close(conn);
		return bkList;
			
		
	}
	
	public void rentBookList() {
		Connection conn = LibraryTemplate.getConnection();
		//int result = 
		new LibraryDao().rentBookList(conn, hum);
//		LibraryTemplate.close(conn);
//		if(result > 0) {
//			LibraryTemplate.commit(conn);
//		}else
//			LibraryTemplate.rollback(conn);
		

		
	}
}
