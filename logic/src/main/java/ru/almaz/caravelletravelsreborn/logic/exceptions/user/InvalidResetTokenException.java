package ru.almaz.caravelletravelsreborn.logic.exceptions.user;

import ru.almaz.caravelletravelsreborn.logic.exceptions.UserException;

public class InvalidResetTokenException extends UserException {
    public InvalidResetTokenException(String message) {
        super(message);
    }
}
