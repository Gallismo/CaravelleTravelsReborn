package ru.almaz.caravelletravelsreborn.infrastructure;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;
import ru.almaz.caravelletravelsreborn.model.BookingView;

public interface IAdminsNotifyProvider {
    void notifyBookingCreated(BookingView booking);
}
