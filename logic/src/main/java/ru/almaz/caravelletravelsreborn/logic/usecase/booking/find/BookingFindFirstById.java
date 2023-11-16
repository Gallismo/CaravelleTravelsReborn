package ru.almaz.caravelletravelsreborn.logic.usecase.booking.find;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.NoPermissionsException;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;

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
