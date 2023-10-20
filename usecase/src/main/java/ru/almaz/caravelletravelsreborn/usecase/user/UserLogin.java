package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.InvalidCredentialsException;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.PasswordEncoder;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserLogin extends UseCase<UserLogin.InputValues, UserLogin.OutputValues> {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public UserLogin(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findByEmail(input.email()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        if (!passwordEncoder.isEquals(input.password, user.getPassword())) {
            throw new InvalidCredentialsException("Wrong password");
        }

        return new OutputValues(user);
    }

    public record InputValues(String email, String password) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
