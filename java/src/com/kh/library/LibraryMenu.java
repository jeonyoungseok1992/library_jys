package com.kh.library;

import java.util.*;

public class LibraryMenu {
	
	
	LibraryController lc = new LibraryController();
	
	LibraryService ls = new LibraryService();

	Scanner sc = new Scanner(System.in);


	public void mainMenu() {
		int number = 99;
		while (number != 0) {
			
			String login = (ls.hum == null)? "로그인" : "로그아웃";
			String sign = (ls.hum == null)? "7. 회원가입" : "7. 로그인 중";
		
			System.out.println("========================================================");
			System.out.println("1. 도서조회");
			System.out.println("2. 도서검색");
			System.out.println("3. 도서대여");
			System.out.println("4. 도서반납");
			System.out.println("5. 대여목록");
			System.out.println("6. " +login );
			System.out.println(sign);
			System.out.println("8. 관리자페이지");
			System.out.println("0. 프로그램 종료");
			System.out.println("========================================================");
			System.out.println("원하시는 서비스 번호를 입력하세요 : ");

			boolean validInput = false;
			while (!validInput) {
				try {
					number = sc.nextInt();
					sc.nextLine(); 

					validInput = true; 
				} catch (InputMismatchException e) {
					System.out.println("알맞은 숫자를 입력해주세요");
					sc.nextLine(); 
				}
			}

			switch (number) {
			case 1:
				lc.printBookList();
				break;
			case 2:
				searchBook();
					break;
				case 3:
					if(ls.hum == null) {
						System.out.println("로그인하세요");
						break;
					}
					rentBook();
					break;
				case 4:
					if(ls.hum == null) {
						System.out.println("로그인하세요");
						break;
					}
					returnBook();
					break;
				case 5:
					if(ls.hum == null) {
						System.out.println("로그인하세요");
						break;
					}
					lc.rentBookList();	
					break;
				case 6:
					if("로그인".equals(login))
					login();
					else {
						ls.hum = null;
						System.out.println("로그아웃 되었습니다");
					}
					break;
				case 7:
					createHuman();				
					break;	
				case 8:
					adminMenu();				
					break;				
				case 0:
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
			}
		}
		
	}
	
//	public void memberMenu() {
//		if(ls.hum == null) {
//			System.out.println("로그인하세요");
//			return;
//		}
//
//		int num = 0;
//		boolean tf = true;
//		do {
//			System.out.println("1. 도서대여");
//			System.out.println("2. 도서반납");
//			System.out.println("3. 대여목록");
//			System.out.println("9. 뒤로가기");
//			num = sc.nextInt();
//			switch(num) {
//				case 1:
//					rentBook();
//					break;
//				case 2:
//					returnBook();
//					break;
//				case 3:
//					lc.rentBookList();	
//					break;
//				case 9:
//					tf = false;
//				default:
//					System.out.println("잘 못 입력하셨습니다.");
//					break;
//			}
//			
//		}while(tf);
//	}
	
	public void adminMenu() {
		if(!adminCheck()) {
			System.out.println("관리자 권한이 필요합니다");
			return;
		}
		int num = 0;
		boolean tf = true;
		do {
			System.out.println("1. 도서등록");
			System.out.println("2. 도서삭제");
			System.out.println("3. 회원삭제");
			System.out.println("4. 회원조회");
			System.out.println("5. 전체대여 기록조회");
			System.out.println("9. 뒤로가기");

			boolean validInput = false;
			while (!validInput) {
				try {
					num = sc.nextInt();
					sc.nextLine(); 

					validInput = true; 
				} catch (InputMismatchException e) {
					System.out.println("알맞은 숫자를 입력해주세요");
					sc.nextLine(); 
				}
			}
			switch(num) {
				case 1:
					createBook();
					break;
				case 2:
					deleteBook();
					break;
				case 3:
					deleteHuman();
					break;
				case 4:
					lc.printHumanList();	
					break;
				case 5:
					lc.printRentLog();	
					break;
				case 9:
					tf = false;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
			}
			
		}while(tf);
	}
	
