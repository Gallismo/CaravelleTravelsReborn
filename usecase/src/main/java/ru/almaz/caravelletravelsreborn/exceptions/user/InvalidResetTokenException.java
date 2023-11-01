package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UserException;

public class InvalidResetTokenException extends UserException {
    public InvalidResetTokenException(String message) {
        super(message);
    }
}
