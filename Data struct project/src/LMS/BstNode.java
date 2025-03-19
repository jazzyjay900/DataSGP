package LMS;

public class BstNode {
  Book book;
  BstNode left, right;

  public BstNode(Book book) {
    this.book = book;
    this.left = this.right = null;
  }
}