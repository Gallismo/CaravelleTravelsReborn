package ru.almaz.caravelletravelsreborn.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

import java.util.List;

public class UserFindAll extends UseCase<UserFindAll.InputValues, UserFindAll.OutputValues> {
    private final UserRepository repository;


    public UserFindAll(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        List<User> users = repository.findAll(input.limit(), input.offset());

        return new OutputValues(users);
    }

    public record InputValues(Integer limit, Integer offset) implements UseCase.InputValues {
    }
    public record OutputValues(List<User> user) implements UseCase.OutputValues {
    }
}
