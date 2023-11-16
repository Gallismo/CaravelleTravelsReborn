package ru.almaz.caravelletravelsreborn.presentation.bot;

import ru.almaz.caravelletravelsreborn.presentation.exceptions.*;
import ru.almaz.caravelletravelsreborn.presentation.infrastructure.IBotTextProvider;
import ru.almaz.caravelletravelsreborn.logic.exceptions.BookingException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.UseCaseException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.UserException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.booking.*;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.*;

import java.util.List;

public class BotExceptionHandler {
    private final IBotTextProvider textProvider;

    public BotExceptionHandler(IBotTextProvider textProvider) {
        this.textProvider = textProvider;
    }

    // USE CASE EXCEPTIONS
    public List<String> handleException(UseCaseException e) {
        if (e instanceof UserException) { // USER USE CASE EXCEPTIONS
            if (e instanceof UserNotFoundedException) {
                return textProvider.get(BotExceptions.USER_NOT_FOUND);
            } else if (e instanceof NoPermissionsException) {
                return textProvider.get(BotExceptions.NO_PERMISSION);
            } else if (e instanceof InvalidResetTokenException) {
                return textProvider.get(BotExceptions.INVALID_RESET_TOKEN);
            } else if (e instanceof InvalidCredentialsException) {
                return textProvider.get(BotExceptions.INVALID_CREDENTIALS);
            } else if (e instanceof EmailAlreadyUsedException) {
                return textProvider.get(BotExceptions.EMAIL_USED);
            }

        } else if (e instanceof BookingException) { // BOOKING USE CASE EXCEPTIONS
            if (e instanceof BookingNotFoundException) {
                return textProvider.get(BotExceptions.BOOKING_NOT_FOUND);
            } else if (e instanceof BookingNotFilledException) {
                return textProvider.get(BotExceptions.BOOKING_NOT_FILLED);
            } else if (e instanceof BookingIsNotCreatedBySpecUserException) {
                return textProvider.get(BotExceptions.USER_NOT_CREATOR_OF_BOOKING);
            } else if (e instanceof BookingCommittedException) {
                return textProvider.get(BotExceptions.BOOKING_ALREADY_COMMITTED);
            } else if (e instanceof BookingAlreadyExistException) {
                return textProvider.get(BotExceptions.BOOKING_EXIST);
            }
        }

        return textProvider.get(BotExceptions.UNHANDLED_USECASE_EXCEPTION);
    }

    // PRESENTATION EXCEPTIONS
    public List<String> handleException(PresentationException e) {
        if (e instanceof BookingValidationException converted) {
            switch (converted.reason) {
                case DATE -> {return textProvider.get(BotExceptions.INVALID_DATE);}
                case PHONE -> {return textProvider.get(BotExceptions.INVALID_PHONE);}
                case PASS_COUNT -> {return textProvider.get(BotExceptions.INVALID_PASS_COUNT);}
            }
        } else if (e instanceof UserValidationException converted) {
            switch (converted.reason) {
                case PHONE -> {return textProvider.get(BotExceptions.INVALID_PHONE);}
                case PASSWORD -> {return textProvider.get(BotExceptions.INVALID_PASSWORD);}
                case EMAIL -> {return textProvider.get(BotExceptions.INVALID_EMAIL);}
            }
        } else if (e instanceof OtherInputValidationException converted) {
            switch (converted.reason) {
                case ID -> {return textProvider.get(BotExceptions.INVALID_ID);}
            }
        } else if (e instanceof TextsNotFoundException) {
            return textProvider.get(BotExceptions.TEXT_NOT_FOUND);
        }

        return textProvider.get(BotExceptions.UNHANDLED_PRESENTATION_EXCEPTION);
    }
}
