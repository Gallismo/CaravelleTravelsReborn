package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class BookingValidationException extends UseCaseException {
    public BookingValidationException(String message) {
        super(message);
    }
}
