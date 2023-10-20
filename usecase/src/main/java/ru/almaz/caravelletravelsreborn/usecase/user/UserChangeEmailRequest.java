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

public class UserChangeEmailRequest extends UseCase<UserChangeEmailRequest.InputValues, UserChangeEmailRequest.OutputValues> {
    private final UserRepository repository;
    private final ResetCredentialsTokenGenerator tokenGenerator;
    private final UserCredentialsResetsRepository resetsRepository;
    private final CredentialsResetsIdGenerator idGenerator;
    private final EmailService emailService;


    public UserChangeEmailRequest(UserRepository repository, ResetCredentialsTokenGenerator tokenGenerator, UserCredentialsResetsRepository resetsRepository, CredentialsResetsIdGenerator idGenerator, EmailService emailService) {
        this.repository = repository;
        this.tokenGenerator = tokenGenerator;
        this.resetsRepository = resetsRepository;
        this.idGenerator = idGenerator;
        this.emailService = emailService;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findById(input.id()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        CredentialReset reset = new CredentialReset(idGenerator.generate(), user.getId(),
                tokenGenerator.generate(), CredentialReset.CredentialResetType.EMAIL);

        reset = resetsRepository.save(reset.getId(), reset);

        emailService.sendEmail(new EmailMessageDTO(user.getEmail(), "Email reset", reset.getToken()));

        return new OutputValues();
    }

    public record InputValues(Long id) implements UseCase.InputValues {
    }
    public record OutputValues() implements UseCase.OutputValues {
    }
}
