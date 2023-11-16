package ru.almaz.caravelletravelsreborn.presentation.dto.output;

import ru.almaz.caravelletravelsreborn.presentation.model.BookingView;
import ru.almaz.caravelletravelsreborn.presentation.model.UserView;

import java.util.List;

public class BotAnswerDTO {
    public List<String> text;
    public BookingView bookingView;
    public UserView userView;
}
