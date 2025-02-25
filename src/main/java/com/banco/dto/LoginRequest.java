package com.banco.dto;

import lombok.Data;

// The @Data anotation automatically generate getters, setters, toString method
@Data
public class LoginRequest {

    private String userDni;
    private String password;

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
