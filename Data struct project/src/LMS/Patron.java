package LMS;

import java.util.ArrayList;

public class Patron extends User {
    private BookStack checkedOutBooks;
    private BookStack holder;
    private String salt; // for salt hash
    private String hashedPassword; // Ahashed password
    
    // Default constructor
    public Patron() {
        name = "Jane Doe";
        Password = "Admin";
        libraryCardNum = 000000;
        checkedOutBooks = new BookStack();
        holder = new BookStack();
        salt = "";
        hashedPassword = "";
    }
    
    // Primary constructor
    public Patron(String name, String Password, int libaryCardNum) {
        this.name = name;
        this.Password = Password;
        this.libraryCardNum = libaryCardNum;
        checkedOutBooks = new BookStack();
        holder = new BookStack();
        salt = "";
        hashedPassword = "";
    }
    
    // Constructor for password manager
    public Patron(String name, int libaryCardNum, ArrayList<Book> books, String salt, String hashedPassword) {
        this.name = name;
        this.libraryCardNum = libaryCardNum;
        this.checkedOutBooks = new BookStack();
        this.holder = new BookStack();
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        
        // Convert ArrayList to BookStack if needed
        if (books != null) {
            for (Book book : books) {
                this.checkedOutBooks.Push(book);
            }
        }
    }
    
    // Copy constructor
    public Patron(Patron obj) {
        this.name = obj.name;
        this.libraryCardNum = obj.libraryCardNum;
        this.checkedOutBooks = new BookStack();
        this.holder = new BookStack();
        this.salt = obj.salt;
        this.hashedPassword = obj.hashedPassword;
    }
    
    // Added getters and setters for salt and hashedPassword
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getHashedPassword() {
        return hashedPassword;
    }
    
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    // Existing getters and setters
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
    
    // Existing methods
    public void checkOutBook(Book book) {
        if(book.getIsavailable()) {
            book.checkOut();
            checkedOutBooks.Push(book);
        } else {
            System.out.println(book.getTitle() + " Is currently unavailable");
        }
    }
    
    public void Undo() {
        if (checkedOutBooks.getTop() != null) {
            holder.Push(checkedOutBooks.Pop());
            holder.getTop().getdata().checkIn();
        } else {
            System.out.println("No books to undo");
        }
    }
    
    public void Redo() {
        if (holder.getTop() != null) {
            checkedOutBooks.Push(holder.Pop());
            checkedOutBooks.getTop().getdata().checkOut();
        } else {
            System.out.println("No books to redo");
        }
    }
    
    public void returnBook() {
        if (checkedOutBooks.getTop() != null) {
            checkedOutBooks.getTop().getdata().checkIn();
            System.out.println(name + " returned " + checkedOutBooks.getTop().getdata().getTitle());
            checkedOutBooks.Pop();
        } else {
            System.out.println("No books to return");
        }
    }
}
