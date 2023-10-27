package ru.almaz.caravelletravelsreborn.usecase.user.find;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IUserRepository;

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
