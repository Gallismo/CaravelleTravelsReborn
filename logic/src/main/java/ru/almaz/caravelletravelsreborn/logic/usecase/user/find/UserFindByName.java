package ru.almaz.caravelletravelsreborn.logic.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;

public class UserFindByName extends UseCase<UserFindByName.InputValues, UserFindByName.OutputValues> {
    private final IUserRepository repository;


    public UserFindByName(IUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findByName(input.name()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        return new OutputValues(user);
    }

    public record InputValues(String name) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
