package com.kh.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
						bk.setTitle(rset.getString("bk_title"));
						bk.setAuthor(rset.getString("bk_author"));
						bk.setCode(rset.getInt("bk_code"));
						bk.setStock(rset.getInt("bk_stock"));
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
					hm.setGender(rset.getString("hm_gender").charAt(0));
					hm.setAdmin(rset.getString("hm_admin"));
					

					
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
	
	
	public ArrayList<RentLog> printRentLog(){
		
		ArrayList<RentLog> rlList = new ArrayList<>();
		
		ResultSet rset = null;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "SELECT * FROM TB_RentLog";
		

			
			try {
			
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BOOK","BOOK");
					stmt = conn.createStatement();
					rset = stmt.executeQuery(sql);
					
			
					while(rset.next()) {
						RentLog rl = new RentLog();
						rl.setLogNo(rset.getInt("LOG_NO"));
						rl.setHm_rentKey(rset.getInt("HM_RENTKEY"));
						rl.setBk_rentCode(rset.getInt("BK_RENTCODE"));
						rl.setRentInOut(rset.getString("RENT_INOUT"));
						String strdate = rset.getDate("enrolldate").toGMTString();
						rl.setEnrollDate(strdate);
						
						rlList.add(rl);
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
			
			
			return rlList;
		
				
	}
	
	

	
	
	public int createBook(Connection conn, Book bk) {

		int result = 0;
		
		String sql = "insert into tb_book values(SEQ_BOOK.NEXTVAL,?,?,?,default)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1,bk.getTitle());
			pstmt.setString(2,bk.getAuthor());
			pstmt.setInt(3,bk.getStock());
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
		String sql = "insert into tb_human values(seq_human.nextval,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, human.getId());
			pstmt.setString(2, human.getPwd());
			pstmt.setString(3, human.getName());
			pstmt.setString(4, human.getResidentNumber());
			pstmt.setInt(5, human.getAge());
			pstmt.setString(6, String.valueOf(human.getGender()));
			pstmt.setString(7, human.getAdmin());
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
	
//	public int rentBook(Connection conn, int selectKey, int selectCode) {
//		int result1 = 0;
//		int result2 = 0;
//		int result3 = result1 + result2;
//		PreparedStatement pstmt = null;
//		PreparedStatement pstmt2 = null;
//		String sql = "UPDATE tb_human set hm_rentbookcode = ? where hm_key = ?";
//		String sql2 = "UPDATE tb_book set BK_ISRENT = 0 where bk_code = ?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, selectCode);
//			pstmt.setInt(2, selectKey);
//			result1 = pstmt.executeUpdate();
//			
//			pstmt2 = conn.prepareStatement(sql2);
//			pstmt2.setInt(1, selectCode);
//			result2 = pstmt2.executeUpdate();
//			result3 = result1 + result2;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			LibraryTemplate.close(pstmt);
//			LibraryTemplate.close(pstmt2);
//		}
//		
//		return result3;
//	
//	}
	
	
	public int rentBook(Connection conn, int selectKey, int selectCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TB_RENTLOG VALUES(SEQ_RENTLOG.nextval,?,?,'대여',default)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectKey);
			pstmt.setInt(2, selectCode);
			result = pstmt.executeUpdate();
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);

		}
		
		return result;
	
	}
	

	
	public int returnBook(Connection conn, int selectKey, int selectCode) {
		int result = 0;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "INSERT INTO TB_RENTLOG VALUES(SEQ_RENTLOG.nextval,?,?,'반납',default)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectKey);
			pstmt.setInt(2, selectCode);
			result = pstmt.executeUpdate();
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);

		}
		
		return result;
	
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
				hm.setGender(rset.getString("hm_gender").charAt(0));
				
				
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
				bk.setStock(rset.getInt("bk_stock"));
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
	
	public ArrayList<RentLog> allRentLog(Connection conn){
		ArrayList<RentLog> rlList = new ArrayList<>();
		RentLog rl;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from tb_rentlog";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rl = new RentLog();
				rl.setLogNo(rset.getInt("LOG_NO"));
				rl.setHm_rentKey(rset.getInt("HM_RENTKEY"));
				rl.setBk_rentCode(rset.getInt("BK_RENTCODE"));
				rl.setRentInOut(rset.getString("RENT_INOUT"));
				String strdate = rset.getDate("ENROLLDATE").toString();
				rl.setEnrollDate(strdate);
			
				
				rlList.add(rl);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		
		return rlList;
	}
	
//	public Human adminCheck() {
//		Human hm = new Human();
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String sql = "select";			
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()) {
//				rl = new RentLog();
//				rl.setLogNo(rset.getInt("LOG_NO"));
//				rl.setHm_rentKey(rset.getInt("HM_RENTKEY"));
//				rl.setBk_rentCode(rset.getInt("BK_RENTCODE"));
//				rl.setRentInOut(rset.getString("RENT_INOUT"));
//				String strdate = rset.getDate("ENROLLDATE").toString();
//				rl.setEnrollDate(strdate);
//			
//				
//				rlList.add(rl);
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			LibraryTemplate.close(pstmt);
//		}
//		
//		return rlList;
//		
//	}
	
	public Human login(Connection conn, String id, String pwd) {
		Human hm = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from tb_human where hm_id = ? and hm_pwd = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				hm = new Human();
//				hm.setId(rset.getString("hm_id"));
//				hm.setPwd(rset.getString("hm_pwd"));		
				hm.setAdmin(rset.getString("hm_admin"));	

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LibraryTemplate.close(pstmt);
		}
		
		return hm;
	}
	
	
	
	
	
	
	
	
}
