package ru.almaz.caravelletravelsreborn.logic.exceptions.booking;

import ru.almaz.caravelletravelsreborn.logic.exceptions.BookingException;

public class BookingAlreadyExistException extends BookingException {
    public BookingAlreadyExistException(String message) {
        super(message);
    }
}
