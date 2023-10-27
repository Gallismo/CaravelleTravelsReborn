package ru.almaz.caravelletravelsreborn.bot;

import ru.almaz.caravelletravelsreborn.controller.BookingController;
import ru.almaz.caravelletravelsreborn.controller.UserController;
import ru.almaz.caravelletravelsreborn.dto.AnswerAndBookingDTO;
import ru.almaz.caravelletravelsreborn.dto.CreateBookingDTO;
import ru.almaz.caravelletravelsreborn.dto.UpdateBookingDTO;
import ru.almaz.caravelletravelsreborn.exceptions.booking.BookingAlreadyExistException;
import ru.almaz.caravelletravelsreborn.model.UserView;

import java.text.ParseException;
import java.util.List;

public class BotService {
    private final IBotTextProvider textProvider;
    private final UserController userController;
    private final BookingController bookingController;

    public BotService(IBotTextProvider textProvider, UserController userController, BookingController bookingController) {
        this.textProvider = textProvider;
        this.userController = userController;
        this.bookingController = bookingController;
    }

    public List<String> getCommandByIdentifier(BotCommands command) {
        return textProvider.get(command);
    }

    public List<String> getExceptionByIdentifier(BotExceptions exception) {
        return textProvider.get(exception);
    }

    public UserView createNewUser() {
        return userController.createUser(new UserView());
    }

    public List<String> startCommand() {
        return textProvider.get(BotCommands.START);
    }

    public AnswerAndBookingDTO bookingCommand(UserView userView) {
        AnswerAndBookingDTO result = new AnswerAndBookingDTO();
        try {
            result.bookingView = bookingController.createBooking(userView, new CreateBookingDTO());
            result.answer = textProvider.get(BotCommands.BOOKING);
            return result;
        } catch (BookingAlreadyExistException e) {
            result.answer = textProvider.get(BotExceptions.BOOKING_EXIST);
            return result;
        } catch (ParseException e) {
            result.answer = textProvider.get(BotExceptions.INVALID_DATE_STRING);
            return result;
        }
    }

    public AnswerAndBookingDTO fillBooking(UserView userView, UpdateBookingDTO dto) {
        AnswerAndBookingDTO result = new AnswerAndBookingDTO();
        try {
            result.bookingView = bookingController.updateBooking(userView, dto);
            result.answer = textProvider.get(BotCommands.FIll_BOOKING);
            return result;
        } catch (ParseException e) {
            result.answer = textProvider.get(BotExceptions.INVALID_DATE_STRING);
            return result;
        }
    }
}
