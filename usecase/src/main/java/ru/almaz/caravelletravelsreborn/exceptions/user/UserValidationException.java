package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class UserValidationException extends UseCaseException {
    public UserValidationException(String message) {
        super(message);
    }
}
