package com.kh.library;

import java.util.*;

public class LibraryMenu {
	LibraryController lc = new LibraryController();

	Scanner sc = new Scanner(System.in);


	public void mainMenu() {
		int number = 0;
		while (number != 9) {
			System.out.println("============================");
			System.out.println("1. 도서등록");
			System.out.println("2. 도서대여");
			System.out.println("3. 도서반납");
			System.out.println("4. 도서삭제");
			System.out.println("5. 회원등록");
			System.out.println("6. 회원삭제");
			System.out.println("7. 도서조회");
			System.out.println("8. 회원조회");
			System.out.println("9. 프로그램 종료");
			System.out.println("============================");
			System.out.println("원하시는 서비스 번호를 입력하세요 : ");

			// 원하는 서비스 번호 입력받기
			number = sc.nextInt();
			sc.nextLine();
			switch (number) {
				case 1:
					createBook();
					break;
				case 2:
					rentBook();
					break;
				case 3:
					returnBook();
					break;
				case 4:
					deleteBook();
					break;
				case 5:
					createHuman();
					break;
				case 6:
					deleteHuman();
					break;
				case 7:
					lc.printBookList();			
					break;
				case 8:
					lc.printHumanList();				
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void deleteHuman() {             //리턴을 객체로 해주면 중간에 끊근게 안됨...조회했는데 다 빌린상태라 삭제할게 없으면?
		boolean isTrue = true;
		while(isTrue) {
			lc.printHumanList();
			System.out.println("어떤 회원을 삭제하시겠습니까?(회원코드입력) : ");
			
			try {
			int selectCode = sc.nextInt();		
			sc.nextLine();
			lc.deleteHuman(selectCode);
			isTrue = false;

			}catch(InputMismatchException x) {
					System.out.println("제대로 된 회원 코드(숫자)를 입력하세요");
					sc.nextLine();
			}
		}
	}
	
	public void deleteBook() {
	
		boolean isTrue = true;
		while(isTrue) {
			lc.printBookList();
			System.out.println("어떤 책을 삭제하시겠습니까?(도서코드입력) : ");
			
			try {
			int selectCode = sc.nextInt();		
			sc.nextLine();
			lc.deleteBook(selectCode);
			isTrue = false;

			}catch(InputMismatchException x) {
					System.out.println("제대로 된 책 코드(숫자)를 입력하세요");
					sc.nextLine();
			}
		}
			
	}

	public void rentBook() {
		boolean isHuman = false;
		for(Human hm : lc.allHuman()) {
			if(hm.getRentBookCode()==0) {
				isHuman = true;
				break;
			}
		}
		
		if(!isHuman) {
			System.out.println("대여가능 한 회원이 없습니다");
			return;
		}

		boolean isBook = false;
		for(Book bk : lc.allBook()) {
			if(bk.getIsRent() == 1) {
				isBook = true;
				break;
			}
		}
		if(!isBook) {
			System.out.println("대여가능 한 책이 없습니다");
			return;
		}

		for(Human hm : lc.allHuman()) {
			System.out.println(hm);
		}
				
						
		System.out.print("어떤 회원으로 대여하시겠습니까?(id입력) : ");		
		int selectKey = sc.nextInt();
		sc.nextLine();
		
		for(Book bk : lc.allBook()) {
			System.out.println(bk);
		}
		
		
		System.out.println("어떤 책을 대여하시겠습니까?(도서코드입력) : ");
		int selectCode = sc.nextInt();		
		sc.nextLine();
		
		lc.rentBook(selectKey, selectCode);	
		

		
	}
	

	public void returnBook() {
		
		



	}

	public void createHuman() {
		// 입력받기위한 객체
		String name, residentNumber;
		int age;
		char gender;
		// 이름, 나이, 주민등록번호, 성별을 입력받아 사람객체 한개를 생성한다.

		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();
		System.out.print("주민등록번호 앞 6자리를 입력하세요. : ");
		residentNumber = sc.nextLine();
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();
		sc.nextLine();
		System.out.print("성별을 입력해주세요.(남 : M, 여자는: F) : ");
		gender = sc.nextLine().toUpperCase().charAt(0);


		lc.createHuman(name, residentNumber, age, gender);
	}

	// 사용자에 입력에 따라 책객체를 생성해서 반환한다.
	public void createBook() {
		// 입력받기위한 객체

		String title, author;
		String code;
		// 제목, 작가, 책코드를 입력받는다.

		System.out.print("책 제목을 입력하세요 : ");
		title = sc.nextLine();
		System.out.print("작가를 입력하세요 : ");
		author = sc.nextLine();
//		System.out.print("책 코유코드를 입력하세요. : ");
//		code = sc.nextLine();
		

		lc.createBook(title, author);
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
	
	//-------------------------------응답화면------------------------------------
	
	public void displayNoData(String message) {
		System.out.println(message);
	}
	
	public void displayBookList(ArrayList<Book> BkList) {
		System.out.println("--------------------------------");
		
		System.out.println("번호 \t 제목 \t 작가 \t 대여여부");
		for(Book bk : BkList) {
			System.out.println(bk);	
		}
		System.out.println("--------------------------------");
	}
	
	public void displayHumanList(ArrayList<Human> HmList) {
		System.out.println("--------------------------------");
		
		System.out.println("번호 \t 이름 \t 주민번호\t나이\t성별\t 대여여부");
		for(Human hm : HmList) {
			System.out.println(hm);	
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
