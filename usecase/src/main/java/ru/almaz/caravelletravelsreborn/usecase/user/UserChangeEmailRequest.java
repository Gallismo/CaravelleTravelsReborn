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

public class UserChangeEmailRequest extends UseCase<UserChangeEmailRequest.InputValues, UserChangeEmailRequest.OutputValues> {
    private final IUserRepository repository;
    private final IResetCredentialsTokenGenerator tokenGenerator;
    private final ICredentialsResetsRepository resetsRepository;
    private final ICredentialsResetsIdGenerator idGenerator;
    private final IEmailProvider emailService;


    public UserChangeEmailRequest(IUserRepository repository, IResetCredentialsTokenGenerator tokenGenerator, ICredentialsResetsRepository resetsRepository, ICredentialsResetsIdGenerator idGenerator, IEmailProvider emailService) {
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

        emailService.sendResetMessage(new EmailResetMessageDTO(user,  reset));

        return new OutputValues(reset);
    }

    public record InputValues(Long id) implements UseCase.InputValues {
    }
    public record OutputValues(CredentialReset reset) implements UseCase.OutputValues {
    }
}
