package ru.almaz.caravelletravelsreborn.logic.exceptions.booking;

import ru.almaz.caravelletravelsreborn.logic.exceptions.BookingException;

public class BookingNotFoundException extends BookingException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
