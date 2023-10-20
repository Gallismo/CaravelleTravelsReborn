package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.InvalidResetTokenException;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.PasswordEncoder;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserCredentialsResetsRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.UserValidator;

public class UserChangePassword extends UseCase<UserChangePassword.InputValues, UserChangePassword.OutputValues> {
    private final UserRepository repository;
    private final UserValidator validator;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialsResetsRepository resetsRepository;

    public UserChangePassword(UserRepository repository, UserValidator validator, PasswordEncoder passwordEncoder, UserCredentialsResetsRepository resetsRepository) {
        this.repository = repository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.resetsRepository = resetsRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        CredentialReset reset = resetsRepository.findByToken(input.resetToken()).orElseThrow(() -> new InvalidResetTokenException("Invalid token"));
        User user = repository.findById(reset.getUserId()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        if (reset.isExpired()) {
            throw new InvalidResetTokenException("Token is expired");
        }

        validator.validatePassword(input.newPassword());
        user.setPassword(passwordEncoder.encode(input.newPassword()));

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(String resetToken, String newPassword) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
