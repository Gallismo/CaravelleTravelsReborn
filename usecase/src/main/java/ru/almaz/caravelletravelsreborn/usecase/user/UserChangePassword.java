package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.InvalidResetTokenException;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.IPasswordEncoder;
import ru.almaz.caravelletravelsreborn.infrastructure.data.ICredentialsResetsRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IUserRepository;

public class UserChangePassword extends UseCase<UserChangePassword.InputValues, UserChangePassword.OutputValues> {
    private final IUserRepository repository;
    private final IPasswordEncoder passwordEncoder;
    private final ICredentialsResetsRepository resetsRepository;

    public UserChangePassword(IUserRepository repository, IPasswordEncoder passwordEncoder, ICredentialsResetsRepository resetsRepository) {
        this.repository = repository;
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

        user.setPassword(passwordEncoder.encode(input.newPassword()));

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(String resetToken, String newPassword) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
