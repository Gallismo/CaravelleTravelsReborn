package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class BookingAlreadyCreatingException extends UseCaseException {
    public BookingAlreadyCreatingException(String message) {
        super(message);
    }
}
