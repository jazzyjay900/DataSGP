package LMS;

public class PatronLinkedList {
    private patronNode head;

    //default Constructor
    public PatronLinkedList() {
        this.head = null;
    }
    
  //primary Constructor
    public PatronLinkedList(patronNode head) {
    	this.head = head;
    }

    // Method to add a patron at the end
    @SuppressWarnings("unused")
	public void addPatrontoFront(Patron DatatoAdd) {
    	patronNode temp;
    	temp = new patronNode ();
        if (temp != null) {
            temp.setData(DatatoAdd);
            temp.setNode(null);
            if (head == null) {
            	head = temp;
            }else {
            	temp.setNode(head);
            	head = temp;
            }
        } else {
        	System.err.println("Error! List is full, cannot add new node");
        	}
        }

    
    @SuppressWarnings("unused")
	public void addPatrontoBack(Patron DatatoAdd) {
    	patronNode temp1 , temp2;
    	temp1 = new patronNode();
    	if(temp1 != null) {
    		temp1.setData(DatatoAdd);
    		temp1.setNode(null);
    		if(head == null) {
    			head = temp1;
    		}else {
    			temp2 = head;
    			while(temp2.getNode()!= null) {
    				temp2 = temp2.getNode();
    			}
    			temp2.setNode(temp1);
    		}
    	}else {
    		System.err.println("Error! List is full, cannot add new node");
    	}   	
    }
    
    public boolean isEmpty() {
  	  if(head == null) {
  		  return true;
  	  }return false;  
    }
    
    
    @SuppressWarnings("unused")
    public boolean isFull() {
    	  patronNode temp = new patronNode();
    	  if(temp != null) {
    		  temp = null;
    		  return false;
    	  }
    	  return true;
      }
    
    
    
    
    // Method to remove a patron by library card number
    public Patron  removePatron(int libraryCardNum) {
    	 Patron dataToReturn = new Patron();
   	  if(isEmpty()) {
   		patronNode curr = head , prev =null;
   		  while(curr!=null) {
   			  if(curr.getData().getLibaryCardNum()== libraryCardNum) {
   				  if(curr == head) {
   					  head = head.getNode();
   				  }else {
   					  prev.setNode(curr.getNode());
   				  }
   				  dataToReturn = curr.getData();
   				  curr = null;
   				  break;
   			  }
   			  prev = curr;
   			  curr = curr.getNode();
   		  }
   	  }else {
   		  System.err.println("List is empty, there is nothing to delete");
   	  }
   	  return dataToReturn;
    }

    // Method to display all patrons
    public void displayPatron() {
  	  patronNode temp = head;
      while (temp != null) {
        System.out.println("Name: " + temp.getData().getName() + ", Library Card Num: " + temp.getData().getLibaryCardNum() 
                   + ", Books Checked out: " + temp.getData().getCheckedOutBooks());
                   temp = temp.getNode();
      }
      System.out.println("Null");
    }
    
    
    
}