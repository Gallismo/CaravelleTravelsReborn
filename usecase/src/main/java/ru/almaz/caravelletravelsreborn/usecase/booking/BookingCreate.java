package ru.almaz.caravelletravelsreborn.usecase.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingAlreadyCreatingException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.AdminsNotifyService;
import ru.almaz.caravelletravelsreborn.infrastructure.data.BookingIdGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.BookingRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.BookingValidator;

import java.util.Date;

public class BookingCreate extends UseCase<BookingCreate.InputValues, BookingCreate.OutputValues> {
    private final BookingRepository repository;
    private final BookingValidator validator;
    private final BookingIdGenerator idGenerator;
    private final AdminsNotifyService notifyService;

    public BookingCreate(BookingRepository repository, BookingValidator validator, BookingIdGenerator idGenerator, AdminsNotifyService notifyService) {
        this.repository = repository;
        this.validator = validator;
        this.idGenerator = idGenerator;
        this.notifyService = notifyService;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (repository.findFirstByUserIdAndStatus(input.userId(), Booking.BookingStatus.CREATING).isPresent()) {
            throw new BookingAlreadyCreatingException("Booking is already creating");
        }

        Booking booking = Booking.builder()
                .userId(input.userId())
                .date(input.date())
                .fromPlace(input.fromPlace())
                .toPlace(input.toPlace())
                .phone(input.phone())
                .passengerName(input.passengerName())
                .passengerCount(input.passengerCount())
                .build();
        validator.validate(booking);

        if (booking.isAllParametersField()) {
            notifyService.notifyBookingCreated(booking);
        }

        return new OutputValues(repository.save(idGenerator.generate(), booking));
    }

    public record InputValues(Long userId, Date date, String fromPlace, String toPlace, String phone, String passengerName, Integer passengerCount) implements UseCase.InputValues {}
    public record OutputValues(Booking booking) implements UseCase.OutputValues {}
}
