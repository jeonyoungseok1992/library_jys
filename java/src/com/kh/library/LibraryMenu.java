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
					lc.printBookList();
					createBook();
					break;
				case 2:
					rentBook();
					break;
				case 3:
					returnBook();
					break;
				case 4:
					//bookList.remove(this.deleteBook());				
					break;
				case 5:
					lc.printHumanList();
					//humanList.add(this.createHuman());
					break;
				case 6:
					//humanList.remove(this.deleteHuman());				
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
	
	public Human deleteHuman() {             //리턴을 객체로 해주면 중간에 끊근게 안됨...조회했는데 다 빌린상태라 삭제할게 없으면?
		Human selectHuman = null;
		boolean isTrue =true;
		while(isTrue) {{
			lc.printHumanList();
			System.out.print("어떤 회원을 삭제하시겠습니까?(id입력) : ");
			try {
			int selectKey = sc.nextInt();			
			sc.nextLine();			
			for (Human human : humanList) {
				if (selectKey == human.getKey()) {
					if (human.getRentBookCode() != 0) {
						System.out.println("책 빌려간 놈임 삭제하기전 받아야함");
						
						
					} else {
						selectHuman = human;	
					}
				}
			}			
		}catch(InputMismatchException x) {
			System.out.println("제대로 된 사람 코드 입력하셈 숫자로");
			sc.nextLine();
	}
		}
		}
		return selectHuman;
	}
	
	public Book deleteBook() {
		Book selectBook = null;
		
		while(selectBook == null) {
			lc.printBookList();
			System.out.println("어떤 책을 삭제하시겠습니까?(도서코드입력) : ");
			
			try {
			int selectCode = sc.nextInt();		
			sc.nextLine();
		
			for(Book book : bookList) {
				if(book.getCode()==selectCode) {
					if(book.getIsRent())
						System.out.println("대여 중이라 삭제 하실 수 없습니다");
						
					else
						selectBook = book;			
				}
			}
			}catch(InputMismatchException x) {
					System.out.println("제대로 된 책 코드 입력하셈 숫자로");
					sc.nextLine();
			}
		}
		return selectBook;
		
		
		
	}

	// 도서 대여를 위한 메서드
	public void rentBook() {
		//************************************************
		//대여가능한 책이 있는지 검사
		boolean isBookCheck = false; // 북을 빌릴 수 있는지 검사 결과값
		boolean isHumanCheck = false; // 북을 빌릴 수 있는 사람이 있는지 검사 결과값
		for (Book b : bookList) {
			if (b.getIsRent()) {
				isBookCheck = true;
				break;
			}
		}
		
		// 북을 빌릴 수 있는 사람이 있는지 검사
		for (Human h : humanList) {
			if (h.getRentBookCode() == 0) {
				isHumanCheck = true;
				break;
			}
		}
		// 없으면 없다고하고 리턴
		// 대여를 할 수 있는 회원이 있는지 검사
		//없으면 없다고하고 리턴
		
		
		if (bookList.size() == 0 || !isBookCheck) {
			System.out.println("도서등록이 필요합니다.");
			return;
		} else if (humanList.size() == 0 || !isHumanCheck) {
			System.out.println("회원등록이 필요합니다.");
			return;
		}
		
		
		// 대여하는 사람을 선택하는 코드
		Human selectHuman = selectHuman();
		
		// 대여할 book을 선택하는 코드
		Book selectBook = selectBook();
		
		//책을 대여해준다.
		// 책에는 isRent상태를 false로 변경
		// 사람은 대여책 코드를 등록시킨다.
		selectHuman.setRentBookCode(selectBook.getCode());
		selectBook.setIsRent(false);
		
	}
	
	//도서를 반납하기위한 메서드
	public void returnBook() {
		//************************************************
		//책을 빌린사람들을 추린다.
		// humanList => 전체검사하면서 책을 대여한 사람만 tmpHumanList 추가 
		ArrayList<Human> tmpHumanList = new ArrayList<>();
		for (Human man : humanList) {
			if (man.getRentBookCode() != 0) {
				tmpHumanList.add(man);
			}
		}
		
		if (tmpHumanList.size() == 0) {
			System.out.println("반납할 책이 없습니다.");
			return;
		}
		//tmpHumanList담긴 사람들을 보여준다.
		//lc.printHumanList(tmpHumanList);
		
		System.out.println("어떤 회원이 반납함?(id입력) : ");
		
		int hk = sc.nextInt();
		sc.nextLine();
		for(Human man : tmpHumanList) {
			if(man.getKey() == hk) {
				for(Book bk : bookList) {
					if(man.getRentBookCode() == bk.getCode()) {
						bk.setIsRent(true);
						System.out.println("반납 완료");
					}
				}
				man.setRentBookCode(0);
				
			}
				
		
		}
	
		//리스트에 있는 사람중 어떤 사람의 책을 반납할지 id를 입력받는다.
		//해당 사람을 selectHuman이라는 변수를 만들어 담아준다.
		//해당 사람이 빌린 책을 rentBookCode를 이용해서 bookList에서 찾아준다.
		//해당 책을 selectBook이라는 변수를 만들어 담아준다.
		
		//selectHuman의 rentBookCode를 0으로 변경
		//selectBook의 isRent를 true로 변경
		//반납이 완료되었습니다. 출력
	}
	//human을 선택해서 반환해주는 메서드
	public Human selectHuman() {
		Human selectHuman = null;
		while(selectHuman == null) {{
			lc.printHumanList();
			System.out.print("어떤 회원으로 대여하시겠습니까?(id입력) : ");
			try {
			int selectKey = sc.nextInt();
			sc.nextLine();
			
			for (Human human : humanList) {
				if (selectKey == human.getKey()) {
					if (human.getRentBookCode() != 0) {
						System.out.println("대여중인 책을 반납 후 이용 부탁드립니다.");
					} else {
						selectHuman = human;	
					}
				}
			}
		}catch(InputMismatchException x) {
			System.out.println("제대로 된 사람 코드 입력하셈 숫자로");
			sc.nextLine();
	}
		}
		}
		return selectHuman;
	}
	
	// 대여할 book을 선택해서 반환해주는 메서드
	public Book selectBook() {
		Book selectBook = null;
		
		while(selectBook == null) {
			lc.printBookList();
			System.out.println("어떤 책을 대여하시겠습니까?(도서코드입력) : ");
			
			try {
			int selectCode = sc.nextInt();		
			sc.nextLine();
		
			for(Book book : bookList) {
				if(book.getCode()==selectCode) {
					if(book.getIsRent())
						selectBook = book;
					else
						System.out.println("이미 대여중인 책입니다");
					//빌리다가 종료하고 싶을 때 방법이 없다???????????????????
//				}else {
//					System.out.println("없는 도서코드를 입력하셨습니다");
//					continue;
				
				}
			}
			}catch(InputMismatchException x) {
					System.out.println("제대로 된 책 코드 입력하셈 숫자로");
					sc.nextLine();
			}
//			for (int i = 0; i < bookList.size(); i++) {
//				Book book = bookList.get(i);
//				if (selectCode == book.getCode()) {
//					if (!book.getIsRent()) {
//						System.out.println("이미 대여중인 책입니다.");
//					} else {
//						selectBook = book;	
//					}
//				}
//			}
		
	
		}
		return selectBook;
	}
	
	
	// bookList의 목록을 보여주는 메서드


	// humanList의 목록을 보여주는 메서드


	// 사용자에 입력에 따라 사람객체를 생성해서 반환한다.
	public Human createHuman() {
		// 입력받기위한 객체
		String name, residentNumber;
		int age, key;
		char gender;
		// 이름, 나이, 주민등록번호, 성별을 입력받아 사람객체 한개를 생성한다.

		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();
		sc.nextLine();
		System.out.print("주민등록번호 앞 6자리를 입력하세요. : ");
		residentNumber = sc.nextLine();
		System.out.print("성별을 입력해주세요.(남 : M, 여자는: F) : ");
		gender = sc.nextLine().toUpperCase().charAt(0);

		System.out.print("고객 고유코드를 입력하세요. : ");
		key = sc.nextInt();
		sc.nextLine();

		Human human = new Human(key, name, residentNumber, age, gender);
		System.out.println(human.toString() + " 생성완료");

		return human;
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
		System.out.print("책 코유코드를 입력하세요. : ");
		code = sc.nextLine();
		

		lc.createBook(title, author, code);
	}
	
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
		
		System.out.println("번호 \t 제목 \t 작가 \t 대여여부");
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
