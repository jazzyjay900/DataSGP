package LMS;

public class PatronLinkedList {
    private Node head;

    // Constructor
    public PatronLinkedList() {
        this.head = null;
    }

    // Method to add a patron at the end
    public void addPatron(Patron patron) {
        Node newNode = new Node(patron);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println(patron.getName() + " added to the library system.");
    }

    // Method to remove a patron by library card number
    public void removePatron(int libraryCardNum) {
        if (head == null) {
            System.out.println("No patrons in the library.");
            return;
        }

        if (head.patron.getLibraryCardNum() == libraryCardNum) {
            System.out.println(head.patron.getName() + " removed from the library.");
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.patron.getLibraryCardNum() != libraryCardNum) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Patron with library card number " + libraryCardNum + " not found.");
        } else {
            System.out.println(temp.next.patron.getName() + " removed from the library.");
            temp.next = temp.next.next;
        }
    }

    // Method to display all patrons
    public void displayPatrons() {
        if (head == null) {
            System.out.println("No patrons in the library.");
            return;
        }

        Node temp = head;
        System.out.println("List of Patrons:");
        while (temp != null) {
            System.out.println("Name: " + temp.patron.getName() + ", Library Card #: " + temp.patron.getLibraryCardNum());
            temp = temp.next;
        }
    }
}
