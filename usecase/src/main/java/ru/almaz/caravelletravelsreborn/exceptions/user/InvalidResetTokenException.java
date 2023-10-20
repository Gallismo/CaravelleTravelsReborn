package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class InvalidResetTokenException extends UseCaseException {
    public InvalidResetTokenException(String message) {
        super(message);
    }
}
