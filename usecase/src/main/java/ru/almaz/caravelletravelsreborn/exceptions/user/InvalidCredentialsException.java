package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UserException;

public class InvalidCredentialsException extends UserException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
