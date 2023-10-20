package ru.almaz.caravelletravelsreborn.usecase.user;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;
import ru.almaz.caravelletravelsreborn.exceptions.user.UserNotFoundedException;
import ru.almaz.caravelletravelsreborn.usecase.UseCase;
import ru.almaz.caravelletravelsreborn.infrastructure.data.UserRepository;
import ru.almaz.caravelletravelsreborn.infrastructure.validators.UserValidator;

public class UserSetPhone extends UseCase<UserSetPhone.InputValues, UserSetPhone.OutputValues> {
    private final UserRepository repository;
    private final UserValidator validator;

    public UserSetPhone(UserRepository repository, UserValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = repository.findById(input.id()).orElseThrow(() -> new UserNotFoundedException("User not founded"));

        validator.validatePhone(input.phone());
        user.setPhone(input.phone());

        return new OutputValues(repository.save(user.getId(), user));
    }

    public record InputValues(Long id, String phone) implements UseCase.InputValues {
    }
    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
