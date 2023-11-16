package ru.almaz.caravelletravelsreborn.logic.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.logic.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.logic.infrastructure.data.IUserRepository;
import ru.almaz.caravelletravelsreborn.logic.usecase.UseCase;

public class UserSetName extends UseCase<UserSetName.InputValues, UserSetName.OutputValues> {
    private final IUserRepository repository;

    public UserSetName(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findById(input.id()).orElseThrow(() -> new UserNotFoundedException("User not founded"));
        user.setName(input.name());

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(Long id, String name) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
