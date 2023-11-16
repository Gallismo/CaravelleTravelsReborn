package ru.almaz.caravelletravelsreborn.logic.exceptions.user;

import ru.almaz.caravelletravelsreborn.logic.exceptions.UserException;

public class EmailAlreadyUsedException extends UserException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
