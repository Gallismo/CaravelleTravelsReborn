package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;

public class BookingNotFoundException extends UseCaseException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
