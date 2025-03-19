package LMS;

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

    // View the first book without removing)
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
        while (temp != null) {
            System.out.println(temp.getBook());
            temp = temp.getNext();
        }
    }
}
