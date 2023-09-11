package com.kh.library;

import java.util.ArrayList;

public class LibraryController {
	
	
	
	public void printHumanList() {
		ArrayList<Human> HmList = new LibraryDao().printHumanList();

	}
	
	
	public void printBookList() {
		
		ArrayList<Book> BkList = new LibraryDao().printBookList();
		
		if(BkList.isEmpty()) {
			new LibraryMenu().displayNoData("전체 조회 결과가 없습니다");
		}else {
			new LibraryMenu().displayBookList(BkList);
		}

	}

}
