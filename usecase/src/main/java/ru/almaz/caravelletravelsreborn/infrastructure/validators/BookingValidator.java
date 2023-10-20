package ru.almaz.caravelletravelsreborn.infrastructure.validators;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingValidationException;

public interface BookingValidator {
    void validate(Booking booking) throws BookingValidationException;
}
