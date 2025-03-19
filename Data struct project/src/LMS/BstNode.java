package LMS;

public class BstNode {
  Books book;
  BstNode left, right;

  public BstNode(Books book) {
    this.book = book;
    this.left = this.right = null;
  }
}

