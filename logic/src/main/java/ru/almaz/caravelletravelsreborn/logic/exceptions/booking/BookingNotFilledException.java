package ru.almaz.caravelletravelsreborn.logic.exceptions.booking;

import ru.almaz.caravelletravelsreborn.logic.exceptions.BookingException;

public class BookingNotFilledException extends BookingException {
    public BookingNotFilledException(String message) {
        super(message);
    }
}
