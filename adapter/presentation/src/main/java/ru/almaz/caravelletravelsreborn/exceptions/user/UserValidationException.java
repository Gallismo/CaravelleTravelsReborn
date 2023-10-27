package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.PresentationException;

public class UserValidationException extends PresentationException {
    public UserValidationException(String message) {
        super(message);
    }
}
