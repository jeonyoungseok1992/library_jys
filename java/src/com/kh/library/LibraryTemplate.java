package com.kh.library;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class LibraryTemplate {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BOOK","BOOK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
		
	}
	
	//2. (Connection 전달받아서) commit 처리해주는 메서드 
	public static void commit(Connection conn) {
		try {
		if (conn != null && !conn.isClosed()) {
			conn.commit();
		} 
		}catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	//3. (Connection 전달받아서) rollback 처리해주는 메서드 
	public static void rollback(Connection conn) {
		try {
		if (conn != null && !conn.isClosed()) {
			conn.rollback();
		} 
		}catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	//JDBC용 객체들 전달받아서 반납처리해주는 메서드
	
	//4. Statement관련 객체 전달받아서 반납시켜주는 메서드
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed())
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//5. Connection 객체 전달받아서 반납시켜주는 메서드
	public static void close(Connection conn) {    //메서드 이름 달라도 가능(매개변수 달라서 오버로딩 됨)
		try {
			if(conn != null && !conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//6. ResultSet 객체 전달받아서 반납시켜주는 메서드
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
