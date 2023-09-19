package com.kh.library;

import java.sql.Connection;
import java.util.ArrayList;



public class LibraryService {
	

	
	public ArrayList<Human> printHumanList(){
		ArrayList<Human> HmList = new LibraryDao().printHumanList();
		
		return HmList;
		
	}
	
	public ArrayList<Book> printBookList(){
		ArrayList<Book> bkList = new LibraryDao().printBookList();
		
		return bkList;
		
	}
	
	public ArrayList<RentLog> printRentLog(){
		ArrayList<RentLog> rlList = new LibraryDao().printRentLog();
		
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
	
	public int rentBook(int selectKey, int selectCode) {
		Connection conn = LibraryTemplate.getConnection();
		int result = new LibraryDao().rentBook(conn, selectKey, selectCode);
		LibraryTemplate.close(conn);
		if(result > 0) {
			LibraryTemplate.commit(conn);
		}else
			LibraryTemplate.rollback(conn);
		return result;
	}
	
	public int returnBook(int selectKey, int selectCode) {
		Connection conn = LibraryTemplate.getConnection();
		int result3 = new LibraryDao().returnBook(conn, selectKey, selectCode);
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
	
	public ArrayList<RentLog> allRentLog(){
		Connection conn = LibraryTemplate.getConnection();
		ArrayList<RentLog> rlList = new LibraryDao().allRentLog(conn);
		LibraryTemplate.close(conn);
		return rlList;
	}
}
