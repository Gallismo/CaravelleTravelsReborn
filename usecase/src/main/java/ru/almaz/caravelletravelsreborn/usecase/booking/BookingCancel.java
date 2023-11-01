package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingCommittedException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingIsNotCreatedBySpecUserException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.exceptions.user.NoPermissionsException;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;

public class BookingCancel extends UseCase<BookingCancel.InputValues, BookingCancel.OutputValues> {
    private final IBookingRepository repository;

    public BookingCancel(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Booking booking = repository.findById(input.bookingId()).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (!booking.isCreator(input.fromUser)) {
            throw new BookingIsNotCreatedBySpecUserException("User is not creator of this booking");
        }
        if (booking.getStatus() != Booking.BookingStatus.CREATING) {
            throw new BookingCommittedException("Booking already committed");
        }

        return new OutputValues(repository.delete(booking));
    }

    public record InputValues(Long bookingId, User fromUser) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
