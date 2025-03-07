//Rajay Trowers


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

    // Generates a cryptographic salt so that identical password dont have the same hash
    private static String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt); 
        return HexFormat.of().formatHex(salt);
    }

    // yah so hashes the passwords using "SHA-256" along with the salts
    private static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(HexFormat.of().parseHex(salt)); // Apply salt before hashing
            return HexFormat.of().formatHex(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    // validate users using name and password
    public boolean authenticate(String name, String password) {
        return patrons.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst()
            .map(p -> hashPassword(password, p.getSalt()).equals(p.getHashedPassword()))
            .orElse(false);
    }

    // Registers a new users and returns a randomly generated default password
    public String registerPatron(String name, int libraryCardNum) {
        if (patrons.stream().anyMatch(p -> p.getLibaryCardNum() == libraryCardNum)) return null; // Prevent duplicate accounts
        
        String defaultPassword = generateRandomPassword(); // Generate a default password
        String salt = generateSalt(); // Generate a new salt
        patrons.add(new Patron(name, libraryCardNum, new ArrayList<>(), salt, hashPassword(defaultPassword, salt))); // Add user to list
        savePatrons(); // Save changes to file
        return defaultPassword;
    }

    // Allows a user to change their password
    public boolean changePassword(String name, String newPassword) {
        return patrons.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst()
            .map(p -> {
                p.setSalt(generateSalt()); // Generate new salt
                p.setHashedPassword(hashPassword(newPassword, p.getSalt())); // Hash new password
                savePatrons(); // Save changes
                return true;
            })
            .orElse(false);
    }

    // Generates a random secure password 
    private static String generateRandomPassword() {
        return random.ints(8, 33, 127) // Generates 8-character 
            .mapToObj(i -> String.valueOf((char) i))
            .collect(Collectors.joining());
    }
}
