package com.banco.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name="users")

public class User {

    // Id of the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // UserPlate for transactions
    @Column(unique = true, name="user_plate", nullable = false)
    private String userPlate;

    // Phone number of the user
    @Column(unique = true, name="user_phone", nullable = false)
    private String userPhone;

    // User name
    @Column(name="user_name", nullable = false)
    private String userName;

    // User email
    @Column(name="user_email", nullable = false)
    private String userEmail;

    // DNI
    @Column(name="user_identification", nullable = false)
    private String userDni;

    @Column(name="user_password", nullable = false)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }




    public String getUserPlate() {
        return userPlate;
    }

    public void setUserPlate(String userPlate) {

        // Instance for the random number
        Random randomNumbers = new Random();

        // String builder for efficient concateniation
        StringBuilder plateBuilder =new StringBuilder();

        // Generate three random numbers
        for (int i = 0; i < 3; i++) {

            // Generate 9 to 1 random numbers
            int number = randomNumbers.nextInt(9)+1 ;
            plateBuilder.append(number);

        }

        // Get the first letter of user name
        String userName = getUserName();

        // Check if the user name is not null
        if (userName == null || userName.isEmpty()) {
            // Throw a exception
            throw new IllegalStateException("Username cannot be null or empty");
        }

        // Append the first letter of the user name to the plate
        plateBuilder.append(userName.charAt(0));

        // Assign the generated three digit string
        this.userPlate = plateBuilder.toString();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
