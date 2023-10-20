package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingNotFoundException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.BookingRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.BookingValidator;

public class BookingSetStatus extends UseCase<BookingSetStatus.InputValues, BookingSetStatus.OutputValues> {
    private final BookingRepository repository;
    private final BookingValidator validator;

    public BookingSetStatus(BookingRepository repository, BookingValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Booking booking = repository.findById(input.id()).orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        booking.setStatus(input.status());

        return new OutputValues(repository.save(booking.getId(), booking));
    }

    public record InputValues(Long id, Booking.BookingStatus status) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
