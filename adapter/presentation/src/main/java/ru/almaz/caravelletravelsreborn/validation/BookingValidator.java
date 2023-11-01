package ru.almaz.caravelletravelsreborn.validation;

import ru.almaz.caravelletravelsreborn.dto.input.CreateBookingDTO;
import ru.almaz.caravelletravelsreborn.dto.input.UpdateBookingDTO;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingValidationException;

public class BookingValidator {
    private static final String DATE_REGEX = "\\d{2}\\.\\d{2}\\.\\d{4}";
    private static final String PHONE_REGEX = "(^\\+?\\d-\\d{3}-\\d{3}-\\d{2}-\\d{2}$|^\\+?\\d \\d{3} \\d{3} \\d{2} \\d{2}$|^\\+?\\d{11}$)";
    private static final String PASS_COUNT_REGEX = "\\d";
    private static final String ID_REGEX = "\\d+";


    public static void validate(CreateBookingDTO dto) {
        validate(dto.date, dto.phone, dto.passengerCount);
    }

    public static void validate(UpdateBookingDTO dto) {
        validate(dto.date, dto.phone, dto.passengerCount);
    }

    public static void validate(String date, String phone, String passengerCount) {
        if (date != null && !date.matches(DATE_REGEX)) {
            throw new BookingValidationException("Date invalid", BookingValidationException.Reason.DATE);
        }

        if (phone != null && !phone.matches(PHONE_REGEX)) {
            throw new BookingValidationException("Phone invalid", BookingValidationException.Reason.PHONE);
        }

        if (passengerCount != null && !passengerCount.matches(PASS_COUNT_REGEX)) {
            throw new BookingValidationException("Passenger count invalid", BookingValidationException.Reason.PASS_COUNT);
        }
    }

    public static void validateId(String bookingId) {
        if (!bookingId.matches(ID_REGEX)) {
            throw new BookingValidationException("ID is not Long", BookingValidationException.Reason.ID);
        }
    }
}
