package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.CredentialReset;
import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.dto.EmailResetMessageDTO;
import ru.almaz.caravelletravelsreborn.infrastructure.IEmailProvider;
import ru.almaz.caravelletravelsreborn.infrastructure.IResetCredentialsTokenGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.ICredentialsResetsIdGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.ICredentialsResetsRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IUserRepository;

public class UserChangePasswordRequest extends UseCase<UserChangePasswordRequest.InputValues, UserChangePasswordRequest.OutputValues> {
    private final IUserRepository repository;
    private final ICredentialsResetsRepository resetsRepository;
    private final ICredentialsResetsIdGenerator idGenerator;
    private final IResetCredentialsTokenGenerator tokenGenerator;
    private final IEmailProvider emailService;

    public UserChangePasswordRequest(IUserRepository repository, ICredentialsResetsRepository resetsRepository, ICredentialsResetsIdGenerator idGenerator, IResetCredentialsTokenGenerator tokenGenerator, IEmailProvider emailService) {
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

        emailService.sendResetMessage(new EmailResetMessageDTO(user, reset));

        return new OutputValues(reset);
    }

    public record InputValues(String email) implements UseCase.InputValues {
    }
    public record OutputValues(CredentialReset reset) implements UseCase.OutputValues {
    }
}
