package LMS;

public class BST() {

  private BstNode root;

  public void addBook(Books book) {
    root = addRecursive(root, book);
  }

  private BstNode addRecursive(BstNode current, Books book) {
    if (current == null) {
        return new BstNode(book);
    }

    if (book.title.compareTo(current.book.title) < 0) {
        current.left = addRecursive(current.left, book);
    } else if (book.title.compareTo(current.book.title) > 0) {
        current.right = addRecursive(current.right, book);
    }
    return current;
  }

  public Books search(String key) {
    return searchRecursive(root, key);
  }

  private Books searchRecursive(BstNode bst, String key) {
    if (bst == null) {
        return null;
    } 
    if (key.equals(bst.book.title) || key.equals(bst.book.author) || key.equals(bst.book.isbn) {
        return bst.book;
    } 
   
    if (key.compareTo(bst.book.title) < 0) {
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
        System.out.print("Title: " + node.book.title + ", Author: " + node.book.author + ", ISBN: " + node.book.ISBN + ", Available: " + node.book.isAvailable);
        traverseInOrder(node.right);
    }
  }
  
  
}
  
