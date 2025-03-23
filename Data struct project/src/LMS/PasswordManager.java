package LMS;

import java.io.*;
import java.security.*;
import java.util.*;
import java.util.stream.Collectors;

public class PasswordManager {

    private static final String PASSWORD_FILE = "passwords.txt"; // File to store users passwords
    private static final SecureRandom random = new SecureRandom(); // to create a secure random generator for salts and passwords
    private List<Patron> patrons = new ArrayList<>(); // List of users

    // Constructor to loads existing patrons from password text file
    public PasswordManager() {
        loadPatrons();
    }

    // Loads patron data from the password file
    private void loadPatrons() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            patrons = reader.lines()
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 4) // for correct data format
                .map(parts -> new Patron(parts[0], Integer.parseInt(parts[1]), new ArrayList<>(), parts[2], parts[3]))
                .collect(Collectors.toList());
        } catch (IOException e) { //error handling
            System.err.println("<< No existing password file. Creating new list >>");
        }
    }

    // Saves patron data back to the password file
    private void savePatrons() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE))) {
            for (Patron patron : patrons) {
                writer.write(String.join(",",
                    patron.getName(),
                    String.valueOf(patron.getLibaryCardNum()),
                    patron.getSalt(),
                    patron.getHashedPassword()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("<< Error saving passwords >> " + e.getMessage());
        }
    }

    // Generates salt so that identical password dont have the same hash
    private static String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt); 
        return bytesToHex(salt);
    }

    // Utility method to convert bytes to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Utility method to convert hexadecimal string to bytes
    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                 + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    // Hashes the passwords using "SHA-256" along with the salts
    private static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(hexToBytes(salt)); // Apply salt before hashing
            return bytesToHex(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    // Validate users using name and password
    public boolean authenticate(String name, String password) {
        for (Patron p : patrons) {
            if (p.getName().equals(name)) {
                String hashedAttempt = hashPassword(password, p.getSalt());
                if (hashedAttempt.equals(p.getHashedPassword())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    // Registers a new user and returns a randomly generated default password
    public String registerPatron(String name, int libraryCardNum) {
        // Check for duplicate library card numbers
        for (Patron p : patrons) {
            if (p.getLibaryCardNum() == libraryCardNum) {
                System.out.println("A patron with this library card number already exists.");
                return null;
            }
        }
        
        String defaultPassword = generateRandomPassword(); // Generate a default password
        String salt = generateSalt(); // Generate a new salt
        String hashedPassword = hashPassword(defaultPassword, salt);
        
        patrons.add(new Patron(name, libraryCardNum, new ArrayList<>(), salt, hashedPassword)); // Add user to list
        savePatrons(); // Save changes to file
        return defaultPassword;
    }

    // Allows a user to change their password
    public boolean changePassword(String name, String newPassword) {
        for (Patron p : patrons) {
            if (p.getName().equals(name)) {
                p.setSalt(generateSalt()); // Generate new salt
                p.setHashedPassword(hashPassword(newPassword, p.getSalt())); // Hash new password
                savePatrons(); // Save changes
                return true;
            }
        }
        return false;
    }

    // Generates a random secure password 
    private static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            // Generate characters between ASCII 33 and 126 (printable characters)
            password.append((char) (random.nextInt(94) + 33));
        }
        return password.toString();
    }
    

    public Patron getPatronByName(String name) {
        for (Patron p : patrons) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    
    // Display all patrons for admin
    public void displayAllPatrons() {
        System.out.println("--- All Registered Patrons ---");
        for (Patron p : patrons) {
            System.out.println("Name: " + p.getName() + ", Library Card #: " + p.getLibaryCardNum());
        }
        System.out.println("----------------------------");
    }
}
