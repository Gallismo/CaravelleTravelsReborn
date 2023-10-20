package ru.almaz.caravelletravelsreborn.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserFindByPhone extends UseCase<UserFindByPhone.InputValues, UserFindByPhone.OutputValues> {
    private final UserRepository repository;


    public UserFindByPhone(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findByPhone(input.phone()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        return new OutputValues(user);
    }

    public record InputValues(String phone) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
