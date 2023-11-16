package ru.almaz.caravelletravelsreborn.presentation.controller;

import ru.almaz.caravelletravelsreborn.presentation.model.UserView;
import ru.almaz.caravelletravelsreborn.logic.usecase.user.UserCreate;
import ru.almaz.caravelletravelsreborn.logic.usecase.user.UserLogin;
import ru.almaz.caravelletravelsreborn.presentation.validation.UserValidator;

public class UserController {
    private final UserCreate userCreate;
    private final UserLogin userLogin;

    public UserController(UserCreate userCreate, UserLogin userLogin) {
        this.userCreate = userCreate;
        this.userLogin = userLogin;
    }

    public UserView createUser(UserView userView) {
        UserCreate.InputValues input = new UserCreate.InputValues(userView.toUser());
        return UserView.fromUser(userCreate.execute(input).user());
    }

    public UserView loginUser(String email, String password) {
        UserValidator.validateEmail(email);
        UserLogin.InputValues input = new UserLogin.InputValues(email, password);
        return UserView.fromUser(userLogin.execute(input).user());
    }
}
