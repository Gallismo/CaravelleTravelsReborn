package ru.almaz.caravelletravelsreborn.validation;

import ru.almaz.caravelletravelsreborn.exceptions.user.UserValidationException;
import ru.almaz.caravelletravelsreborn.model.UserView;

public class UserValidator {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)[\\w-]{2,4}$";
    private static final String PHONE_REGEX = "(^\\+?\\d-\\d{3}-\\d{3}-\\d{2}-\\d{2}$|^\\+?\\d \\d{3} \\d{3} \\d{2} \\d{2}$|^\\+?\\d{11}$)";
    private static final String PASSWORD_REGEX = ".{8,}";

    public static void validate(UserView userView) {
        if (userView.getEmail() != null) {
            validateEmail(userView.getEmail());
        }
        if (userView.getPhone() != null) {
            validatePhone(userView.getPhone());
        }
        if (userView.getPassword() != null) {
            validatePassword(userView.getPassword());
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new UserValidationException("Email invalid", UserValidationException.Reason.EMAIL);
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw new UserValidationException("Phone invalid", UserValidationException.Reason.PHONE);
        }
    }

    public static void validatePassword(String password) {
        if (password == null || !password.matches(PASSWORD_REGEX)) {
            throw new UserValidationException("Password length must be at least 8 characters", UserValidationException.Reason.PASSWORD);
        }
    }
}
