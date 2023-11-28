package com.example.AuthAndAut.models;

import lombok.Data;

@Data
public class RegisterDTO {

    private String username;
    private String password;
    private boolean isAdmin;

    public RegisterDTO() {
        super();
    }

    public RegisterDTO(String username, String password, boolean isAdmin) {
        super();
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Registration complete:\n username: " + this.username + "\nPassword: " + this.password;
    }
}
