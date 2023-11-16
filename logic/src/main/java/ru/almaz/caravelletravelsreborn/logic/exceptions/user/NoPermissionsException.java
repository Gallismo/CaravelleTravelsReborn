package ru.almaz.caravelletravelsreborn.logic.exceptions.user;

import ru.almaz.caravelletravelsreborn.logic.exceptions.UserException;

public class NoPermissionsException extends UserException {
    public NoPermissionsException(String message) {
        super(message);
    }
}
