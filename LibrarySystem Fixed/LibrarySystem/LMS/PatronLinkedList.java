package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class PatronLinkedList {
    private PatronNode head;
    
    // Default Constructor
    public PatronLinkedList() {
        this.head = null;
    }
    
    // Primary Constructor
    public PatronLinkedList(PatronNode head) {
        this.head = head;
    }
    
    // Getter for head
    public PatronNode getHead() {
        return head;
    }
    
    // Method to add a patron at the front
    public void addPatrontoFront(Patron dataToAdd) {
        PatronNode temp;
        temp = new PatronNode();
        if (temp != null) {
            temp.setData(dataToAdd);
            temp.setNode(null);
            if (head == null) {
                head = temp;
            } else {
                temp.setNode(head);
                head = temp;
            }
        } else {
            System.err.println("Error! List is full, cannot add new node");
        }
    }
    
    // Method to add a patron at the back
    public void addPatrontoBack(Patron dataToAdd) {
        PatronNode temp1, temp2;
        temp1 = new PatronNode();
        if(temp1 != null) {
            temp1.setData(dataToAdd);
            temp1.setNode(null);
            if(head == null) {
                head = temp1;
            } else {
                temp2 = head;
                while(temp2.getNode() != null) {
                    temp2 = temp2.getNode();
                }
                temp2.setNode(temp1);
            }
        } else {
            System.err.println("Error! List is full, cannot add new node");
        }
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    @SuppressWarnings("unused")
    public boolean isFull() {
        PatronNode temp = new PatronNode();
        if(temp != null) {
            temp = null;
            return false;
        }
        return true;
    }
    
    // Find patron by name
    public Patron findPatronByName(String name) {
        if (isEmpty()) {
            return null;
        }
        
        PatronNode current = head;
        while (current != null) {
            if (current.getData().getName().equals(name)) {
                return current.getData();
            }
            current = current.getNode();
        }
        
        return null;
    }
    
    // Find patron by library card number
    public Patron findPatronByCardNumber(int cardNum) {
        if (isEmpty()) {
            return null;
        }
        
        PatronNode current = head;
        while (current != null) {
            if (current.getData().getLibaryCardNum() == cardNum) {
                return current.getData();
            }
            current = current.getNode();
        }
        
        return null;
    }
    
    // Method to remove a patron by library card number
    public Patron removePatron(int libraryCardNum) {
        Patron dataToReturn = new Patron();
        if(!isEmpty()) {
            PatronNode curr = head, prev = null;
            while(curr != null) {
                if(curr.getData().getLibaryCardNum() == libraryCardNum) {
                    if(curr == head) {
                        head = head.getNode();
                    } else {
                        prev.setNode(curr.getNode());
                    }
                    dataToReturn = curr.getData();
                    curr = null;
                    break;
                }
                prev = curr;
                curr = curr.getNode();
            }
        } else {
            System.err.println("List is empty, there is nothing to delete");
        }
        return dataToReturn;
    }
    
    // Method to display all patrons
    public void displayPatrons() {
        if (isEmpty()) {
            System.out.println("No patrons in the system.");
            return;
        }
        
        PatronNode temp = head;
        while (temp != null) {
            // Get the number of books checked out from the borrowedBooks list
            int booksCheckedOut = 0;
            if (temp.getData().getBorrowedBooks() != null) {
                booksCheckedOut = temp.getData().getBorrowedBooks().size();
            }
            
            System.out.println("Name: " + temp.getData().getName() + 
                             ", Library Card Num: " + temp.getData().getLibaryCardNum() + 
                             ", Books Checked out: " + booksCheckedOut);
            temp = temp.getNode();
        }
        System.out.println("Null");
    }
}