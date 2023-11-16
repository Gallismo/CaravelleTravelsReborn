package ru.almaz.caravelletravelsreborn.logic.exceptions.user;

import ru.almaz.caravelletravelsreborn.logic.exceptions.UserException;

public class UserNotFoundedException extends UserException {
    public UserNotFoundedException(String message) {
        super(message);
    }
}
