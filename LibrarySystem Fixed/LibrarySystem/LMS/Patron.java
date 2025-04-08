package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
import java.util.ArrayList;

public class Patron extends User {
    private BookQueue checkedOutBooks;  // Changed from BookStack to BookQueue
    private BookStack holder;
    private ArrayList<Book> borrowedBooks;
    private String salt;
    private String hashedPassword;
    
    // Default constructor
    public Patron() {
        name = "Mr Clark";
        password = "0000000";
        libraryCardNum = 000000;
        checkedOutBooks = new BookQueue();  // Changed to BookQueue
        holder = new BookStack();
        borrowedBooks = new ArrayList<>();
    }
    
    // Primary constructor
    public Patron(String name, String password, int libraryCardNum) {
        this.name = name;
        this.password = password;
        this.libraryCardNum = libraryCardNum;
        checkedOutBooks = new BookQueue();  // Changed to BookQueue
        holder = new BookStack();
        borrowedBooks = new ArrayList<>();
    }
    
    // Constructor for use with PasswordManager
    public Patron(String name, int libraryCardNum, ArrayList<Book> borrowedBooks, String salt, String hashedPassword) {
        this.name = name;
        this.password = null; // Not storing plain password
        this.libraryCardNum = libraryCardNum;
        this.borrowedBooks = borrowedBooks;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        checkedOutBooks = new BookQueue();  // Changed to BookQueue
        holder = new BookStack();
    }
    
    // Copy constructor
    public Patron(Patron obj) {
        this.name = obj.name;
        this.password = obj.password;
        this.libraryCardNum = obj.libraryCardNum;
        this.checkedOutBooks = new BookQueue();  // Changed to BookQueue
        this.holder = new BookStack();
        this.borrowedBooks = new ArrayList<>();
        this.salt = obj.salt;
        this.hashedPassword = obj.hashedPassword;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getLibaryCardNum() {
        return libraryCardNum;
    }
    
    public void setLibaryCardNum(int libraryCardNum) {
        this.libraryCardNum = libraryCardNum;
    }
    
    public BookQueue getCheckedOutBooks() {  // Changed return type to BookQueue
        return checkedOutBooks;
    }
    
    public void setCheckedOutBooks(BookQueue checkedOutBooks) {  // Changed parameter type to BookQueue
        this.checkedOutBooks = checkedOutBooks;
    }
    
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
    
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
    
    // Methods
    public void checkOutBook(Book book) {
        if(book.getIsavailable()) {
            book.checkOut();
            checkedOutBooks.AddQueue(book);  // Changed to use BookQueue method
            borrowedBooks.add(book);
        } else {
            System.out.println(book.getTitle() + " is currently unavailable");
        }
    }
    
    public void Undo() {
        if (!checkedOutBooks.isEmpty()) {
            Book book = checkedOutBooks.RemoveQueue();  // Changed to use BookQueue method
            if (book != null) {
                holder.Push(book);
                book.checkIn();
                borrowedBooks.remove(book);
            }
        } else {
            System.out.println("No books to undo checkout!");
        }
    }
    
    public void Redo() {
        if (holder.getTop() != null) {
            Book book = holder.Pop();
            checkedOutBooks.AddQueue(book);  // Changed to use BookQueue method
            book.checkOut();
            borrowedBooks.add(book);
        } else {
            System.out.println("No books to redo checkout!");
        }
    }
    
    // Return a specific book by ISBN
    public void returnBook(String isbn) {
        if (checkedOutBooks.isEmpty()) {
            System.out.println("No books to return!");
            return;
        }
        
        // Create temporary queue to hold books
        BookQueue tempQueue = new BookQueue();
        Book returnedBook = null;
        
        // Look for the book with matching ISBN
        while (!checkedOutBooks.isEmpty()) {
            Book book = checkedOutBooks.RemoveQueue();
            if (book.getISBN().equals(isbn)) {
                returnedBook = book;
            } else {
                tempQueue.AddQueue(book);
            }
        }
        
        // Put all non-returned books back in the queue
        while (!tempQueue.isEmpty()) {
            checkedOutBooks.AddQueue(tempQueue.RemoveQueue());
        }
        
        // Process the returned book
        if (returnedBook != null) {
            final Book finalReturnedBook = returnedBook; // Create a final copy for the lambda
            returnedBook.checkIn();
            System.out.println(name + " returned " + returnedBook.getTitle());
            borrowedBooks.removeIf(book -> book.getISBN().equals(finalReturnedBook.getISBN()));
        } else {
            System.out.println("You don't have a book with ISBN: " + isbn);
        }
    }
    
    // Default returnBook method to show a list of books and let user choose
    public void returnBook() {
        // This is now just a placeholder that will be implemented in LibraryManagementSystem
        if (checkedOutBooks.isEmpty()) {
            System.out.println("No books to return!");
        } else {
            System.out.println("Please use the menu to select which book to return.");
        }
    }
    
    // Display all borrowed books
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have no books checked out.");
            return;
        }
        
        System.out.println("Your checked out books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i);
            System.out.println((i + 1) + ". Title: " + book.getTitle() + 
                              ", Author: " + book.getAuthor() + 
                              ", ISBN: " + book.getISBN());
        }
    }
}