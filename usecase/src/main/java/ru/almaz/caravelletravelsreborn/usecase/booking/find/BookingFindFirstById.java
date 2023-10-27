package ru.almaz.caravelletravelsreborn.usecase.booking.find;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingIsNotCreatedBySpecUserException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.exceptions.user.NoPermissionsException;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;

import java.util.List;

public class BookingFindFirstById extends UseCase<BookingFindFirstById.InputValues, BookingFindFirstById.OutputValues> {
    private final IBookingRepository repository;

    public BookingFindFirstById(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Booking booking = repository.findById(input.id).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (!(input.fromUser.isPermissions() || booking.isCreator(input.fromUser))) {
            throw new NoPermissionsException("User have no permissions");
        }

        return new OutputValues(booking);
    }

    public record InputValues(Long id, User fromUser) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
