package ru.almaz.caravelletravelsreborn.logic.exceptions.user;

import ru.almaz.caravelletravelsreborn.logic.exceptions.UserException;

public class InvalidCredentialsException extends UserException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
