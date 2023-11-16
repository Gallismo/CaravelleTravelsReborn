package ru.almaz.caravelletravelsreborn.presentation.bot;

import ru.almaz.caravelletravelsreborn.presentation.controller.BookingController;
import ru.almaz.caravelletravelsreborn.presentation.controller.UserController;
import ru.almaz.caravelletravelsreborn.presentation.dto.output.BotAnswerDTO;
import ru.almaz.caravelletravelsreborn.presentation.dto.input.CreateBookingDTO;
import ru.almaz.caravelletravelsreborn.presentation.dto.output.QuestionsDTO;
import ru.almaz.caravelletravelsreborn.presentation.dto.input.UpdateBookingDTO;
import ru.almaz.caravelletravelsreborn.presentation.exceptions.PresentationException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.UseCaseException;
import ru.almaz.caravelletravelsreborn.presentation.infrastructure.IBotTextProvider;
import ru.almaz.caravelletravelsreborn.presentation.model.UserView;
import ru.almaz.caravelletravelsreborn.presentation.validation.OtherInputValidator;

import java.util.List;

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
        result.text = textProvider.get(BotCommands.START);
        return result;
    }

    public BotAnswerDTO startCommand(UserView userView) {
        BotAnswerDTO result = new BotAnswerDTO();
        result.userView = userView;
        result.text = textProvider.get(BotCommands.START);
        return result;
    }

    public BotAnswerDTO createBooking(UserView userView) {
        BotAnswerDTO result = new BotAnswerDTO();
        try {
            result.bookingView = bookingController.createBooking(userView, new CreateBookingDTO());
            result.text = textProvider.get(BotCommands.CREATE_BOOKING);
        } catch (UseCaseException e) {
            result.text = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO fillBooking(UserView userView, UpdateBookingDTO dto) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            result.bookingView = bookingController.updateBooking(userView, dto);
            result.text = textProvider.get(BotCommands.FIll_BOOKING);
        } catch (UseCaseException e) {
            result.text = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO commitBooking(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            OtherInputValidator.validateId(bookingId);
            result.bookingView = bookingController.setCreatedStatusBooking(userView, Long.valueOf(bookingId));
            result.text = textProvider.get(BotCommands.COMMIT_BOOKING);
        } catch (UseCaseException e) {
            result.text = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO processBooking(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            OtherInputValidator.validateId(bookingId);
            result.bookingView = bookingController.setProcessedStatusBooking(userView, Long.valueOf(bookingId));
            result.text = textProvider.get(BotCommands.PROCESS_BOOKING);
        } catch (UseCaseException e) {
            result.text = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO cancelBooking(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            OtherInputValidator.validateId(bookingId);
            result.bookingView = bookingController.cancelBooking(userView, Long.valueOf(bookingId));
            result.text = textProvider.get(BotCommands.CANCEL_BOOKING);
        } catch (UseCaseException e) {
            result.text = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO findBookingById(UserView userView, String bookingId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            OtherInputValidator.validateId(bookingId);
            result.bookingView = bookingController.findBookingById(userView, Long.valueOf(bookingId));
            result.text = textProvider.get(BotCommands.CANCEL_BOOKING);
        } catch (UseCaseException e) {
            result.text = exceptionHandler.handleException(e);
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }

    public BotAnswerDTO informationCommand(UserView userView) {
        BotAnswerDTO result = new BotAnswerDTO();
        result.text = textProvider.get(BotCommands.INFORMATION);
        result.userView = userView;

        return result;
    }

    public QuestionsDTO questionsCommand(UserView userView) {
        QuestionsDTO result = new QuestionsDTO();
        result.answer.text = textProvider.get(BotCommands.QUESTIONS);
        result.answer.userView = userView;
        result.questions = textProvider.findAllCustomByTag(BotCommands.QUESTIONS.tag);

        return result;
    }

    public BotAnswerDTO getAnswerForQuestion(UserView userView, String customId) {
        BotAnswerDTO result = new BotAnswerDTO();

        try {
            OtherInputValidator.validateId(customId);
            result.text = textProvider.get(Long.valueOf(customId));
            result.userView = userView;
        } catch (PresentationException e) {
            result.text = exceptionHandler.handleException(e);
        }

        return result;
    }
}
