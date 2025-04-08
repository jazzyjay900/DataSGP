package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class Book {
    private String title;
    private String author;
    private String ISBN; // Changed from int to String
    private boolean isAvailable;
    
    // Default constructor
    public Book() {
        title = "";
        author = "";
        ISBN = "";
        isAvailable = false;
    }
    
    // Primary constructor
    public Book(String title, String author, String ISBN, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = isAvailable;
    }
    
    // Copy constructor
    public Book(Book obj) {
        this.title = obj.title;
        this.author = obj.author;
        this.ISBN = obj.ISBN;
        this.isAvailable = obj.isAvailable;
    }
    
    // Getters and setters
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
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public boolean getIsavailable() {
        return isAvailable;
    }
    
    public void setIsavailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    // Book check-in and check-out methods
    public void checkIn() {
        isAvailable = true;
    }
    
    public void checkOut() {
        isAvailable = false;
    }
    
    // toString
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", availability=" + isAvailable + "]";
    }
}