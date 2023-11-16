package ru.almaz.caravelletravelsreborn.presentation.controller;


import ru.almaz.caravelletravelsreborn.presentation.dto.input.CreateBookingDTO;
import ru.almaz.caravelletravelsreborn.presentation.dto.input.UpdateBookingDTO;
import ru.almaz.caravelletravelsreborn.presentation.infrastructure.IAdminsNotifyProvider;
import ru.almaz.caravelletravelsreborn.logic.usecase.booking.*;
import ru.almaz.caravelletravelsreborn.presentation.model.BookingView;
import ru.almaz.caravelletravelsreborn.presentation.model.UserView;
import ru.almaz.caravelletravelsreborn.logic.usecase.booking.find.BookingFindFirstById;
import ru.almaz.caravelletravelsreborn.presentation.validation.BookingValidator;

public class BookingController {
    private final BookingCreate bookingCreate;
    private final BookingUpdate bookingUpdate;
    private final BookingSetCreatedStatus bookingSetCreatedStatus;
    private final BookingSetProcessedStatus bookingSetProcessedStatus;
    private final BookingCancel bookingCancel;
    private final BookingFindFirstById bookingFindFirstById;
    private final IAdminsNotifyProvider notifyProvider;

    public BookingController(BookingCreate bookingCreate, BookingUpdate bookingUpdate, BookingSetCreatedStatus bookingSetCreatedStatus, BookingSetProcessedStatus bookingSetProcessedStatus, BookingCancel bookingCancel, BookingFindFirstById bookingFindFirstById, IAdminsNotifyProvider notifyProvider) {
        this.bookingCreate = bookingCreate;
        this.bookingUpdate = bookingUpdate;
        this.bookingSetCreatedStatus = bookingSetCreatedStatus;
        this.bookingSetProcessedStatus = bookingSetProcessedStatus;
        this.bookingCancel = bookingCancel;
        this.bookingFindFirstById = bookingFindFirstById;
        this.notifyProvider = notifyProvider;
    }

    public BookingView createBooking(UserView userView, CreateBookingDTO dto) {
        BookingValidator.validate(dto);

        BookingView bookingView = BookingView.builder()
                .date(dto.date)
                .fromPlace(dto.fromPlace)
                .toPlace(dto.toPlace)
                .phone(dto.phone)
                .passengerName(dto.passengerName)
                .passengerCount(dto.passengerCount)
                .build();

        BookingCreate.InputValues input = new BookingCreate.InputValues(bookingView.toBooking(), userView.toUser());
        return BookingView.fromBooking(bookingCreate.execute(input).booking());
    }

    public BookingView updateBooking(UserView userView, UpdateBookingDTO dto) {
        BookingValidator.validate(dto);

        BookingView bookingView = BookingView.builder()
                .id(dto.id)
                .date(dto.date)
                .fromPlace(dto.fromPlace)
                .toPlace(dto.toPlace)
                .phone(dto.phone)
                .passengerName(dto.passengerName)
                .passengerCount(dto.passengerCount)
                .build();

        BookingUpdate.InputValues input = new BookingUpdate.InputValues(bookingView.toBooking(), userView.toUser());
        return BookingView.fromBooking(bookingUpdate.execute(input).booking());
    }

    public BookingView setCreatedStatusBooking(UserView userView, Long bookingId) {
        BookingSetCreatedStatus.InputValues input = new BookingSetCreatedStatus.InputValues(bookingId, userView.toUser());

        BookingView view = BookingView.fromBooking(bookingSetCreatedStatus.execute(input).booking());
        notifyProvider.notifyBookingCreated(view);

        return view;
    }

    public BookingView setProcessedStatusBooking(UserView userView, Long bookingId) {
        BookingSetProcessedStatus.InputValues input = new BookingSetProcessedStatus.InputValues(bookingId, userView.toUser());

        return BookingView.fromBooking(bookingSetProcessedStatus.execute(input).booking());
    }

    public BookingView cancelBooking(UserView userView, Long bookingId) {
        BookingCancel.InputValues input = new BookingCancel.InputValues(bookingId, userView.toUser());

        return BookingView.fromBooking(bookingCancel.execute(input).booking());
    }

    public BookingView findBookingById(UserView userView, Long bookingId) {
        BookingFindFirstById.InputValues input = new BookingFindFirstById.InputValues(bookingId, userView.toUser());

        return BookingView.fromBooking(bookingFindFirstById.execute(input).booking());
    }
}
