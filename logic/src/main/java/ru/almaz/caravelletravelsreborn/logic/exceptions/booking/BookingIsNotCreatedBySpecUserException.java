package ru.almaz.caravelletravelsreborn.logic.exceptions.booking;

import ru.almaz.caravelletravelsreborn.logic.exceptions.BookingException;

public class BookingIsNotCreatedBySpecUserException extends BookingException {
    public BookingIsNotCreatedBySpecUserException(String message) {
        super(message);
    }
}
