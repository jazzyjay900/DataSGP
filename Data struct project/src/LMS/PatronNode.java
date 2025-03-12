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

  public PatronNode(


}
