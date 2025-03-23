package LMS;

import java.util.ArrayList;

public class patronNode {
    private Patron data;
    private patronNode node;

    // Default Constructor
    public patronNode() {
        data = new Patron();
        node = null;
    }
  
    // Primary Constructor 1
    public patronNode(Patron Data, patronNode Node) {
        data = new Patron(Data);
        node = Node;
    }

    // Primary Constructor 2
    public patronNode(Patron Data) {
        data = new Patron(Data);
        node = null;
    }

    // Primary Constructor 3
    public patronNode(String name, String Password, int libaryCardNum) {
        data = new Patron(name, Password, libaryCardNum);
        node = null;
    }

    // Copy Constructor
    public patronNode(patronNode obj) {
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

    public patronNode getNode() {
        return node;
    }

    public void setNode(patronNode node) {
        this.node = node;
    }
}
