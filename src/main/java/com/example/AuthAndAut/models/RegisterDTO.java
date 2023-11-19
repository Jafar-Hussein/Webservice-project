package com.example.AuthAndAut.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class RegisterDTO {
    private String username;
    private String password;

    public RegisterDTO() {
        super();
    }

    public RegisterDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString(){
        return "Registration complete:\n username: " + this.username + "\nPassword: " + this.password;
    }

}
