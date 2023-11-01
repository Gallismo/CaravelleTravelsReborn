package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.BookingException;

public class BookingIsNotCreatedBySpecUserException extends BookingException {
    public BookingIsNotCreatedBySpecUserException(String message) {
        super(message);
    }
}
