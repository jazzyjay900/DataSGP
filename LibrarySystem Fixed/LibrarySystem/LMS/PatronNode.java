package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class PatronNode {
    private Patron data;
    private PatronNode node;

    // Default Constructor
    public PatronNode() {
        data = new Patron();
        node = null;
    }
    
    // Primary Constructor 1
    public PatronNode(Patron data, PatronNode node) {
        this.data = new Patron(data);
        this.node = node;
    }

    // Primary Constructor 2
    public PatronNode(Patron data) {
        this.data = new Patron(data);
        this.node = null;
    }

    // Primary Constructor 3
    public PatronNode(String name, String password, int libraryCardNum) {
        this.data = new Patron(name, password, libraryCardNum);
        this.node = null;
    }

    // Copy Constructor
    public PatronNode(PatronNode obj) {
        this.data = obj.data;
        this.node = obj.node;
    }

    // Getters and setters
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
