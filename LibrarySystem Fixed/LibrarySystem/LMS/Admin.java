package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Admin extends User {
    public Admin(String name, String password, int libraryCardNum) {
        this.name = name;
        this.password = password;
        this.libraryCardNum = libraryCardNum;
    }
    
    public void savePatron(Patron p) {
        FileWriter patronFileWriter = null;
        try {
            File patronInput = new File("Patron.txt");
            // Create file if it doesn't exist
            if(!patronInput.exists()) {
                patronInput.createNewFile();
            }
            patronFileWriter = new FileWriter(patronInput, true);
            String patronRecord = "Name: " + p.getName() + " Password: " + p.getPassword() + 
                " Library card number: " + p.getLibaryCardNum() + "\n";
            patronFileWriter.write(patronRecord);
        } catch(IOException e) {
            System.err.println("Information entered was not saved please try again: " + e.getMessage());
        } catch(Exception e) {
            System.err.println("Something went wrong: " + e.getMessage());
        } finally {
            if(patronFileWriter != null) {
                try {
                    patronFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Update a patron's password in Patron.txt
    public void updatePatronPassword(String name, String newPassword, int libraryCardNum) throws IOException {
        try {
            String patronIdentifier = "Name: " + name;
            String currentLine;
            
            File inputFile = new File("Patron.txt");
            if (!inputFile.exists()) {
                System.err.println("Patron file does not exist!");
                return;
            }
            
            File tempFile = new File("temp.txt");
            
            try (
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                BufferedReader reader = new BufferedReader(new FileReader(inputFile))
            ) {
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.contains(patronIdentifier)) {
                        // Replace the line with updated password
                        String updatedLine = "Name: " + name + " Password: " + newPassword + 
                                           " Library card number: " + libraryCardNum;
                        pw.println(updatedLine);
                    } else {
                        if(!currentLine.isEmpty()) {
                            pw.println(currentLine);
                        }
                    }
                }
            }
            
            // Delete old file and rename new one
            if (!inputFile.delete()) {
                System.err.println("Could not delete original file");
                return;
            }
            
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Could not rename temp file");
            }
        } catch(FileNotFoundException e) {
            System.err.println("Error, file not found: " + e.getMessage());
        }
    }
    
    public void deletePatron(int libraryCardNum) throws IOException {
        try {
            String lineToDelete = "Library card number: " + libraryCardNum;
            String currentLine;
            
            File inputFile = new File("Patron.txt");
            if (!inputFile.exists()) {
                System.err.println("Patron file does not exist!");
                return;
            }
            
            File tempFile = new File("temp.txt");
            
            try (
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                BufferedReader reader = new BufferedReader(new FileReader(inputFile))
            ) {
                while ((currentLine = reader.readLine()) != null) {
                    if (!currentLine.contains(lineToDelete)) {
                        if(!currentLine.isEmpty()) {
                            pw.println(currentLine); 
                        }
                    }
                }
            }
            
            // Delete old file and rename new one
            if (!inputFile.delete()) {
                System.err.println("Could not delete original file");
                return;
            }
            
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Could not rename temp file");
            }
        } catch(FileNotFoundException e) {
            System.err.println("Error, file not found: " + e.getMessage());
        }
    }
    
    public void saveBook(Book b) {
        FileWriter bookFileWriter = null;
        try {
            File bookInput = new File("Book.txt");
            // Create file if it doesn't exist
            if(!bookInput.exists()) {
                bookInput.createNewFile();
            }
            bookFileWriter = new FileWriter(bookInput, true);
            String bookRecord = "Title: " + b.getTitle() + " Author: " + b.getAuthor() + 
                " ISBN: " + b.getISBN() + "\n";
            bookFileWriter.write(bookRecord);
        } catch(IOException e) {
            System.err.println("Information entered was not saved please try again: " + e.getMessage());
        } catch(Exception e) {
            System.err.println("Something went wrong: " + e.getMessage());
        } finally {
            if(bookFileWriter != null) {
                try {
                    bookFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void deleteBook(String ISBN) throws IOException {
        try {
            String lineToDelete = "ISBN: " + ISBN;
            String currentLine;
            
            File inputFile = new File("Book.txt");
            if (!inputFile.exists()) {
                System.err.println("Book file does not exist!");
                return;
            }
            
            File tempFile = new File("temp.txt");
            
            try (
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                BufferedReader reader = new BufferedReader(new FileReader(inputFile))
            ) {
                while ((currentLine = reader.readLine()) != null) {
                    if (!currentLine.contains(lineToDelete)) {
                        if(!currentLine.isEmpty()) {
                            pw.println(currentLine); 
                        }
                    }
                }
            }
            
            // Delete old file and rename new one
            if (!inputFile.delete()) {
                System.err.println("Could not delete original file");
                return;
            }
            
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Could not rename temp file");
            }
        } catch(FileNotFoundException e) {
            System.err.println("Error, file not found: " + e.getMessage());
        }
    }
}