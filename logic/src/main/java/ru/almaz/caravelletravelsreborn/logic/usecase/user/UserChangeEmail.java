package ru.almaz.caravelletravelsreborn.logic.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.EmailAlreadyUsedException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.InvalidResetTokenException;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.ICredentialsResetsRepository;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;

public class UserChangeEmail extends UseCase<UserChangeEmail.InputValues, UserChangeEmail.OutputValues> {
    private final IUserRepository repository;
    private final ICredentialsResetsRepository resetsRepository;

    public UserChangeEmail(IUserRepository repository, ICredentialsResetsRepository resetsRepository) {
        this.repository = repository;
        this.resetsRepository = resetsRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        CredentialReset reset = resetsRepository.findByToken(input.resetToken()).orElseThrow(() -> new InvalidResetTokenException("Invalid token"));
        User user = repository.findById(reset.getUserId()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        if (repository.findByEmail(input.newEmail()).isPresent()) {
            throw new EmailAlreadyUsedException("Email used!");
        }
        if (reset.isExpired()) {
            throw new InvalidResetTokenException("Token is expired");
        }

        user.setEmail(input.newEmail());

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(String resetToken, String newEmail) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
