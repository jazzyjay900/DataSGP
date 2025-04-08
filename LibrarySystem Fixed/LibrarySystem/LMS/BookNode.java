package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class BookNode {
    private Book data;
    private BookNode nextNode;
    
    // Default Constructor
    public BookNode() {
        data = new Book();
        nextNode = null;
    }
    
    // Primary constructor 1
    public BookNode(Book data, BookNode next) {
        this.data = new Book(data);
        this.nextNode = next;
    }
    
    // Primary constructor 2
    public BookNode(Book data) {
        this.data = new Book(data);
        this.nextNode = null;
    }
    
    // Primary constructor 3
    public BookNode(String title, String author, String ISBN, boolean isAvailable) {
        this.data = new Book(title, author, ISBN, isAvailable);
        this.nextNode = null;
    }
    
    // Copy constructor
    public BookNode(BookNode node) {
        this.data = node.data;
        this.nextNode = node.nextNode;
    }
    
    public Book getdata() {
        return data;
    }
    
    public void setdata(Book data) {
        this.data = data;
    }
    
    public BookNode getNextNode() {
        return nextNode;
    }
    
    public void setNextNode(BookNode nextNode) {
        this.nextNode = nextNode;
    }
}