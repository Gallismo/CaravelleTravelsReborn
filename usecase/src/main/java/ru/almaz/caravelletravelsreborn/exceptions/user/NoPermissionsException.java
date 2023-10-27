package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class NoPermissionsException extends UseCaseException {
    public NoPermissionsException(String message) {
        super(message);
    }
}
