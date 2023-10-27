package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingIsNotCreatedBySpecUserException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFilledException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.exceptions.user.NoPermissionsException;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;

public class BookingSetProcessedStatus extends UseCase<BookingSetProcessedStatus.InputValues, BookingSetProcessedStatus.OutputValues> {
    private final IBookingRepository repository;

    public BookingSetProcessedStatus(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (!input.fromUser.isPermissions()) {
            throw new NoPermissionsException("User have no permissions");
        }

        Booking booking = repository.findById(input.bookingId()).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (booking.getStatus() == Booking.BookingStatus.CREATING) {
            throw new BookingNotFilledException("Not all parameters field");
        }

        booking.setStatus(Booking.BookingStatus.PROCESSED);

        return new OutputValues(repository.save(booking.getId(), booking));
    }

    public record InputValues(Long bookingId, User fromUser) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
