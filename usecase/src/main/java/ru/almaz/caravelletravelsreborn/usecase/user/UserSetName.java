package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserSetName extends UseCase<UserSetName.InputValues, UserSetName.OutputValues> {
    private final UserRepository repository;

    public UserSetName(UserRepository repository) {
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
