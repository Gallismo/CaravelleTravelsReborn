package ru.almaz.caravelletravelsreborn.logic.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;

import java.util.List;

public class UserFindAllByPhone extends UseCase<UserFindAllByPhone.InputValues, UserFindAllByPhone.OutputValues> {
    private final IUserRepository repository;


    public UserFindAllByPhone(IUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        List<User> users = repository.findAllByPhone(input.phone(), input.limit(), input.offset());

        return new OutputValues(users);
    }

    public record InputValues(String phone, Integer limit, Integer offset) implements UseCase.InputValues {
    }
    public record OutputValues(List<User> user) implements UseCase.OutputValues {
    }
}
