package ru.almaz.caravelletravelsreborn.logic.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;

import java.util.List;

public class UserFindAll extends UseCase<UserFindAll.InputValues, UserFindAll.OutputValues> {
    private final IUserRepository repository;


    public UserFindAll(IUserRepository repository) {
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
