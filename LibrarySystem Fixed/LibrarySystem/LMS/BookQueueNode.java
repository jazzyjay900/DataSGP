package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class BookQueueNode {
    private Book book;
    private BookQueueNode next;

    public BookQueueNode(Book book) {
        this.book = book;
        this.next = null;
    }

    // Getters and setters
    public Book getBook() {
        return book;
    }

    public BookQueueNode getNext() {
        return next;
    }

    public void setNext(BookQueueNode next) {
        this.next = next;
    }
}
