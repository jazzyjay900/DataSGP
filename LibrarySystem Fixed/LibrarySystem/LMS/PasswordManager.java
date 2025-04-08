package LMS;
/*Rajay Trowers 2205702
Dijean Sterling 2304879
Jahzeal Simms 2202446
Abigail Bembridge 2305624
Kadedra Mason 2304879*/
import java.io.*;
import java.security.*;
import java.util.*;

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
        File passwordFile = new File(PASSWORD_FILE);
        if (!passwordFile.exists()) {
            try {
                passwordFile.createNewFile();
                System.out.println("Created new password file.");
            } catch (IOException e) {
                System.err.println("Failed to create password file: " + e.getMessage());
            }
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            List<Patron> loadedPatrons = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        String name = parts[0];
                        int libraryCardNum = Integer.parseInt(parts[1]);
                        String salt = parts[2];
                        String hashedPassword = parts[3];
                        
                        Patron patron = new Patron(name, null, libraryCardNum);
                        patron.setSalt(salt);
                        patron.setHashedPassword(hashedPassword);
                        loadedPatrons.add(patron);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid library card number in password file: " + parts[1]);
                    }
                }
            }
            patrons = loadedPatrons;
            System.out.println("Loaded " + patrons.size() + " patrons from password file.");
        } catch (IOException e) {
            System.err.println("Error reading password file: " + e.getMessage());
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
            writer.flush(); // Make sure data is written immediately
        } catch (IOException e) {
            System.err.println("Error saving passwords: " + e.getMessage());
        }
    }

    // Generates a cryptographic salt so that identical password dont have the same hash
    private static String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt); 
        return HexFormat.of().formatHex(salt);
    }

    // Hashes the passwords using "SHA-256" along with the salts
    private static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(HexFormat.of().parseHex(salt)); // Apply salt before hashing
            return HexFormat.of().formatHex(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    // Validate users using name and password
    public boolean authenticate(String name, String password) {
        for (Patron patron : patrons) {
            if (patron.getName().equals(name)) {
                try {
                    String hashedInputPassword = hashPassword(password, patron.getSalt());
                    boolean isMatch = hashedInputPassword.equals(patron.getHashedPassword());
                    
                    // For debugging
                    if (!isMatch) {
                        System.out.println("Password mismatch for user: " + name);
                    }
                    
                    return isMatch;
                } catch (Exception e) {
                    System.err.println("Authentication error for " + name + ": " + e.getMessage());
                    return false;
                }
            }
        }
        System.out.println("User not found: " + name);
        return false;
    }

    // makes new users and returns a randomly generated default password
    public String registerPatron(String name, int libraryCardNum) {
        // Check for duplicate library card numbers
        for (Patron patron : patrons) {
            if (patron.getLibaryCardNum() == libraryCardNum) {
                System.out.println("Registration failed: Library card number already exists: " + libraryCardNum);
                return null; // Prevent duplicate accounts
            }
        }
        
        String defaultPassword = generateRandomPassword(); // Generate a default password
        String salt = generateSalt(); // Generate a new salt
        String hashedPassword = hashPassword(defaultPassword, salt);
        
        Patron newPatron = new Patron(name, defaultPassword, libraryCardNum);
        newPatron.setSalt(salt);
        newPatron.setHashedPassword(hashedPassword);
        patrons.add(newPatron);
        
        savePatrons(); // Save changes to file
        System.out.println("Registered new patron: " + name + " with card number: " + libraryCardNum);
        return defaultPassword;
    }

    // Allows a user to change their password
    public boolean changePassword(String name, String newPassword) {
        for (Patron patron : patrons) {
            if (patron.getName().equals(name)) {
                String newSalt = generateSalt();
                patron.setSalt(newSalt);
                patron.setHashedPassword(hashPassword(newPassword, newSalt));
                patron.setPassword(newPassword);
                savePatrons();
                System.out.println("Password changed for user: " + name);
                return true;
            }
        }
        System.out.println("User not found for password change: " + name);
        return false;
    }

    // Generates a random secure password 
    private static String generateRandomPassword() {
        // Generate a more user-friendly password with 8 characters
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            // Using a slightly more restricted range for better readability
            // ASCII 48-57 (numbers), 65-90 (uppercase), 97-122 (lowercase)
            int type = random.nextInt(3);
            int charCode;
            if (type == 0) {
                charCode = random.nextInt(10) + 48; // numbers
            } else if (type == 1) {
                charCode = random.nextInt(26) + 65; // uppercase
            } else {
                charCode = random.nextInt(26) + 97; // lowercase
            }
            password.append((char) charCode);
        }
        return password.toString();
    }
    
    // Get patron by library card number
    public Patron getPatronByCardNumber(int cardNumber) {
        for (Patron patron : patrons) {
            if (patron.getLibaryCardNum() == cardNumber) {
                return patron;
            }
        }
        return null;
    }
    
    // Get patron by name
    public Patron getPatronByName(String name) {
        for (Patron patron : patrons) {
            if (patron.getName().equals(name)) {
                return patron;
            }
        }
        return null;
    }
    
    // Remove a patron by library card number
    public boolean removePatron(int cardNumber) {
        Iterator<Patron> iterator = patrons.iterator();
        boolean removed = false;
        
        while (iterator.hasNext()) {
            Patron patron = iterator.next();
            if (patron.getLibaryCardNum() == cardNumber) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        
        if (removed) {
            savePatrons();
        }
        return removed;
    }
}