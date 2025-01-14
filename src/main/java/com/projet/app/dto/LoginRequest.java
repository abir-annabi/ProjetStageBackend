//DTO contenant les informations nécessaires pour authentifier un utilisateur (email et mot de passe).
package com.projet.app.dto;

public class LoginRequest {

    private String email;
    private String password;

    // Constructor with all arguments
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

}