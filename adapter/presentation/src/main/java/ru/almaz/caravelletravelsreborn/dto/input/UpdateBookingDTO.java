package ru.almaz.caravelletravelsreborn.dto.input;

import lombok.Builder;

@Builder
public class UpdateBookingDTO {
    public Long id;
    public String date;
    public String fromPlace;
    public String toPlace;
    public String phone;
    public String passengerName;
    public String passengerCount;

    public UpdateBookingDTO() {
    }
}
