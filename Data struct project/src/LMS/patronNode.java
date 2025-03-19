package LMS;

public class patronNode {
	 private Patron data;
	  private patronNode node;

	//Default Constructor
	  public patronNode() {
	    data = new Patron();
	    node = null;
	  }
	  
	//primary Constructor 1
	  public patronNode(Patron Data, patronNode Node) {
	    data = new Patron(Data);
	    node = Node;
	  }

	//primary Constructor 2
	  public patronNode(Patron Data) {
	    data = new Patron(Data);
	    node = null;
	  }

	//primary Constructor 3
	  public patronNode(String name, int libaryCardNum) {
	    data = new Patron(name, libaryCardNum);
	    node = null;
	  }

	//copy Constructor
	  public patronNode(patronNode obj) {
	    this.data = obj.data;
	    this.node = obj.node;
	  }

	//getters and setters
	  public Patron getData() {
	    return data;
	  }

	  public void setData(Patron data) {
	    this.data = data;
	  }

	  public patronNode getNode() {
	    return node;
	  }

	  public void setNode(patronNode node) {
	    this.node = node;
	  }
	}

