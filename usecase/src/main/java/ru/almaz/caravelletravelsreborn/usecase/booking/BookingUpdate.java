package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.AdminsNotifyService;
import ru.almaz.caravelletravelsreborn.infrastructure.data.BookingRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.BookingValidator;

import java.util.Date;

public class BookingUpdate extends UseCase<BookingUpdate.InputValues, BookingUpdate.OutputValues> {
    private final BookingRepository repository;
    private final BookingValidator validator;
    private final AdminsNotifyService notifyService;

    public BookingUpdate(BookingRepository repository, BookingValidator validator, AdminsNotifyService notifyService) {
        this.repository = repository;
        this.validator = validator;
        this.notifyService = notifyService;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Booking booking = repository.findById(input.id()).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        Booking updatedBooking = Booking.builder()
                .date(input.date())
                .fromPlace(input.fromPlace())
                .toPlace(input.toPlace())
                .phone(input.phone())
                .passengerName(input.passengerName())
                .passengerCount(input.passengerCount())
                .build();

        booking.merge(updatedBooking);
        validator.validate(booking);

        if (booking.isAllParametersField()) {
            notifyService.notifyBookingCreated(booking);
        }

        return new OutputValues(repository.save(booking.getId(), booking));
    }

    public record InputValues(Long id, Date date, String fromPlace, String toPlace, String phone, String passengerName, Integer passengerCount) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
