package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UserException;

public class EmailAlreadyUsedException extends UserException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
