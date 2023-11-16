package ru.almaz.caravelletravelsreborn.logic.infrastructure.data;

import ru.almaz.caravelletravelsreborn.domain.entities.booking.Booking;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBookingRepository {
//  Find one booking by 1 parameter
    Optional<Booking> findById(Long id);
    Optional<Booking> findFirstByUserId(Long userId);
    Optional<Booking> findFirstByDate(Date date);
    Optional<Booking> findFirstByPhone(String phone);
    Optional<Booking> findFirstByName(String name);
    Optional<Booking> findFirstByStatus(Booking.BookingStatus status);
//  Find one booking by 2 parameter
    Optional<Booking> findFirstByUserIdAndStatus(Long userId, Booking.BookingStatus status);
    Optional<Booking> findFirstByUserIdAndStatusNot(Long userId, Booking.BookingStatus status);
//  Find X bookings by 1 parameter
    List<Booking> findByUserId(Long userId, Integer limit, Integer offset);
    List<Booking> findLastByUserId(Long userId, Integer limit);
    List<Booking> findByDate(Date date, Integer limit, Integer offset);
    List<Booking> findByPhone(String phone, Integer limit, Integer offset);
    List<Booking> findByName(String name, Integer limit, Integer offset);
    List<Booking> findByStatus(Booking.BookingStatus status, Integer limit, Integer offset);
//  Find X bookings by 2 parameters
    List<Booking> findByStatusAndDate(Date date, Booking.BookingStatus status, Integer limit, Integer offset);
//  Save or update user
    Booking save(Long id, Booking booking);
    Booking delete(Booking booking);
}
