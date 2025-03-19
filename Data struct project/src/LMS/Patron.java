package LMS;

import java.util.ArrayList;

public class Patron {
	private String name;
	private int libraryCardNum;
	private ArrayList<Book> checkedOutBooks;
	
	//default constructor
	public Patron() {
		name="Jane Doe";
		libraryCardNum = 000000;
		checkedOutBooks = new ArrayList<>();
	}
	//primary constructor
	public Patron(String name,int libaryCardNum) {
		this.name = name;
		this.libraryCardNum = libaryCardNum;
		checkedOutBooks = new ArrayList<>();
	}
	//copy constructor
	public Patron(Patron obj) {
		this.name = obj.name;
		this.libraryCardNum = obj.libraryCardNum;
		 this.checkedOutBooks = new ArrayList<>(obj.checkedOutBooks);
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLibaryCardNum() {
		return libraryCardNum;
	}
	public void setLibaryCardNum(int libaryCardNum) {
		this.libraryCardNum = libaryCardNum;
	}
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	//methods
	public void checkOutBook(Book book) {
		if(book.getIsavailable()) {
			book.checkOut();
			checkedOutBooks.add(book);
		}else {
			{System.out.println(book.getTitle()+" Is currently unabailable");}
		}
	}
	
	public void returnBook(Book book) {
		if(checkedOutBooks.contains(book)) {
			book.checkIn();
			System.out.println(name +" returned "+book.getTitle());
		}else {System.out.println(name +" did not check out "+book.getTitle());}
	}
	
	
	
	
	
}
