package ru.almaz.caravelletravelsreborn.domain.entities.booking;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;

import java.util.Date;

public class Booking {
    private Long id;
    private Long userId;
    private Date date;
    private String fromPlace;
    private String toPlace;
    private String phone;
    private String passengerName;
    private Integer passengerCount;
    private BookingStatus status = BookingStatus.CREATING;

    private final Date creationDate;

    private Booking(Long id, Long userId, Date date, String fromPlace, String toPlace, String phone, String passengerName, Integer passengerCount, BookingStatus status, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.phone = phone;
        this.passengerName = passengerName;
        this.passengerCount = passengerCount;
        this.status = status;
        this.creationDate = creationDate;
    }

    public boolean isAllParametersField() {
        return userId != null && date != null && fromPlace != null && toPlace != null
                && phone != null && passengerName != null && passengerCount != null;
    }

    public void merge(Booking otherBooking) {
        if (otherBooking.date != null) this.date = otherBooking.date;
        if (otherBooking.fromPlace != null) this.fromPlace = otherBooking.fromPlace;
        if (otherBooking.toPlace != null) this.toPlace = otherBooking.toPlace;
        if (otherBooking.phone != null) this.phone = otherBooking.phone;
        if (otherBooking.passengerName != null) this.passengerName = otherBooking.passengerName;
        if (otherBooking.passengerCount != null) this.passengerCount = otherBooking.passengerCount;
    }

    public boolean isCreator(User user) {
        return userId.equals(user.getId());
    }

    public static BookingBuilder builder() {
        return new BookingBuilder();
    }

    public static class BookingBuilder {
        private Long id;
        private Long userId;
        private Date date;
        private String fromPlace;
        private String toPlace;
        private String phone;
        private String passengerName;
        private Integer passengerCount;
        private BookingStatus status = BookingStatus.CREATING;
        private Date creationDate = new Date();

        public BookingBuilder() {}

        public BookingBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public BookingBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public BookingBuilder date(Date date) {
            this.date = date;
            return this;
        }
        public BookingBuilder fromPlace(String fromPlace) {
            this.fromPlace = fromPlace;
            return this;
        }
        public BookingBuilder toPlace(String toPlace) {
            this.toPlace = toPlace;
            return this;
        }
        public BookingBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public BookingBuilder passengerName(String passengerName) {
            this.passengerName = passengerName;
            return this;
        }
        public BookingBuilder passengerCount(Integer passengerCount) {
            this.passengerCount = passengerCount;
            return this;
        }
        public BookingBuilder status(BookingStatus status) {
            this.status = status;
            return this;
        }
        public BookingBuilder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }
        public Booking build() {
            return new Booking(id, userId, date, fromPlace, toPlace, phone, passengerName, passengerCount, status, creationDate);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public enum BookingStatus {
        CREATING, CREATED, PROCESSED;
    }
}
