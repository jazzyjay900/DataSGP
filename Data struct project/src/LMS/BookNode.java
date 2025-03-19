package LMS;

public class BookNode {
  
  private Book Data;
  private BookNode nextNode;

  //Default Constructor
  public BookNode() {
    Data = new Book();
    nextNode = null;
  }

  //primary constructor 1
  public BookNode(Book data, BookNode Next) {
    Data = new Book(data);
    nextNode = Next;
  }
  
//primary constructor 2
  public BookNode(Book data) {
    Data = new Book(data);
    nextNode = null;
  }

//primary constructor 3
  public BookNode(String title, String author, int ISBN, boolean Isavailable) {
    Data = new Book(title, author, ISBN, true);
    nextNode = null;
  }

  //copy constructor
  public BookNode(BookNode node) {
    this.Data = node.Data;
    this.nextNode = node.nextNode;
  }

  public Book getdata() {
    return Data;
  }

  public void setdata(Book Data) {
    this.Data = Data;
  }

  public BookNode getNextNode() {
    return nextNode;
  }

  public void setNextNode(BookNode nextNode) {
    this.nextNode = nextNode;
  }
}