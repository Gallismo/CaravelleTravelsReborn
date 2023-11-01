package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UserException;

public class NoPermissionsException extends UserException {
    public NoPermissionsException(String message) {
        super(message);
    }
}
