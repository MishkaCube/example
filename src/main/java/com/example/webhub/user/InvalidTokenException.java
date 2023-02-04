package com.example.webhub.user;

import javax.xml.bind.ValidationException;

public class InvalidTokenException extends ValidationException {

    private static final String errorMessage = "Токен администратора недействителен.";

    public InvalidTokenException() {
        super(errorMessage);
    }

}
