package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.PresentationException;

public class BookingValidationException extends PresentationException {
    public final Reason reason;
    public BookingValidationException(String message, Reason reason) {
        super(message);
        this.reason = reason;
    }

    public enum Reason {
        DATE,
        PHONE,
        PASS_COUNT,
        ID
    }
}
