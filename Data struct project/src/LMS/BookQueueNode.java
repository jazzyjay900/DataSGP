package LMS;

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
