package LMS;

public class PatronNode() {
  
  private Patron data;
  private PatronNode node;

  public PatronNode() {

    data = new Patron();
    node = null;
  }

  public PatronNode(Patron Data, PatronNode Node) {

    data = new Patron(Data);
    node = Node;
  }

  public PatronNode(Patron Data) {

    data = new Patron(Data);
    node = null;
  }

  public PatronNode(String name, int libaryCardNum, ArrayList<Books> checkedOutBooks) {

    data = new Patron(name, libaryCardNum, checkedOutBooks);
    node = null;
  }

  public PatronNode(PatronNode obj) {

    this.data = obj.data;
    this.node = obj.node;
  }

  public Patron getData() {
    return data;
  }

  public void setData(Patron data) {
    this.data = data;
  }

  public PatronNode getNode() {
    return node;
  }

  public void setNode(PatronNode node) {
    this.node = node;
  }
}