	public void deleteHuman() {   
		
		if(lc.allHuman().isEmpty()) {
			System.out.println("등록 된 회원이 존재하지 않습니다");
			return;
		}

		lc.printHumanList();
		System.out.println("어떤 회원을 삭제하시겠습니까?(회원코드입력) : ");
		while(true) {
		try {
		int selectCode = sc.nextInt();		
		sc.nextLine();
		lc.deleteHuman(selectCode);
		break;
		

		}catch(InputMismatchException x) {
				System.out.println("제대로 된 회원 코드(숫자)를 입력하세요");
				sc.nextLine();
		}
		}
		
	}
	
	public void deleteBook() {
		
		if(lc.allBook().isEmpty()) {
			System.out.println("등록 된 도서가 존재하지 않습니다");
			return;
		}
	
		lc.printBookList();
		
		while(true) {
		try {
		System.out.println("어떤 책을 삭제하시겠습니까?(도서코드입력) : ");
		int selectCode = sc.nextInt();		
		sc.nextLine();
		lc.deleteBook(selectCode);
		break;
	

		}catch(InputMismatchException x) {
				System.out.println("제대로 된 책 코드(숫자)를 입력하세요");
				sc.nextLine();
		}
			}
		
			
	}

	public void rentBook() {
		
//		boolean isHuman = true;
//			if(lc.allHuman().isEmpty()){
//				isHuman = false;			
//			}
//		
//	
//		
//		if(!isHuman) {
//			System.out.println("대여가능 한 회원이 없습니다");
//			return;
//		}

		boolean isBook = false;
		for(Book bk : lc.allBook()) {
			if(bk.getIsRent() == 0) {
				isBook = true;
				break;
			}
		}
		if(!isBook) {
			System.out.println("대여가능 한 책이 없습니다");
			return;
		}

//		for(Human hm : lc.allHuman()) {
//			System.out.println(hm);
//		}
//		while(true) {		
		try {				
//		System.out.print("어떤 회원으로 대여하시겠습니까?  (회원key입력) : ");		
//		int selectKey = sc.nextInt();
//		sc.nextLine();
		System.out.println("========================================================");
		for(Book bk : lc.allBook()) {
			System.out.println(bk);
		}
		System.out.println("========================================================");
		
		System.out.println("어떤 책을 대여하시겠습니까?  (도서코드입력) : ");
		int selectCode = sc.nextInt();		
		sc.nextLine();
		
		lc.rentBook(selectCode);	
		
		}
		catch(Exception e){
			System.out.println("제대로 된 코드(숫자)를 입력하세요");
		
		}

		
	}
	

	public void returnBook() {
//		boolean isRentLog = false;
//		for(RentLog rl : lc.allRentLog()) {
//			if(rl.getRentInOut().equals("대여")) {
//				isRentLog = true;
//				break;
//			}
//		}
//		if(!isRentLog) {
//			System.out.println("책을 대여 중인 회원이 없습니다");
//			return;
//		} 
		
		boolean isBook = false;
		for(Book bk : lc.allrentBook()) {
			if(bk.getIsRent() == 0) {
				isBook = true;
				break;
			}
		}
		if(isBook) {
			System.out.println("대여 중인 도서가 없습니다");
			return;
		}
		
//		for(Human hm : lc.allHuman()) {
//			System.out.println(hm);
//		}
//		while(true) {
		try {
//		System.out.print("어떤 회원으로 반납하시겠습니까?(회원key입력) : ");		
//		int selectKey = sc.nextInt();
//		sc.nextLine();
		System.out.println("========================================================");
		for(Book bk : lc.allrentBook()) {
			System.out.println(bk);
		}
		System.out.println("========================================================");
		
		System.out.println("어떤 책을 반납하시겠습니까?(도서코드입력) : ");
		int selectCode = sc.nextInt();		
		sc.nextLine();
		
		lc.returnBook(selectCode);	
		
		}
		catch(Exception e) {
			System.out.println("제대로 된 회원 코드(숫자)를 입력하세요");
		}
		


	}

