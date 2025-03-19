package LMS;

public class Book {
	private String title;
	private String author;
	private int ISBN;
	private boolean Isavailable;
	//default constructor
	public Book() {
		title ="";
		author = "";
		ISBN = 0000000000000;
		Isavailable = false;
	}
	//primary constructor
	public Book(String title,String author,int ISBN, boolean Isavailable){
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.Isavailable = Isavailable;
	}
	//copy constructor
	public Book(Book obj) {
		this.title = obj.title;
		this.author = obj.author;
		this.ISBN = obj.ISBN;
		this.Isavailable = obj.Isavailable;
	}
	//getters and setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public boolean getIsavailable() {
		return Isavailable;
	}
	public void setIsavailable(boolean Isavailable) {
		this.Isavailable = Isavailable;
	}
	
	//book check-in and check-out methods
	public void checkIn() {
		Isavailable = true;
	}
	public void checkOut() {
		Isavailable = false;
	}
	//toString
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", availability=" + Isavailable
				+ "]";
	}
	
}
