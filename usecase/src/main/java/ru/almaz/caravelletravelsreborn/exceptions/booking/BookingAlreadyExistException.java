package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.BookingException;

public class BookingAlreadyExistException extends BookingException {
    public BookingAlreadyExistException(String message) {
        super(message);
    }
}
