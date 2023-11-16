package ru.almaz.caravelletravelsreborn.logic.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.EmailAlreadyUsedException;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserIdGenerator;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.IPasswordEncoder;

public class UserCreate extends UseCase<UserCreate.InputValues, UserCreate.OutputValues> {
    private final IUserRepository repository;
    private final IPasswordEncoder passwordEncoder;
    private final IUserIdGenerator idGenerator;


    public UserCreate(IUserRepository repository, IPasswordEncoder passwordEncoder, IUserIdGenerator idGenerator) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.idGenerator = idGenerator;
    }


    @Override
    public OutputValues execute(InputValues input) {
        if (input.user.getEmail() != null && repository.findByEmail(input.user.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException("Email is already in use!");
        }

        User user = input.user;
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return new OutputValues(repository.save(idGenerator.generate(), user));

    }

    public record InputValues(User user) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
