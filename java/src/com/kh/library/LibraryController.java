package com.kh.library;

import java.util.ArrayList;

public class LibraryController {
	
	
	
	public void printHumanList() {
		ArrayList<Human> HmList = new LibraryService().printHumanList();
		if(HmList.isEmpty()) {
			new LibraryMenu().displayNoData("전체 회원 조회 결과가 없습니다");
		}else
			new LibraryMenu().displayHumanList(HmList);

	}
	
	
	public void printBookList() {
		
		ArrayList<Book> BkList = new LibraryService().printBookList();
		
		if(BkList.isEmpty()) {
			new LibraryMenu().displayNoData("전체 도서 조회 결과가 없습니다");
		}else {
			new LibraryMenu().displayBookList(BkList);
		}

	}
	
	public void printRentLog() {
		
		ArrayList<RentLog> rlList = new LibraryService().printRentLog();
		
		if(rlList.isEmpty()) {
			new LibraryMenu().displayNoData("전체 대여 기록이 없습니다");
		}else {
			new LibraryMenu().displayRentLog(rlList);
		}

	}
	
	public void createBook(String title, String author, int stock) {
		Book bk = new Book(title, author, stock);
		int result = new LibraryService().createBook(bk);
		
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 도서 추가를 완료하였습니다");
		}else
			new LibraryMenu().displayFail("도서 추가에 실패하였습니다");
		
	}
	
	public void createHuman(String name, String residentNumber, int age, char gender, String admin) {
		Human human = new Human(name, residentNumber, age, gender, admin);
		int result = new LibraryService().createHuman(human);
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 회원 추가를 완료하였습니다");
		}else
			new LibraryMenu().displayFail("회원 추가에 실패하였습니다");
	}
	
	public void deleteBook(int selectCode) {
		int result = new LibraryService().deleteBook(selectCode);
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 도서 삭제를 완료하였습니다");
		}else
			new LibraryMenu().displayFail("도서 삭제에 실패하였습니다");
	}
	
	
	public void deleteHuman(int selectCode) {
		int result = new LibraryService().deleteHuman(selectCode);
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 회원 삭제를 완료하였습니다");
		}else
			new LibraryMenu().displayFail("회원 삭제에 실패하였습니다");
	}
	
	public void rentBook(int selectKey, int selectCode ) {
		int result = new LibraryService().rentBook(selectKey, selectCode);
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 도서 대여를 완료하였습니다");
		}else
			new LibraryMenu().displayFail("도서 대여에 실패하였습니다");

	}
	
	public void returnBook(int selectKey, int selectCode ) {
		int result = new LibraryService().returnBook(selectKey, selectCode);
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 도서 반납을 완료하였습니다");
		}else
			new LibraryMenu().displayFail("도서 반납에 실패하였습니다");

	}
	
	public ArrayList<Human> allHuman(){
		ArrayList<Human> hmList = new LibraryService().allHuman();
		return hmList;
	}
	
	public ArrayList<Book> allBook(){
		ArrayList<Book> bkList = new LibraryService().allBook();
		return bkList;
	}
	
	public ArrayList<RentLog> allRentLog(){
		ArrayList<RentLog> rlList = new LibraryService().allRentLog();
		return rlList;
	}
}
