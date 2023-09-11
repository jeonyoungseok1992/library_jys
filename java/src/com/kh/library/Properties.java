package com.kh.library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Properties {
	public static void main(String[] args) {
	
	Properties prop = new Properties();
	
	prop.setProperty("C", "INSERT"); // 게시판 글 작성
	prop.setProperty("R", "SELECT"); // 글 정보 읽기
	prop.setProperty("U", "UPDATE"); // 글 정보 변경
	prop.setProperty("D", "DELETE"); // 글 삭제
	
	try {
		prop.store(new FileOutputStream("resources/driver.properties"), "properties test");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
//	Properties prop = new Properties();
//	try {
//		prop.load(new FileInputStream("resources/driver.properties"));
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	
//	//System.out.println(prop);
//	System.out.println(prop.get("driver"));
//	System.out.println(prop.get("url"));
//	System.out.println(prop.get("username"));
//	System.out.println(prop.get("password"));










	
	}
}
