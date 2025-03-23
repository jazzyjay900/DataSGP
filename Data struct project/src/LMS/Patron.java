package LMS;


public class Patron extends User {
	private BookStack checkedOutBooks;
	private BookStack holder;
	
	//default constructor
	public Patron() {
		name="Jane Doe";
		Password = "Admin";
		libraryCardNum = 000000;
		checkedOutBooks = new BookStack();
		holder = new BookStack();
	}
	//primary constructor
	public Patron(String name,String Password,int libaryCardNum) {
		this.name = name;
		this.Password = Password;
		this.libraryCardNum = libaryCardNum;
		checkedOutBooks = new BookStack();
		holder = new BookStack();
	}
	//copy constructor
	public Patron(Patron obj) {
		this.name = obj.name;
		this.libraryCardNum = obj.libraryCardNum;
		 this.checkedOutBooks = new BookStack();
		 this.holder = new BookStack();
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
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
	
	
	public void Undo() {
		holder.Push(checkedOutBooks.Pop());
		holder.getTop().getdata().checkIn();
		
	}
	
	public void Redo() {
		checkedOutBooks.Push(holder.Pop());
		checkedOutBooks.getTop().getdata().checkOut();
	}
	
	
	
	
	public void returnBook() {
		checkedOutBooks.getTop().getdata().checkIn();
		System.out.println(name +" returned "+checkedOutBooks.getTop().getdata().getTitle());
		checkedOutBooks.Pop();
	}
	
	
	
	
	
}
