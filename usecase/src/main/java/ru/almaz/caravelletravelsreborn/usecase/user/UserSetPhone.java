package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.IUserRepository;

public class UserSetPhone extends UseCase<UserSetPhone.InputValues, UserSetPhone.OutputValues> {
    private final IUserRepository repository;

    public UserSetPhone(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findById(input.id()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        user.setPhone(input.phone());

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(Long id, String phone) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
