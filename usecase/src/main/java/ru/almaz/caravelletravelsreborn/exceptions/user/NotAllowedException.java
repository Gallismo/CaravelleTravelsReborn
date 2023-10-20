package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class NotAllowedException extends UseCaseException {
    public NotAllowedException(String message) {
        super(message);
    }
}
