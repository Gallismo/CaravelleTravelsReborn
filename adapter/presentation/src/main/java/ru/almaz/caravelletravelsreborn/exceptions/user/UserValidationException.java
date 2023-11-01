package ru.almaz.caravelletravelsreborn.exceptions.user;

import ru.almaz.caravelletravelsreborn.exceptions.PresentationException;

public class UserValidationException extends PresentationException {
    public final Reason reason;
    public UserValidationException(String message, Reason reason) {
        super(message);
        this.reason = reason;
    }

    public enum Reason {
        EMAIL,
        PHONE,
        PASSWORD
    }
}
