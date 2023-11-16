package ru.almaz.caravelletravelsreborn.presentation.dto.input;

import lombok.Builder;

import java.util.Date;

@Builder
public class CreateBookingDTO {
    public String date;
    public String fromPlace;
    public String toPlace;
    public String phone;
    public String passengerName;
    public String passengerCount;

    public CreateBookingDTO() {
    }

    public CreateBookingDTO(String date, String fromPlace, String toPlace, String phone, String passengerName, String passengerCount) {
        this.date = date;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.phone = phone;
        this.passengerName = passengerName;
        this.passengerCount = passengerCount;
    }
}
