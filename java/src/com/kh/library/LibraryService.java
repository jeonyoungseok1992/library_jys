package com.kh.library;

import java.sql.Connection;
import java.util.ArrayList;



public class LibraryService {
	
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
	
	public ArrayList<Human> printHumanList(){
		ArrayList<Human> HmList = new LibraryDao().printHumanList();
		
		return HmList;
		
	}

}
