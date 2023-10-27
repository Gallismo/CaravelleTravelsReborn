package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingIsNotCreatedBySpecUserException;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;

import java.util.Date;

public class BookingUpdate extends UseCase<BookingUpdate.InputValues, BookingUpdate.OutputValues> {
    private final IBookingRepository repository;

    public BookingUpdate(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Booking booking = repository.findById(input.booking.getId()).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (!booking.isCreator(input.fromUser)) {
            throw new BookingIsNotCreatedBySpecUserException("User is not creator of booking");
        }

        Booking updatedBooking = Booking.builder()
                .date(input.booking.getDate())
                .fromPlace(input.booking.getFromPlace())
                .toPlace(input.booking.getToPlace())
                .phone(input.booking.getPhone())
                .passengerName(input.booking.getPassengerName())
                .passengerCount(input.booking.getPassengerCount())
                .build();

        booking.merge(updatedBooking);

        return new OutputValues(repository.save(booking.getId(), booking));
    }

    public record InputValues(Booking booking, User fromUser) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
