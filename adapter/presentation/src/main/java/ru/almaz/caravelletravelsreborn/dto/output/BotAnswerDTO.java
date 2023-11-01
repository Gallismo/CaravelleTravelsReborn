package ru.almaz.caravelletravelsreborn.dto.output;

import ru.almaz.caravelletravelsreborn.model.BookingView;
import ru.almaz.caravelletravelsreborn.model.UserView;

import java.util.List;

public class BotAnswerDTO {
    public List<String> answer;
    public BookingView bookingView;
    public UserView userView;
}
