package ru.almaz.caravelletravelsreborn.logic.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;

public class UserFindByPhone extends UseCase<UserFindByPhone.InputValues, UserFindByPhone.OutputValues> {
    private final IUserRepository repository;


    public UserFindByPhone(IUserRepository repository) {
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
