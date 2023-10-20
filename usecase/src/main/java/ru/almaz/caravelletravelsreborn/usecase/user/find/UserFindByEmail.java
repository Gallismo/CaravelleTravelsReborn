package ru.almaz.caravelletravelsreborn.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserFindByEmail extends UseCase<UserFindByEmail.InputValues, UserFindByEmail.OutputValues> {
    private final UserRepository repository;


    public UserFindByEmail(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findByEmail(input.email()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        return new OutputValues(user);
    }

    public record InputValues(String email) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
