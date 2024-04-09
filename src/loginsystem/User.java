/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author Mukarram
 */
/**
 * Represents a user entity with basic information.
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password; // This will be stored as an encrypted hash.
    private String email;

    /**
     * Constructs a new User object with provided details.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param username  The username of the user.
     * @param password  The password of the user (encrypted hash).
     * @param email     The email address of the user.
     */
    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password; // Note: Should be encrypted before setting.
        this.email = email;
    }

    // Getters
    /**
     * Gets the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user (stored as an encrypted hash).
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }
}
