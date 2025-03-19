package LMS;

import java.util.ArrayList;

public class Patron {
	private String name;
	private int libraryCardNum;
	private BookStack checkedOutBooks;
	
	//default constructor
	public Patron() {
		name="Jane Doe";
		libraryCardNum = 000000;
		checkedOutBooks = new BookStack();
	}
	//primary constructor
	public Patron(String name,int libaryCardNum) {
		this.name = name;
		this.libraryCardNum = libaryCardNum;
		checkedOutBooks = new BookStack();
	}
	//copy constructor
	public Patron(Patron obj) {
		this.name = obj.name;
		this.libraryCardNum = obj.libraryCardNum;
		 this.checkedOutBooks = new BookStack();
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
	public BookStack getCheckedOutBooks() {
		return checkedOutBooks;
	}
	public void setCheckedOutBooks(BookStack checkedOutBooks) {
		this.checkedOutBooks = checkedOutBooks;
	}
	//methods
	public void checkOutBook(Book book) {
		if(book.getIsavailable()) {
			book.checkOut();
			checkedOutBooks.Push(book);
		}else {
			{System.out.println(book.getTitle()+" Is currently unabailable");}
		}
	}
	
	public void returnBook(Book book) {
		checkedOutBooks.Pop();
		book.checkIn();
		System.out.println(name +" returned "+book.getTitle());
	}
	
	
	
	
	
}
