package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class EmailAlreadyUsedException extends UseCaseException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
