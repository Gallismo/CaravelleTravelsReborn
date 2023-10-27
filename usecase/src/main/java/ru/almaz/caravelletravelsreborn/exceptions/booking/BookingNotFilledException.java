package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class BookingNotFilledException extends UseCaseException {
    public BookingNotFilledException(String message) {
        super(message);
    }
}
