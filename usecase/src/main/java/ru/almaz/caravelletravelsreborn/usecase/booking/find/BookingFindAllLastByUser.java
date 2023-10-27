package ru.almaz.caravelletravelsreborn.usecase.booking.find;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IBookingRepository;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;

import java.util.List;

public class BookingFindAllLastByUser extends UseCase<BookingFindAllLastByUser.InputValues, BookingFindAllLastByUser.OutputValues> {
    private final IBookingRepository repository;

    public BookingFindAllLastByUser(IBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<Booking> bookings = repository.findLastByUserId(input.user.getId(), input.limit);

        return new OutputValues(bookings);
    }

    public record InputValues(User user, Integer limit) implements UseCase.InputValues {}
    public record OutputValues(List<Booking> booking) implements UseCase.OutputValues {}
}
