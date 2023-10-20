package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;

public class UserDelete extends UseCase<UserDelete.InputValues, UserDelete.OutputValues> {
    private final UserRepository repository;


    public UserDelete(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public OutputValues execute(InputValues input) {
        User userToDelete = repository.findById(input.userIdToDelete()).orElseThrow(() -> new UserNotFoundedException("User to delete not founded"));

        return new OutputValues(repository.delete(userToDelete));
    }

    public record InputValues(Long userIdToDelete) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
