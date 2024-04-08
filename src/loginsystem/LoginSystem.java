    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Mukarram
 */

public class LoginSystem {
    private Map<String, User> users = new HashMap<>();
    private final String filePath = "users.txt"; // Updated file path
    private final String delimiter = ";";
    // Method to check if a password is listed in the "dictbadpass.txt" file
    private boolean isPasswordInvalid(String password) {
        final String dictBadPassFilePath = "dictbadpass.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(dictBadPassFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(password)) {
                    return true; // Password is found in the list of invalid passwords
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }

        return false; // Password is not found in the list of invalid passwords
    }
    public LoginSystem() {
        loadUsersFromFile();
  
    }

    public boolean registerUser(String firstName, String lastName, String username, String password, String email) {
    if (!isUsernameUnique(username) || containsDelimiter(firstName, lastName, username, password, email)) {
        return false;
    }
    
    if (isPasswordInvalid(password)) {
            System.err.println("Invalid password. Please choose a different one.");
    }
        // Encrypt the password
    String encryptedPassword = encryptPassword(password);
    User newUser = new User(firstName, lastName, username, encryptedPassword, email);
    users.put(username, newUser);
    saveUserToFile(newUser);
    return true;
}

    private boolean isUsernameUnique(String username) {
        return !users.containsKey(username);
    }

    private boolean containsDelimiter(String... args) {
        for (String arg : args) {
            if (arg.contains(delimiter)) {
                System.err.println("Input cannot contain the delimiter '" + delimiter + "'");
                return true;
            }
        }
        return false;
    }

    private void saveUserToFile(User user) {
    try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
        // Assuming User class has methods: getFirstName(), getLastName(), getUsername(), getPassword(), getEmail()
        out.println(user.getFirstName() + delimiter + user.getLastName() + delimiter + user.getUsername() + delimiter + user.getPassword() + delimiter + user.getEmail());
    } catch (IOException e) {
        System.err.println("An error occurred while writing to the file: " + e.getMessage());
    }
}


    private void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(delimiter);
                if (userDetails.length == 5) {
                    users.put(userDetails[0], new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuilder encryptedPassword = new StringBuilder();
            for (int i = 0; i < byteData.length; ++i) {
                encryptedPassword.append(Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return encryptedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }
    private boolean isPasswordStrong(String password) {
        if (password.length() < 8) {
            return false; // Check for minimum length of 8 characters
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                // Assuming any character that is not uppercase, lowercase, or digit is a special character
                hasSpecialChar = true;
            }
        }

        // Check if all conditions are met
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}
