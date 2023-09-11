package com.kh.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibraryDao {
	
	public ArrayList<Book> printBookList(){
		
		ArrayList<Book> BkList = new ArrayList<>();
		
		ResultSet rset = null;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "SELECT * FROM TB_BOOK";
		

			
			try {
			
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BOOK","BOOK");
					stmt = conn.createStatement();
					rset = stmt.executeQuery(sql);
					
			
					while(rset.next()) {
						Book bk = new Book();
						bk.setTitle(rset.getString("BK_TITLE"));
						bk.setAuthor(rset.getString("BK_AUTHOR"));
						bk.setCode(rset.getInt("BK_CODE"));
						//bk.setIsRent(rset.getBoolean("bk_isrent"));
						
						BkList.add(bk);
					}
					
				
			}		
			 catch (ClassNotFoundException e) {
					e.printStackTrace();
			}
				
			catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rset.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
			
			
			return BkList;

		
				
				
				
				
				
				
	}
	
	
	public void printHumanList() {
		ArrayList<Human> HmList = new ArrayList<>();
		
		Human hm = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "select * from tb_human";
	
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BOOK","BOOK");
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql);
			} catch (SQLException e) {
	
				e.printStackTrace();
			
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			}
		
		
		
	}

}
