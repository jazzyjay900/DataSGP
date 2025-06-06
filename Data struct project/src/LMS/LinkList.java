package LMS;

public class LinkList {
  
  private Node head;
  
  public LinkList() {
    head = null;
  }
  
  public LinkList(Node h) {
    head = h;
  }
  
  public Node getHead() {
    return head;
  }
  
  public void setHead(Node head) {
    this.head = head;
  }

  public void addBooks(Books dataToAdd) {
    Node temp;
    temp = new Node();
    if(temp != null) {
      temp.setdata(dataToAdd);
      temp.setNextNode(null);

      if(head == null) {
        head = temp;
      }
      else {
        temp.setNextNode(head);
        head = temp;
      }
    }
    else {
      System.err.println("List is full! Out of memory");
    }
  }  
  
  public void displayBooks() {
    Node temp = head;
    while (temp != null) {
      System.out.println("Title: " + temp.book.title + ", Author: " + temp.book.author 
                 + ", ISBN: " + temp.book.ISBN + ", Available: " 
                 + (temp.book.isAvailable ? "Yes" : "No"));
                 temp = temp.next;
    }
  }
}
