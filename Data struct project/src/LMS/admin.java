package LMS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class admin extends User {

	public admin(String name, String Password, int libraryCardNum) {
		this.name = name;
		this.Password = Password;
		this.libraryCardNum = libraryCardNum;
	}
	
	
	public void SavePatron(Patron p) {
		FileWriter patronFileWriter = null;//Creates a null File Writer
		//Exception handling
		try {//Opens try block
			File Patroninput = new File("Patron.txt");
			patronFileWriter = new FileWriter(Patroninput,true);
			if(Patroninput.exists()) {//checks if the file was created
				
				String PatronRecord = "Name: "+p.getName()+" Password: "+p.getPassword()+" Library card number:"+p.getLibaryCardNum()+"\n";
				patronFileWriter.write(PatronRecord);
			}else {
				System.out.println("Patron file could not be created or unable to be found, please try again");
			}
		}//closes try block
		catch(IOException e) {//opens catch block
			System.err.println("Information entered was not saved please try again");
		}//closes catch block
		catch(Exception e) {//opens catch all block
			System.err.println("Something went wrong: " + e.getMessage());
		}//closes catch all block
		finally {//opens finally block
			if(patronFileWriter !=null) {//checks if the file writer is null
				try {//opens try block 
					patronFileWriter.close();
				}//closes try block
				catch (IOException e) {//opens catch block
					e.printStackTrace();
				}//closes catch block
			}//End if
		}//closes finally block
		
	}
	
	
	
	
	
	public void DeletePatron(int libraryCardNum) throws IOException {
		try {
			int line=0;
			String lineToDelete = String.valueOf(libraryCardNum);
		    String currentLine;
		    
			String newFile = "temp.txt";//Create a temporary file
		    File inputFile = new File("Patron.txt");
		    File tempFile = new File(newFile);

		    FileWriter fw = new FileWriter(tempFile, true); // Uses a FileWriter to write to temporary file
		    PrintWriter Pw = new PrintWriter(fw);
		    FileReader oldFile = new FileReader(inputFile);
		    BufferedReader reader = new BufferedReader(oldFile);

		    while ((currentLine = reader.readLine()) != null) {
		        // Skip the line with the matching card Num
		       if (currentLine.contains(lineToDelete)) {
		              continue;
		         }
		       if(!currentLine.isEmpty()) {
		    	   Pw.println(String.format("%s",currentLine)); 
		       }
		      }
		    Pw.flush();
		    Pw.close();
		    oldFile.close();
		    reader.close();
		    fw.close(); // Close the writer

		    inputFile.delete();//delete original file
		    File dump =new File("Patron.txt");
		    tempFile.renameTo(dump);
		}
		catch(FileNotFoundException e) {
			System.err.println("Error, file not found");
			e.printStackTrace();
		}
	}
	
	
	
	public void SaveBook(Book b) {
		FileWriter bookFileWriter = null;//Creates a null File Writer
		//Exception handling
		try {//Opens try block
			File bookinput = new File("Book.txt");
			bookFileWriter = new FileWriter(bookinput,true);
			if(bookinput.exists()) {//checks if the file was created
				
				String bookRecord = "Title: "+b.getTitle()+" Author: "+b.getAuthor()+" ISBN:"+b.getISBN()+"\n";
				bookFileWriter.write(bookRecord);
			}else {
				System.out.println("Patron file could not be created or unable to be found, please try again");
			}
		}//closes try block
		catch(IOException e) {//opens catch block
			System.err.println("Information entered was not saved please try again");
		}//closes catch block
		catch(Exception e) {//opens catch all block
			System.err.println("Something went wrong: " + e.getMessage());
		}//closes catch all block
		finally {//opens finally block
			if(bookFileWriter !=null) {//checks if the file writer is null
				try {//opens try block 
					bookFileWriter.close();
				}//closes try block
				catch (IOException e) {//opens catch block
					e.printStackTrace();
				}//closes catch block
			}//End if
		}//closes finally block
	}
	
	
	
	public void DeleteBook(int ISBN)throws IOException {
		try {
			int line=0;
			String lineToDelete = String.valueOf(ISBN);
		    String currentLine;
		    
			String newFile = "temp.txt";//Create a temporary file
		    File inputFile = new File("Book.txt");
		    File tempFile = new File(newFile);

		    FileWriter fw = new FileWriter(tempFile, true); // Uses a FileWriter to write to temporary file
		    PrintWriter Pw = new PrintWriter(fw);
		    FileReader oldFile = new FileReader(inputFile);
		    BufferedReader reader = new BufferedReader(oldFile);

		    while ((currentLine = reader.readLine()) != null) {
		        // Skip the line with the matching ISBN
		       if (currentLine.contains(lineToDelete)) {
		              continue;
		         }
		       if(!currentLine.isEmpty()) {
		    	   Pw.println(String.format("%s",currentLine)); 
		       }
		      }
		    Pw.flush();
		    Pw.close();
		    oldFile.close();
		    reader.close();
		    fw.close(); // Close the writer

		    inputFile.delete();//delete original file
		    File dump =new File("Book.txt");
		    tempFile.renameTo(dump);
		}
		catch(FileNotFoundException e) {
			System.err.println("Error, file not found");
			e.printStackTrace();
		}
	}
	
}


