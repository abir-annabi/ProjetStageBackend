package com.projet.app.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="dbuser")
public class DBUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;


    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }
 // Getter pour profile
    public Profile getProfile() {
        return profile;
    }

    // Setter pour profile
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    public String toString() {
		return this.getProfile().getRole();
    	
    }
    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
}
