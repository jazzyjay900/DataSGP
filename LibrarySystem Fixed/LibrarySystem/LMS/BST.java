package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class BST {
    private BstNode root;

    public BST() {
        root = null;
    }

    public void addBook(Book book) {
        root = addRecursive(root, book);
    }

    private BstNode addRecursive(BstNode current, Book book) {
        if (current == null) {
            return new BstNode(book);
        }

        if (book.getTitle().compareTo(current.book.getTitle()) < 0) {
            current.left = addRecursive(current.left, book);
        } else if (book.getTitle().compareTo(current.book.getTitle()) > 0) {
            current.right = addRecursive(current.right, book);
        }
        return current;
    }

    public Book search(String key) {
        return searchRecursive(root, key);
    }

    private Book searchRecursive(BstNode bst, String key) {
        if (bst == null) {
            return null;
        } 
        if (key.equals(bst.book.getTitle()) || key.equals(bst.book.getAuthor()) || key.equals(bst.book.getISBN())) {
            return bst.book;
        } 
       
        if (key.compareTo(bst.book.getTitle()) < 0) {
            return searchRecursive(bst.left, key);
        } 
        else {
            return searchRecursive(bst.right, key);
        }
    }
    
    // New method specifically for searching by ISBN
    public Book findByISBN(String isbn) {
        return findByISBNRecursive(root, isbn);
    }
    
    // Helper method for ISBN search that traverses the entire tree
    private Book findByISBNRecursive(BstNode node, String isbn) {
        if (node == null) {
            return null;
        }
        
        // Check current node
        if (node.book.getISBN().equals(isbn)) {
            return node.book;
        }
        
        // Check left subtree
        Book leftResult = findByISBNRecursive(node.left, isbn);
        if (leftResult != null) {
            return leftResult;
        }
        
        // Check right subtree
        return findByISBNRecursive(node.right, isbn);
    }
 
    public void displayBooks() {
        traverseInOrder(root);
    }

    public void traverseInOrder(BstNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println("Title: " + node.book.getTitle() + ", Author: " + node.book.getAuthor() 
                + ", ISBN: " + node.book.getISBN() + ", Available: " + node.book.getIsavailable());
            traverseInOrder(node.right);
        }
    }
    
    // Get the root node (needed for traversal when rebuilding)
    public BstNode getRoot() {
        return root;
    }
    
    // Check if a book with given ISBN exists
    public boolean containsISBN(String isbn) {
        return containsISBNRecursive(root, isbn);
    }
    
    private boolean containsISBNRecursive(BstNode node, String isbn) {
        if (node == null) {
            return false;
        }
        
        if (node.book.getISBN().equals(isbn)) {
            return true;
        }
        
        return containsISBNRecursive(node.left, isbn) || 
               containsISBNRecursive(node.right, isbn);
    }
    
    // Count the number of books in the BST
    public int countBooks() {
        return countBooksRecursive(root);
    }
    
    private int countBooksRecursive(BstNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countBooksRecursive(node.left) + countBooksRecursive(node.right);
    }
}