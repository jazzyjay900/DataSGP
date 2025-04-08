package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class BstNode {
    Book book;
    BstNode left, right;

    public BstNode(Book book) {
        this.book = book;
        this.left = this.right = null;
    }
}