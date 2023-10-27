package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class BookingAlreadyExistException extends UseCaseException {
    public BookingAlreadyExistException(String message) {
        super(message);
    }
}
