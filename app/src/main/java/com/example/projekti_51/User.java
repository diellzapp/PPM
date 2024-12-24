package com.example.projekti_51;

public class User {
    public String name;
    public String email;
    public String username;
    public String uid;

    public boolean emailVerified;

    // Constructor to initialize user object
    public User(String name, String email, String username, String uid,  boolean emailVerified) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.uid = uid;
        this.emailVerified = emailVerified;
    }

    // Default constructor required for Firebase
    public User() {}
}
