package ru.almaz.caravelletravelsreborn.logic.exceptions.booking;

import ru.almaz.caravelletravelsreborn.logic.exceptions.BookingException;

public class BookingCommittedException extends BookingException {
    public BookingCommittedException(String message) {
        super(message);
    }
}
