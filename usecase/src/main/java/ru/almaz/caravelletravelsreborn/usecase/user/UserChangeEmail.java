package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.InvalidResetTokenException;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserCredentialsResetsRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.UserValidator;

public class UserChangeEmail extends UseCase<UserChangeEmail.InputValues, UserChangeEmail.OutputValues> {
    private final UserRepository repository;
    private final UserValidator validator;
    private final UserCredentialsResetsRepository resetsRepository;

    public UserChangeEmail(UserRepository repository, UserValidator validator, UserCredentialsResetsRepository resetsRepository) {
        this.repository = repository;
        this.validator = validator;
        this.resetsRepository = resetsRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        CredentialReset reset = resetsRepository.findByToken(input.resetToken()).orElseThrow(() -> new InvalidResetTokenException("Invalid token"));
        User user = repository.findById(reset.getUserId()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        if (reset.isExpired()) {
            throw new InvalidResetTokenException("Token is expired");
        }

        validator.validateEmail(input.newEmail());
        user.setEmail(input.newEmail());

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(String resetToken, String newEmail) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
