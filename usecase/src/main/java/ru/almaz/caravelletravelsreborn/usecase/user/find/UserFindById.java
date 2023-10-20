package ru.almaz.caravelletravelsreborn.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserFindById extends UseCase<UserFindById.InputValues, UserFindById.OutputValues> {
    private final UserRepository repository;


    public UserFindById(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findById(input.userId()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        return new OutputValues(user);
    }

    public record InputValues(Long userId) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
