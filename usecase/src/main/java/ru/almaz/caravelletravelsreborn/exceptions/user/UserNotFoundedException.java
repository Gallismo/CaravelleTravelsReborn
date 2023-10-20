package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class UserNotFoundedException extends UseCaseException {
    public UserNotFoundedException(String message) {
        super(message);
    }
}
