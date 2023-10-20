package ru.almaz.caravelletravelsreborn.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

import java.util.List;

public class UserFindAllByName extends UseCase<UserFindAllByName.InputValues, UserFindAllByName.OutputValues> {
    private final UserRepository repository;


    public UserFindAllByName(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        List<User> users = repository.findAllByName(input.name(), input.limit(), input.offset());

        return new OutputValues(users);
    }

    public record InputValues(String name, Integer limit, Integer offset) implements UseCase.InputValues {
    }
    public record OutputValues(List<User> user) implements UseCase.OutputValues {
    }
}
