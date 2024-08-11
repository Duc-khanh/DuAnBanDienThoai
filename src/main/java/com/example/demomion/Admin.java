package com.example.demomion;

public class Admin extends User {
    private String role = "Admin";

    public Admin(String username, String password,String email,String phoneNumber, String role) {
        super(username,password,email,phoneNumber,role);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
