package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.EmailAlreadyUsedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.PasswordEncoder;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserIdGenerator;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.UserValidator;

public class UserCreate extends UseCase<UserCreate.InputValues, UserCreate.OutputValues> {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator validator;
    private final UserIdGenerator idGenerator;


    public UserCreate(UserRepository repository, PasswordEncoder passwordEncoder, UserValidator validator, UserIdGenerator idGenerator) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.idGenerator = idGenerator;
    }


    @Override
    public OutputValues execute(InputValues input) {
        if (input.email() != null && repository.findByEmail(input.email()).isPresent()) {
            throw new EmailAlreadyUsedException("Email is already in use!");
        }

        User user = User.builder()
                .name(input.name())
                .phone(input.phone())
                .email(input.email())
                .permissions(input.permissions())
                .password(passwordEncoder.encode(input.password()))
                .build();

        validator.validateUser(user);

        return new OutputValues(repository.save(idGenerator.generate(), user));

    }

    public record InputValues(String name, String email, String phone,
                              String password, boolean permissions) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
