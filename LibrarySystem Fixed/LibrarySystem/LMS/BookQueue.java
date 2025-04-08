package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class BookQueue {
    private BookQueueNode front, rear;
    
    public BookQueue() {
        this.front = this.rear = null;
    }
    
    // Add a book to the queue
    public void AddQueue(Book book) {
        BookQueueNode newNode = new BookQueueNode(book);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.setNext(newNode);
        rear = newNode;
    }
    
    // Remove a book from the queue
    public Book RemoveQueue() {
        if (front == null) {
            System.out.println("Queue is empty, no book to remove...");
            return null;
        }
        Book book = front.getBook();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return book;
    }
    
    // View the first book without removing
    public Book seek() {
        if (front == null) {
            System.out.println("Queue is empty...");
            return null;
        }
        return front.getBook();
    }
    
    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }
    
    // Display all books in the queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty...");
            return;
        }
        
        BookQueueNode temp = front;
        int count = 1;
        while (temp != null) {
            System.out.println(count + ". Title: " + temp.getBook().getTitle() + 
                               ", Author: " + temp.getBook().getAuthor() + 
                               ", ISBN: " + temp.getBook().getISBN());
            temp = temp.getNext();
            count++;
        }
    }
    
    // Get the number of items in the queue
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        
        int count = 0;
        BookQueueNode temp = front;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }
    
    // Find a book by ISBN
    public Book findByISBN(String isbn) {
        if (isEmpty()) {
            return null;
        }
        
        BookQueueNode temp = front;
        while (temp != null) {
            if (temp.getBook().getISBN().equals(isbn)) {
                return temp.getBook();
            }
            temp = temp.getNext();
        }
        return null;
    }
    
    // Remove a specific book by ISBN
    public Book removeByISBN(String isbn) {
        if (isEmpty()) {
            return null;
        }
        
        // If the book is at the front of the queue
        if (front.getBook().getISBN().equals(isbn)) {
            return RemoveQueue();
        }
        
        
        BookQueueNode prev = front;
        BookQueueNode curr = front.getNext();
        
        while (curr != null) {
            if (curr.getBook().getISBN().equals(isbn)) {
                Book book = curr.getBook();
                
                // Remove the node
                prev.setNext(curr.getNext());
                
                // If we removed the last node, update rear
                if (curr == rear) {
                    rear = prev;
                }
                
                return book;
            }
            prev = curr;
            curr = curr.getNext();
        }
        
        return null; // Book not found
    }
    
    // Clone the queue (for safe iteration)
    public BookQueue clone() {
        BookQueue newQueue = new BookQueue();
        
        if (isEmpty()) {
            return newQueue;
        }
        
        BookQueueNode temp = front;
        while (temp != null) {
            Book bookCopy = new Book(temp.getBook());
            newQueue.AddQueue(bookCopy);
            temp = temp.getNext();
        }
        
        return newQueue;
    }
}