package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
import java.io.IOException;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static BST bookCatalog = new BST();
    private static PatronLinkedList patrons = new PatronLinkedList();
    private static Admin admin = new Admin("Admin", "admin123", 999999);
    private static PasswordManager passwordManager = new PasswordManager();
    
    public static void main(String[] args) {
        loadInitialData();
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Patron Login");
            System.out.println("3. Register New Patron");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    patronLogin();
                    break;
                case 3:
                    registerPatron();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void loadInitialData() {
        try {
            //  sample books to add to catalog
            //bookCatalog.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", true));
            //bookCatalog.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", true));
            //bookCatalog.addBook(new Book("1984", "George Orwell", "9780451524935", true));
            //bookCatalog.addBook(new Book("Pride and Prejudice", "Jane Austen", "9780141439518", true));
            //bookCatalog.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488", true));
            
            // preloaded user for testing text file
            String defaultPassword = passwordManager.registerPatron("Rajay", 123456);
            if (defaultPassword != null) {
                Patron samplePatron = new Patron("Rajay", defaultPassword, 123456);
                patrons.addPatrontoFront(samplePatron);
                System.out.println("Data loaded successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error loading initial data: " + e.getMessage());
        }
    }
    
    private static void adminLogin() {
        System.out.println("\n===== ADMIN LOGIN =====");
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        
        if (username.equals(admin.name) && password.equals(admin.password)) {
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }
    
    private static void adminMenu() {
        boolean logout = false;
        
        while (!logout) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add New Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Add New Patron");
            System.out.println("5. Remove Patron");
            System.out.println("6. Display All Patrons");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    System.out.println("\n===== BOOK CATALOG =====");
                    bookCatalog.displayBooks();
                    break;
                case 4:
                    addPatron();
                    break;
                case 5:
                    removePatron();
                    break;
                case 6:
                    System.out.println("\n===== PATRON LIST =====");
                    patrons.displayPatrons();
                    break;
                case 7:
                    logout = true;
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void addBook() {
        System.out.println("\n===== ADD NEW BOOK =====");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN (13 digits): ");
        String isbn = scanner.nextLine();
        
        // Validate ISBN is 13 digits
        while (isbn.length() != 13 || !isbn.matches("\\d+")) {
            System.out.println("ISBN must be exactly 13 digits. Please try again.");
            System.out.print("Enter ISBN (13 digits): ");
            isbn = scanner.nextLine();
        }
        
        Book newBook = new Book(title, author, isbn, true);
        bookCatalog.addBook(newBook);
        
        // Save book to file
        admin.saveBook(newBook);
        
        System.out.println("Book added successfully!");
    }
    
    private static void removeBook() {
        System.out.println("\n===== REMOVE BOOK =====");
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine();
        
        // Validate ISBN format
        while (isbn.length() != 13 || !isbn.matches("\\d+")) {
            System.out.println("ISBN must be exactly 13 digits. Please try again.");
            System.out.print("Enter ISBN (13 digits): ");
            isbn = scanner.nextLine();
        }
        
        try {
            Book book = bookCatalog.findByISBN(isbn);
            if (book != null) {
                // Remove from Book.txt file
                admin.deleteBook(isbn);
                // Remove from book catalog (BST)
                removeBookFromCatalog(book);
                System.out.println("Book removed successfully!");
            } else {
                System.out.println("Book with ISBN " + isbn + " not found in catalog.");
            }
        } catch (IOException e) {
            System.out.println("Error removing book: " + e.getMessage());
        }
    }
    
    
    private static Book findBookByISBN(String isbn) {
        return bookCatalog.findByISBN(isbn);
    }
    
    // method to remove a book from the catalog
    private static void removeBookFromCatalog(Book book) {
        // can't directly remove from BST,  so its rebuild the BST
        BST newCatalog = new BST();
        rebuildCatalogWithoutBook(bookCatalog, newCatalog, book);
        bookCatalog = newCatalog;
    }
    
    
    private static void rebuildCatalogWithoutBook(BST oldCatalog, BST newCatalog, Book bookToRemove) {
        // create a temporary array to store all books
        java.util.ArrayList<Book> allBooks = new java.util.ArrayList<>();
        
       // class to collect books from the BST
        class BookCollector {
            void collectBooks(BstNode node, java.util.ArrayList<Book> books) {
                if (node != null) {
                    collectBooks(node.left, books);
                    // add book only if it's not the one we want to remove
                    if (!node.book.getISBN().equals(bookToRemove.getISBN())) {
                        books.add(node.book);
                    }
                    collectBooks(node.right, books);
                }
            }
        }
        
        // collect all books except the one to remove
        BookCollector collector = new BookCollector();
        collector.collectBooks(oldCatalog.getRoot(), allBooks);
        
        // catalog with the remaining books
        for (Book book : allBooks) {
            newCatalog.addBook(book);
        }
    }
    
    private static void addPatron() {
        System.out.println("\n===== ADD NEW PATRON =====");
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter library card number: ");
        int cardNum = getIntInput();
        
        String generatedPassword = passwordManager.registerPatron(name, cardNum);
        
        if (generatedPassword != null) {
            Patron newPatron = new Patron(name, generatedPassword, cardNum);
            patrons.addPatrontoFront(newPatron);
            
            // Save patron to file
            admin.savePatron(newPatron);
            
            System.out.println("Patron added successfully!");
            System.out.println("Generated password: " + generatedPassword);
        } else {
            System.out.println("Failed to add patron. Library card number may already exist.");
        }
    }
    
    private static void removePatron() {
        System.out.println("\n===== REMOVE PATRON =====");
        System.out.print("Enter library card number of patron to remove: ");
        int cardNum = getIntInput();
        
        try {
            admin.deletePatron(cardNum);
            patrons.removePatron(cardNum);
            System.out.println("Patron removed successfully!");
        } catch (IOException e) {
            System.out.println("Error removing patron: " + e.getMessage());
        }
    }
    
    private static void patronLogin() {
        System.out.println("\n===== PATRON LOGIN =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        if (passwordManager.authenticate(name, password)) {
            // Find the patron in our linked list
            PatronNode current = patrons.getHead();
            Patron loggedInPatron = null;
            
            while (current != null) {
                if (current.getData().getName().equals(name)) {
                    loggedInPatron = current.getData();
                    break;
                }
                current = current.getNode();
            }
            
            if (loggedInPatron == null) {
                // Create a new patron if not found in linked list (based on password manager)
                Patron newPatron = passwordManager.getPatronByName(name);
                if (newPatron != null) {
                    loggedInPatron = new Patron(name, password, newPatron.getLibaryCardNum());
                    patrons.addPatrontoFront(loggedInPatron);
                } else {
                    loggedInPatron = new Patron(name, password, 0); // Fallback
                }
            }
            
            // Make sure the patron has the password set
            if (loggedInPatron.getPassword() == null) {
                loggedInPatron.setPassword(password);
            }
            
            patronMenu(loggedInPatron);
        } else {
            System.out.println("Invalid patron credentials!");
        }
    }
    
    private static void patronMenu(Patron patron) {
        boolean logout = false;
        
        while (!logout) {
            System.out.println("\n===== PATRON MENU =====");
            System.out.println("1. Browse Books");
            System.out.println("2. Search Book");
            System.out.println("3. Check Out Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Checked Out Books");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    System.out.println("\n===== BOOK CATALOG =====");
                    bookCatalog.displayBooks();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    checkOutBook(patron);
                    break;
                case 4:
                    returnBookMenu(patron);  
                    break;
                case 5:
                    System.out.println("\n===== YOUR CHECKED OUT BOOKS =====");
                    patron.displayBorrowedBooks();  
                    break;
                case 6:
                    changePassword(patron);
                    break;
                case 7:
                    logout = true;
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void searchBook() {
        System.out.println("\n===== SEARCH BOOK =====");
        System.out.print("Enter book title, author, or ISBN: ");
        String key = scanner.nextLine();
        
        Book book = bookCatalog.search(key);
        
        if (book != null) {
            System.out.println("Book found:");
            System.out.println(book);
        } else {
            System.out.println("Book not found!");
        }
    }
    
    private static void checkOutBook(Patron patron) {
        System.out.println("\n===== CHECK OUT BOOK =====");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        
        Book book = bookCatalog.search(title);
        
        if (book != null) {
            if (book.getIsavailable()) {
                patron.checkOutBook(book);
                System.out.println("Book checked out successfully!");
            } else {
                System.out.println("Book is currently unavailable!");
            }
        } else {
            System.out.println("Book not found!");
        }
    }
    
    //  method to allow patron to select which book to return
    private static void returnBookMenu(Patron patron) {
        if (patron.getBorrowedBooks() == null || patron.getBorrowedBooks().isEmpty()) {
            System.out.println("You have no books to return.");
            return;
        }
        
        System.out.println("\n===== RETURN BOOK =====");
        patron.displayBorrowedBooks();
        
        System.out.print("Enter the ISBN of the book you want to return (or 0 to cancel): ");
        String isbn = scanner.nextLine();
        
        if (isbn.equals("0")) {
            System.out.println("Return operation cancelled.");
            return;
        }
        
        // Check if the patron has borrowed this book
        boolean hasBorrowed = false;
        for (Book book : patron.getBorrowedBooks()) {
            if (book.getISBN().equals(isbn)) {
                hasBorrowed = true;
                break;
            }
        }
        
        if (hasBorrowed) {
            patron.returnBook(isbn);
        } else {
            System.out.println("You haven't borrowed a book with ISBN: " + isbn);
        }
    }
    
    private static void changePassword(Patron patron) {
        System.out.println("\n===== CHANGE PASSWORD =====");
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();
        
        if (patron.getPassword() != null && currentPassword.equals(patron.getPassword())) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            
            if (passwordManager.changePassword(patron.getName(), newPassword)) {
                patron.setPassword(newPassword);
                // Update the Patron.txt file
                try {
                    admin.updatePatronPassword(patron.getName(), newPassword, patron.getLibaryCardNum());
                    System.out.println("Password changed successfully!");
                } catch (IOException e) {
                    System.out.println("Password changed in system but failed to update file: " + e.getMessage());
                }
            } else {
                System.out.println("Error changing password!");
            }
        } else if (passwordManager.authenticate(patron.getName(), currentPassword)) {
            // Current password is correct according to password manager
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            
            if (passwordManager.changePassword(patron.getName(), newPassword)) {
                patron.setPassword(newPassword);
                // Update the Patron.txt file
                try {
                    admin.updatePatronPassword(patron.getName(), newPassword, patron.getLibaryCardNum());
                    System.out.println("Password changed successfully!");
                } catch (IOException e) {
                    System.out.println("Password changed in system but failed to update file: " + e.getMessage());
                }
            } else {
                System.out.println("Error changing password!");
            }
        } else {
            System.out.println("Incorrect current password!");
        }
    }
    
    private static void registerPatron() {
        System.out.println("\n===== REGISTER NEW PATRON =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your library card number: ");
        int cardNum = getIntInput();
        
        String generatedPassword = passwordManager.registerPatron(name, cardNum);
        
        if (generatedPassword != null) {
            Patron newPatron = new Patron(name, generatedPassword, cardNum);
            patrons.addPatrontoFront(newPatron);
            
            // Save patron to file
            admin.savePatron(newPatron);
            
            System.out.println("Registration successful!");
            System.out.println("Your temporary password is: " + generatedPassword);
            System.out.println("Please login and change your password immediately.");
        } else {
            System.out.println("Registration failed. Library card number already exists!");
        }
    }
    
    private static int getIntInput() {
        try {
            int input = Integer.parseInt(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getIntInput(); // ask for input until a valid number is provided.....recursively ??
        }
    }
    
    // method to access patrons list
    public static PatronLinkedList getPatrons() {
        return patrons;
    }
}