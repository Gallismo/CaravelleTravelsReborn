package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UserException;

public class UserNotFoundedException extends UserException {
    public UserNotFoundedException(String message) {
        super(message);
    }
}
