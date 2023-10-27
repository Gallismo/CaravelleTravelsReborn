package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingAlreadyExistException;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingIdGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;

import java.util.Date;

public class BookingCreate extends UseCase<BookingCreate.InputValues, BookingCreate.OutputValues> {
    private final IBookingRepository repository;
    private final IBookingIdGenerator idGenerator;

    public BookingCreate(IBookingRepository repository, IBookingIdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (repository.findFirstByUserIdAndStatusNot(input.fromUser.getId(), Booking.BookingStatus.PROCESSED).isPresent()) {
            throw new BookingAlreadyExistException("Booking is already exist");
        }

        Booking booking = Booking.builder()
                .userId(input.fromUser.getId())
                .date(input.booking.getDate())
                .fromPlace(input.booking.getFromPlace())
                .toPlace(input.booking.getToPlace())
                .phone(input.booking.getPhone())
                .passengerName(input.booking.getPassengerName())
                .passengerCount(input.booking.getPassengerCount())
                .build();

        return new OutputValues(repository.save(idGenerator.generate(), booking));
    }

    public record InputValues(Booking booking, User fromUser) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
