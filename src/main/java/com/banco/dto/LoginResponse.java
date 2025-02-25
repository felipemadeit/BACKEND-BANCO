package com.banco.dto;

import lombok.Data;

// The @Data anotation automatically generate getters, setters, toString method
@Data
public class LoginResponse {

    private String token;
    private String userDni;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }
}
