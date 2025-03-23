package LMS;

public class BST {

	
	private BstNode root;

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

	  @SuppressWarnings("unlikely-arg-type")
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
	 
	  public void displayBooks() {
	    traverseInOrder(root);
	  }

	  public void traverseInOrder(BstNode node) {
	    if (node != null) {
	        traverseInOrder(node.left);
	        System.out.print("Title: " + node.book.getTitle() + ", Author: " + node.book.getAuthor() + ", ISBN: " + node.book.getISBN() + ", Available: " + node.book.getIsavailable());
	        traverseInOrder(node.right);
	    }
	  }
	
	
	
}
