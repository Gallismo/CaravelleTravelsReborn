package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.dto.EmailMessageDTO;
import ru.almaz.caravelletravelsreborn.infrastructure.EmailService;
import ru.almaz.caravelletravelsreborn.infrastructure.ResetCredentialsTokenGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.CredentialsResetsIdGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserCredentialsResetsRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserChangePasswordRequest extends UseCase<UserChangePasswordRequest.InputValues, UserChangePasswordRequest.OutputValues> {
    private final UserRepository repository;
    private final UserCredentialsResetsRepository resetsRepository;
    private final CredentialsResetsIdGenerator idGenerator;
    private final ResetCredentialsTokenGenerator tokenGenerator;
    private final EmailService emailService;

    public UserChangePasswordRequest(UserRepository repository, UserCredentialsResetsRepository resetsRepository, CredentialsResetsIdGenerator idGenerator, ResetCredentialsTokenGenerator tokenGenerator, EmailService emailService) {
        this.repository = repository;
        this.resetsRepository = resetsRepository;
        this.idGenerator = idGenerator;
        this.tokenGenerator = tokenGenerator;
        this.emailService = emailService;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findByEmail(input.email()).orElseThrow(() -> new UserNotFoundedException("User not founded"));
        CredentialReset reset = new CredentialReset(idGenerator.generate(), user.getId(),
                tokenGenerator.generate(), CredentialReset.CredentialResetType.PASSWORD);
        reset = resetsRepository.save(reset.getId(), reset);

        emailService.sendEmail(new EmailMessageDTO(user.getEmail(), "Password reset", reset.getToken()));

        return new OutputValues();
    }

    public record InputValues(String email) implements UseCase.InputValues {
    }
    public record OutputValues() implements UseCase.OutputValues {
    }
}
