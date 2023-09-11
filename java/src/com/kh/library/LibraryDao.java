package com.kh.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

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
						bk.setCode(rset.getString("BK_CODE"));
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
	
	
	public ArrayList<Human> printHumanList() {
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
				
				while(rset.next()) {
					hm = new Human();
					rset.getInt(hm.getKey());
					rset.getString(hm.getName());
					rset.getString(hm.getResidentNumber());
					rset.getInt(hm.getAge());
					
					HmList.add(hm);
				}
			} catch (SQLException e) {
	
				e.printStackTrace();
			
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			} finally {
				LibraryTemplate.close(rset);
				LibraryTemplate.close(stmt);
				LibraryTemplate.close(conn);
			}
		
		return HmList;
		
		
		
	}
	
	
	public int createBook(Connection conn, Book bk) {

		int result = 0;
		
		String sql = "insert into tb_book values(?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt = LibraryTemplate.getConnection().prepareStatement(sql);
			pstmt.setString(1,bk.getTitle());
			pstmt.setString(2,bk.getAuthor());
			pstmt.setString(3,bk.getCode());
			pstmt.setInt(4,bk.getIsRent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			LibraryTemplate.close(pstmt);
		}
		
		return result;
	}

}
