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
						bk.setCode(rset.getInt("BK_CODE"));
						bk.setIsRent(rset.getInt("bk_isrent"));
						
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
					hm.setKey(rset.getInt("HM_KEY"));
					hm.setName(rset.getString("hm_name"));
					hm.setResidentNumber(rset.getString("hm_rnumber"));
					hm.setAge(rset.getInt("hm_age"));
					String str = rset.getString("hm_gender");
					char gender = str.charAt(0);
					hm.setGender(gender);
					hm.setRentBookCode(rset.getInt("HM_RENTBOOKCODE"));

					
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
		
		String sql = "insert into tb_book values(SEQ_BOOK.NEXTVAL,?,?,default)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1,bk.getTitle());
			pstmt.setString(2,bk.getAuthor());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			LibraryTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int createHuman(Connection conn, Human human) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into tb_human values(seq_human.nextval,?,?,?,?,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, human.getName());
			pstmt.setString(2, human.getResidentNumber());
			pstmt.setInt(3, human.getAge());
			pstmt.setString(4, String.valueOf(human.getGender()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBook(Connection conn, int selectCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from tb_book where bk_code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		return result;
		
		
	}
	
	
	public int deleteHuman(Connection conn, int selectCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from tb_human where hm_key = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		return result;
		
		
	}
	
	public int rentBook(Connection conn, int selectKey, int selectCode) {
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "UPDATE tb_human set hm_rentbookcode = ? where hm_key = ?";
		String sql2 = "UPDATE tb_book set BK_ISRENT = 0 where bk_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectCode);
			pstmt.setInt(2, selectKey);
			result1 = pstmt.executeUpdate();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, selectCode);
			result2 = pstmt2.executeUpdate();
			
			result3 = result1 + result2;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
			LibraryTemplate.close(pstmt2);
		}
		
		return result3;
	
	}
	
//	public int rentBook(Connection conn, int selectKey, int selectCode) {
//		int result1 = 0;
//
//		PreparedStatement pstmt = null;
//		String sql = "CREATE TRIGGER update_book_isrent_trigger"
//				+ "AFTER UPDATE ON tb_book FOR EACH ROW"
//				+ "BEGIN"
//				+ "    IF NEW.bk_isrent = 0"
//				+ "      then UPDATE tb_human SET hm_rentbookcode = CASE WHEN new. = 0 THEN 1 ELSE 0 END WHERE bk_code = NEW.hm_rentbookcode;\r\n"
//				+ "    END IF;\r\n"
//				+ "END";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, selectCode);
//			pstmt.setInt(2, selectKey);
//			result1 = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			LibraryTemplate.close(pstmt);
//		}
//		
//		return result1;	
//	}
	
	public int returnBook(Connection conn, int selectKey, int selectCode) {
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "UPDATE tb_human set hm_rentbookcode = 0 where hm_key = ?";
		String sql2 = "UPDATE tb_book set BK_ISRENT = 1 where bk_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectKey);
			result1 = pstmt.executeUpdate();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, selectCode);
			result2 = pstmt2.executeUpdate();
			result3 = result1 + result2;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
			LibraryTemplate.close(pstmt2);
		}
		
		return result3;
	
	}
	
	public ArrayList<Human> allHuman(Connection conn){
		ArrayList<Human> HmList = new ArrayList<>();
		Human hm;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from tb_human";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				hm = new Human();
				hm.setKey(rset.getInt("hm_key"));
				hm.setName(rset.getString("hm_name"));
				hm.setResidentNumber(rset.getString("hm_rnumber"));
				hm.setAge(rset.getInt("hm_age"));
				String str = rset.getString("hm_age");
				char gender = str.charAt(0);
				hm.setGender(gender);
				hm.setRentBookCode(rset.getInt("hm_rentbookcode"));
				
				HmList.add(hm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		
		return HmList;
	}
	

	public ArrayList<Book> allBook(Connection conn){
		ArrayList<Book> bkList = new ArrayList<>();
		Book bk;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from tb_book";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bk = new Book();
				bk.setTitle(rset.getString("bk_title"));
				bk.setAuthor(rset.getString("bk_author"));
				bk.setCode(rset.getInt("bk_code"));
				bk.setIsRent(rset.getInt("bk_isrent"));
				
				bkList.add(bk);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		
		return bkList;
	}
	
	
	
	
	
	
	
	
	
}
