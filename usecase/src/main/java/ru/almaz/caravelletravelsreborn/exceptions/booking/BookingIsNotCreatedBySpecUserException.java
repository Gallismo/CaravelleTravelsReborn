package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class BookingIsNotCreatedBySpecUserException extends UseCaseException {
    public BookingIsNotCreatedBySpecUserException(String message) {
        super(message);
    }
}
