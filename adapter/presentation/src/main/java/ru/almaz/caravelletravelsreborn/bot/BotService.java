package ru.almaz.caravelletravelsreborn.bot;

import ru.almaz.caravelletravelsreborn.controller.BookingController;
import ru.almaz.caravelletravelsreborn.controller.UserController;
import ru.almaz.caravelletravelsreborn.dto.output.BotAnswerDTO;
import ru.almaz.caravelletravelsreborn.dto.input.CreateBookingDTO;
import ru.almaz.caravelletravelsreborn.dto.output.QuestionsDTO;
import ru.almaz.caravelletravelsreborn.dto.input.UpdateBookingDTO;
import ru.almaz.caravelletravelsreborn.exceptions.PresentationException;
import ru.almaz.caravelletravelsreborn.exceptions.UseCaseException;
import ru.almaz.caravelletravelsreborn.infrastructure.IBotTextProvider;
import ru.almaz.caravelletravelsreborn.model.UserView;
import ru.almaz.caravelletravelsreborn.validation.BookingValidator;

import java.util.List;
import java.util.Map;

public class BotService {
    private final IBotTextProvider textProvider;
    private final BotExceptionHandler exceptionHandler;
    private final UserController userController;
    private final BookingController bookingController;

    public BotService(IBotTextProvider textProvider, UserController userController, BookingController bookingController) {
        this.textProvider = textProvider;
        this.exceptionHandler = new BotExceptionHandler(this.textProvider);

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

    public BotAnswerDTO startCommand() {
        BotAnswerDTO result = new BotAnswerDTO();
        result.userView = createNewUser();
        result.answer = textProvider.get(BotCommands.START);
        return result;
    }

    public BotAnswerDTO startCommand(UserView userView) {
        BotAnswerDTO result = new BotAnswerDTO();
        result.userView = userView;
        result.answer = textProvider.get(BotCommands.START);
        return result;
    }

    public BotAnswerDTO createBooking(UserView userView) {
        BotAnswerDTO result = new BotAnswerDTO();
        try {
            result.bookingView = bookingController.createBooking(userView, new CreateBookingDTO());
            result.answer = textProvider.get(BotCommands.CREATE_BOOKING);
        } catch (UseCaseException e) {
            result.answer = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.answer = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO fillBooking(UserView userView, UpdateBookingDTO dto) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            result.bookingView = bookingController.updateBooking(userView, dto);
            result.answer = textProvider.get(BotCommands.FIll_BOOKING);
        } catch (UseCaseException e) {
            result.answer = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.answer = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO commitBooking(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            BookingValidator.validateId(bookingId);
            result.bookingView = bookingController.setCreatedStatusBooking(userView, Long.valueOf(bookingId));
            result.answer = textProvider.get(BotCommands.COMMIT_BOOKING);
        } catch (UseCaseException e) {
            result.answer = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.answer = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO processBooking(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            BookingValidator.validateId(bookingId);
            result.bookingView = bookingController.setProcessedStatusBooking(userView, Long.valueOf(bookingId));
            result.answer = textProvider.get(BotCommands.PROCESS_BOOKING);
        } catch (UseCaseException e) {
            result.answer = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.answer = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO cancelBooking(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            BookingValidator.validateId(bookingId);
            result.bookingView = bookingController.cancelBooking(userView, Long.valueOf(bookingId));
            result.answer = textProvider.get(BotCommands.CANCEL_BOOKING);
        } catch (UseCaseException e) {
            result.answer = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.answer = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO findBookingById(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            BookingValidator.validateId(bookingId);
            result.bookingView = bookingController.findBookingById(userView, Long.valueOf(bookingId));
            result.answer = textProvider.get(BotCommands.CANCEL_BOOKING);
        } catch (UseCaseException e) {
            result.answer = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.answer = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO informationCommand(UserView userView) {
        BotAnswerDTO result = new BotAnswerDTO();
        result.answer = textProvider.get(BotCommands.INFORMATION);
        result.userView = userView;

        return result;
    }

    public QuestionsDTO questionsCommand(UserView userView) {
        QuestionsDTO result = new QuestionsDTO();
        result.answer.answer = textProvider.get(BotCommands.QUESTIONS);
        result.answer.userView = userView;
        result.questions = textProvider.findAllCustomByTag(BotCommands.QUESTIONS.tag);

        return result;
    }
}
