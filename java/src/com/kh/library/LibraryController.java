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
		
		ArrayList<Book> BkList = new LibraryDao().printBookList();
		
		if(BkList.isEmpty()) {
			new LibraryMenu().displayNoData("전체 도서 조회 결과가 없습니다");
		}else {
			new LibraryMenu().displayBookList(BkList);
		}

	}
	
	public void createBook(String title, String author) {
		Book bk = new Book(title, author);
		int result = new LibraryService().createBook(bk);
		
		if (result > 0) {
			new LibraryMenu().displaySuccess("성공적으로 회원 추가를 완료하였습니다");
		}else
			new LibraryMenu().displayFail("회원 추가에 실패하였습니다");
		
	}

}
