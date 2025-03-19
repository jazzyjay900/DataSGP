package LMS;

public class BookLinkedList {
  
  private BookNode head;
  
//Default Constructor
  public BookLinkedList() {
    head = null;
  }
  
//primary Constructor
  public BookLinkedList(BookNode h) {
    head = h;
  }
  
//getters and setters
  public BookNode getHead() {
    return head;
  }
  
  public void setHead(BookNode head) {
    this.head = head;
  }

  @SuppressWarnings("unused")
public void addBookAtFront(Book dataToAdd) {
	  BookNode temp;
    temp = new BookNode();
    if(temp != null) {
      temp.setdata(dataToAdd);
      temp.setNextNode(null);

      if(head == null) {
        head = temp;
      }
      else {
        temp.setNextNode(head);
        head = temp;
      }
    }
    else {
      System.err.println("List is full! Out of memory");
    }
  }  
  
  
  @SuppressWarnings("unused")
public void addBookAtBack(Book dataToAdd) {
	  BookNode temp1,temp2;
	  temp1 = new BookNode();
	  if(temp1 != null) {
		  temp1.setdata(dataToAdd);
		  temp1.setNextNode(null);
		  if(head == null) {
			  head = temp1;
		  }else {
			  temp2 = head;
			  while (temp2.getNextNode() != null) {
				  temp2 = temp2.getNextNode();
			  }
			  temp2.setNextNode(temp1);
		  }
		  
	  }else {
		  System.err.println("List is full! Out of memory");
	  }
  }
  
  public boolean isEmpty() {
	  if(head == null) {
		  return true;
	  }return false;  
  }
  
  @SuppressWarnings("unused")
public boolean isFull() {
	  BookNode temp = new BookNode();
	  if(temp != null) {
		  temp = null;
		  return false;
	  }
	  return true;
  }
  
  
  public Book deleteANode(int BookISBN) {
	  Book dataToReturn = new Book();
	  if(isEmpty()) {
		  BookNode curr = head , prev =null;
		  while(curr!=null) {
			  if(curr.getdata().getISBN()==BookISBN) {
				  if(curr == head) {
					  head = head.getNextNode();
				  }else {
					  prev.setNextNode(curr.getNextNode());
				  }
				  dataToReturn = curr.getdata();
				  curr = null;
				  break;
			  }
			  prev = curr;
			  curr = curr.getNextNode();
		  }
	  }else {
		  System.err.println("List is empty, there is nothing to delete");
	  }
	  return dataToReturn;
  }
  
  
  public void displayBooks() {
	  BookNode temp = head;
    while (temp != null) {
      System.out.println("Title: " + temp.getdata().getTitle() + ", Author: " + temp.getdata().getAuthor() 
                 + ", ISBN: " + temp.getdata().getISBN() + ", Available: " 
                 + temp.getdata().getIsavailable());
                 temp = temp.getNextNode();
    }
    System.out.println("Null");
  }
  
  
}