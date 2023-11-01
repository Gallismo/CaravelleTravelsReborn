package ru.almaz.caravelletravelsreborn.exceptions.booking;

import ru.almaz.caravelletravelsreborn.exceptions.BookingException;

public class BookingCommittedException extends BookingException {
    public BookingCommittedException(String message) {
        super(message);
    }
}
