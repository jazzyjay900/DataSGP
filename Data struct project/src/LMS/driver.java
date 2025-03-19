package LMS;

public class driver {

	public static void main(String[] args) {
		Book book1 = new Book("god","jhon",123456789,true);
		Book book2 = new Book("doggy","mark",3345,true);
		Book book3 = new Book("catty","milly",73467278,true);
		BookLinkedList bl = new BookLinkedList();
		bl.addBookAtBack(book1);
		bl.addBookAtBack(book2);
		bl.addBookAtBack(book3);
		
		Patron p1 = new Patron("jane",332);
		p1.checkOutBook(book1);
		p1.checkOutBook(book2);
		p1.checkOutBook(book3);
		Patron p2 = new Patron("jake",432);
		PatronLinkedList pl = new PatronLinkedList();
		pl.addPatrontoBack(p1);
		pl.addPatrontoBack(p2);
		
	
		p1.getCheckedOutBooks().StackDisplay();
		//bl.displayBooks();
	//	pl.displayPatron();

	}

}
