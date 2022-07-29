package com.example.spring_security_with_angular;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationBean {

    private String message;

    @Override
    public String toString() {
        return String.format("Hello Bean message=%s", message);
    }
}
