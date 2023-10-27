package ru.almaz.caravelletravelsreborn.controller;


import ru.almaz.caravelletravelsreborn.dto.CreateBookingDTO;
import ru.almaz.caravelletravelsreborn.dto.UpdateBookingDTO;
import ru.almaz.caravelletravelsreborn.model.BookingView;
import ru.almaz.caravelletravelsreborn.model.UserView;
import ru.almaz.caravelletravelsreborn.usecase.booking.BookingCreate;
import ru.almaz.caravelletravelsreborn.usecase.booking.BookingSetCreatedStatus;
import ru.almaz.caravelletravelsreborn.usecase.booking.BookingSetProcessedStatus;
import ru.almaz.caravelletravelsreborn.usecase.booking.BookingUpdate;

import java.text.ParseException;

public class BookingController {
    private final BookingCreate bookingCreate;
    private final BookingUpdate bookingUpdate;
    private final BookingSetCreatedStatus bookingSetCreatedStatus;
    private final BookingSetProcessedStatus bookingSetProcessedStatus;
    private final IAdminsNotifyProvider notifyProvider;

    public BookingController(BookingCreate bookingCreate, BookingUpdate bookingUpdate, BookingSetCreatedStatus bookingSetCreatedStatus, BookingSetProcessedStatus bookingSetProcessedStatus, IAdminsNotifyProvider notifyProvider) {
        this.bookingCreate = bookingCreate;
        this.bookingUpdate = bookingUpdate;
        this.bookingSetCreatedStatus = bookingSetCreatedStatus;
        this.bookingSetProcessedStatus = bookingSetProcessedStatus;
        this.notifyProvider = notifyProvider;
    }

    public BookingView createBooking(UserView userView, CreateBookingDTO dto) throws ParseException {
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

    public BookingView updateBooking(UserView userView, UpdateBookingDTO dto) throws ParseException {
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
}
