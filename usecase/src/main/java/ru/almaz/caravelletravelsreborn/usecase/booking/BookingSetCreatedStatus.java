package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingIsNotCreatedBySpecUserException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFilledException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;

public class BookingSetCreatedStatus extends UseCase<BookingSetCreatedStatus.InputValues, BookingSetCreatedStatus.OutputValues> {
    private final IBookingRepository repository;

    public BookingSetCreatedStatus(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Booking booking = repository.findById(input.bookingId()).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (!booking.isCreator(input.fromUser)) {
            throw new BookingIsNotCreatedBySpecUserException("User is not creator of this booking");
        }
        if (!booking.isAllParametersField()) {
            throw new BookingNotFilledException("Not all parameters field");
        }

        booking.setStatus(Booking.BookingStatus.CREATED);

        return new OutputValues(repository.save(booking.getId(), booking));
    }

    public record InputValues(Long bookingId, User fromUser) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
