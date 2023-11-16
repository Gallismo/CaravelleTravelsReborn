package ru.almaz.caravelletravelsreborn.presentation.infrastructure;

import ru.almaz.caravelletravelsreborn.presentation.model.BookingView;

public interface IAdminsNotifyProvider {
    void notifyBookingCreated(BookingView booking);
}
