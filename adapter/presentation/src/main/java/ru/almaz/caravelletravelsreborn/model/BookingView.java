package ru.almaz.caravelletravelsreborn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingView {
    public Long id;
    public Long userId;
    public String date;
    public String fromPlace;
    public String toPlace;
    public String phone;
    public String passengerName;
    public Integer passengerCount;
    public BookingStatus status;

    public BookingView(Long id, Long userId, String date, String fromPlace, String toPlace, String phone, String passengerName, Integer passengerCount) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.phone = phone;
        this.passengerName = passengerName;
        this.passengerCount = passengerCount;
    }

    private static SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    public Booking toBooking() throws ParseException {
        return Booking.builder()
                .userId(userId)
                .date(dateFormatter().parse(date))
                .fromPlace(fromPlace)
                .toPlace(toPlace)
                .phone(phone)
                .passengerName(passengerName)
                .passengerCount(passengerCount)
                .build();
    }

    public static BookingView fromBooking(Booking booking) {
        BookingView view = new BookingView();
        view.id = booking.getId();
        view.userId = booking.getUserId();
        view.date = dateFormatter().format(booking.getDate());
        view.fromPlace = booking.getFromPlace();
        view.toPlace = booking.getToPlace();
        view.phone = booking.getPhone();
        view.passengerName = booking.getPassengerName();
        view.passengerCount = booking.getPassengerCount();

        switch (booking.getStatus()) {
            case CREATING -> view.status = BookingStatus.CREATING;
            case CREATED -> view.status = BookingStatus.CREATED;
            case PROCESSED -> view.status = BookingStatus.PROCESSED;
        }
        return view;
    }


    public enum BookingStatus {
        CREATING, CREATED, PROCESSED
    }
}
