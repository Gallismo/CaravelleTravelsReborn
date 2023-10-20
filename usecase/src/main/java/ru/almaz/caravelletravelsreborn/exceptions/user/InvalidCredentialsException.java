package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class InvalidCredentialsException extends UseCaseException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
