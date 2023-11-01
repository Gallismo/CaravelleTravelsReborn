package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.BookingException;

public class BookingNotFilledException extends BookingException {
    public BookingNotFilledException(String message) {
        super(message);
    }
}
