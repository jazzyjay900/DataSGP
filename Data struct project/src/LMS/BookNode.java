package LMS;

public BookNode {
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

  public BookNode() {
    Data = new Books();
    nextNode = null;
  }

  //copy constructor
  public BookNode(Node node) {
    this.Data = node.Data;
    this.nextNode = nextNode;
  }

  public Node getNextNode() {
    return nextNode;
  }

  public void setNextNode(Node nextNode) {
    this.nextNode = nextNode;
  }
}


