package ru.almaz.caravelletravelsreborn.infrastructure;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;

public interface AdminsNotifyService {
    void notifyBookingCreated(Booking booking);
}