	public void createHuman() {
		String name, residentNumber, admin, id, pwd;
		int age;
		char gender;
		while(true) {
		try {
		System.out.println("아이디를 입력하세요 : ");
		id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요 : ");
		pwd = sc.nextLine();
		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();
		System.out.print("주민등록번호 앞 6자리를 입력하세요. : ");
		residentNumber = sc.nextLine();
		System.out.print("성별을 입력해주세요.(남 : M, 여자는: F) : ");
		gender = sc.nextLine().toUpperCase().charAt(0);
		if(gender !='M' && gender!= 'F' ) {
			System.out.println("남 : M, 여자는: F에 맞게 입력해주세요");
			return;
		}
		System.out.println("권한 등급을 입력해주세요");
		admin = sc.nextLine();
		
		lc.createHuman(id, pwd, name, residentNumber,gender, admin);
		break;
		} 
		catch(Exception e){
			System.out.println("타입에 맞는 입력값을 입력해주세요");
			sc.nextLine();
		}
		}
		

		
	}


	public void createBook() {


		String title, author;
		String code;
		int stock;

		while(true) {
		System.out.print("책 제목을 입력하세요 : ");
		title = sc.nextLine();
		System.out.print("작가를 입력하세요 : ");
		author = sc.nextLine();
		try {
		System.out.println("책 개수를 입력하세요");
		stock = sc.nextInt();
		sc.nextLine();
		lc.createBook(title, author, stock);
		break;
		}catch(Exception e) {
			System.out.println("숫자만 입력해주세요");	
			sc.nextLine();
		}
		}
	}
	
	public void login() {
		System.out.println("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요 : ");
		String pwd = sc.nextLine();
		lc.login(id, pwd);
	}
	
	public boolean adminCheck() {
		boolean isAdmin = false;
		
//		if(ls.hum.getAdmin() == null) {
//			System.out.println("로그인 하세요");
//		}
//		login();
//		}
//		if(ls.hum.getAdmin() != null)
//			return isAdmin;
//
		if(ls.hum != null && ls.hum.getAdmin() != null && ls.hum.getAdmin().equals("관리자")){
			isAdmin = true;
		}
		
//		if(lc.hum.getAdmin() != null && lc.hum.getAdmin().equals("관리자")){
//			isAdmin = true;
//		}
		
		return isAdmin;
	}
	
//	public ArrayList<Human> allHuman() {
//
//		return lc.allHuman();
////		if(lc.checkHuman().isEmpty()) {
////			return false;
////		}else	
////			return true;
//	}
//	public ArrayList<Book> allBook() {
//
//		return lc.allBook();
//		if(lc.checkBook().isEmpty()) {
//			return false;
//		}else	
//			return true;
//	}
	
	public void searchBook(){
		String str = null;
		System.out.println("검색어를 입력해주세요 : ");
		str = sc.nextLine();
		lc.searchBook(str);
	}
	
	//-------------------------------응답화면------------------------------------
	
	public void displayNoData(String message) {
		System.out.println(message);
	}
	
	public void displayBookList(ArrayList<Book> BkList) {
		System.out.println("--------------------------------");
		
		System.out.println("번호 \t 제목 \t 작가 \t 대여가능 권 수 \t 대여여부");
		for(Book bk : BkList) {
			System.out.println(bk);	
		}
		System.out.println("--------------------------------");
	}
	
	public void displayHumanList(ArrayList<Human> HmList) {
		System.out.println("--------------------------------");
		
		System.out.println("번호 \t 이름 \t 주민번호\t성별\t권한");
		for(Human hm : HmList) {
			System.out.println(hm);	
		}
		System.out.println("--------------------------------");
	}
	
	public void displayRentLog(ArrayList<RentLog> rlList) {
		System.out.println("--------------------------------");
		
		System.out.println("번호 \t사람key\t사람이름 \t책code\t책이름\t대여여부\t대여날짜\t\t연체료");
		for(RentLog rl : rlList) {
			System.out.println(rl);	
		}
		System.out.println("--------------------------------");
	}
	
	public void displaySuccess(String message) {
		System.out.println(message);
	}
	public void displayFail(String message) {
		System.out.println(message);
	}
}
