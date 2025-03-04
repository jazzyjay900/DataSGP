package LMS;

import java.util.ArrayList;

public class Patron {
	private String name;
	private int libraryCardNum;
	private ArrayList<Books> checkedOutBooks;
	
	//default constructor
	public Patron() {
		name="Jane Doe";
		libraryCardNum = 000000;
		checkedOutBooks = new ArrayList<>();
	}
	//primary constructor
	public Patron(String name,int libaryCardNum,ArrayList<Books>  checkedOutBooks ) {
		this.name = name;
		this.libraryCardNum = libaryCardNum;
		this.checkedOutBooks = checkedOutBooks;
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
	public ArrayList<Books> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	public void setCheckedOutBooks(ArrayList<Books> checkedOutBooks) {
		this.checkedOutBooks = checkedOutBooks;
	}
	//methods
	public void checkOutBook(Books book) {
		if(book.getIsavailable()) {
			book.checkOut();
			checkedOutBooks.add(book);
		}
	}
	
	public void returnBook(Books book) {
		if(checkedOutBooks.remove(book)) {
			book.checkIn();
			System.out.println(name +" returned "+book.getTitle());
		}else {System.out.println(name +" did not check out "+book.getTitle());}
	}
	
	
	
	
	
}
