package ru.almaz.caravelletravelsreborn.infrastructure.validators;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserValidationException;

public interface UserValidator {
    void validateUser(User user) throws UserValidationException;
    void validateEmail(String email) throws UserValidationException;
    void validatePhone(String phone) throws UserValidationException;
    void validatePassword(String password) throws UserValidationException;
}
