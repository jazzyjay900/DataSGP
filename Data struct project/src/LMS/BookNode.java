package LMS;

public class BookNode {
  
  private Books Data;
  private Node nextNode;

  //Default Constructor
  public BookNode() {
    Data = new Books();
    node = null;
  }

  //primary constructor
  public BookNode(Books data, Node Next) {
    Data = new Books(data);
    nextNode = Next;
  }
  
  public BookNode(Books data) {
    Data = new Books(data);
    nextNode = null;
  }

  public BookNode(String title, String author, int ISBN) {
    Data = new Books(title, author, ISBN);
    nextNode = null;
  }

  //copy constructor
  public BookNode(Node node) {
    this.Data = node.Data;
    this.nextNode = nextNode;
  }

  public Books getdata() {
    return Data;
  }

  public void setdata(Books Data) {
    this.Data = Data;
  }

  public Node getNextNode() {
    return nextNode;
  }

  public void setNextNode(Node nextNode) {
    this.nextNode = nextNode;
  }
}


