package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
public class BookStack {
    private BookNode top;
    
    public BookStack() {
        top = null;
    }
    
    public BookNode getTop() {
        return top;
    }
    
    public void setTop(BookNode top) {
        this.top = top;
    }
    
    public void Push(Book book) {
        BookNode temp = new BookNode(book);
        if(temp != null) {
            if(top == null) {
                top = temp;
            } else {
                temp.setNextNode(top);
                top = temp;
            }
        } else {
            System.err.println("Failed to create and add node to list!");
        }
    }
    
    public Book Pop() {
        Book result = new Book();
        if(top == null) {
            System.out.println("List is empty, no item to remove!");
        } else {
            result = top.getdata();
            BookNode temp = top;
            top = top.getNextNode();
            temp = null;
        }
        return result;
    }
    
    public int StackCount() {
        int count = 0;
        if(top != null) {
            BookStack tempStack = new BookStack();
            Book temp = new Book();
            while (top != null) {
                count++;
                temp = Pop();
                tempStack.Push(temp);
            }
            while(tempStack.getTop() != null) {
                temp = tempStack.Pop();
                Push(temp);
            }
        }
        return count;
    }
    
    public void StackDisplay() {
        if (top != null) {
            BookStack tempStack = new BookStack();
            Book tempData = new Book();
            while (top != null) {
                tempData = Pop();
                System.out.print(tempData + "->");
                tempStack.Push(tempData);
            }
            System.out.println("NULL");
            while (tempStack.getTop() != null) {
                tempData = tempStack.Pop();
                Push(tempData);
            }
        } else {
            System.out.println("The List is empty. Nothing to display!");
        }
    }
}
