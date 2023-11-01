package ru.almaz.caravelletravelsreborn.controller;

import ru.almaz.caravelletravelsreborn.model.UserView;
import ru.almaz.caravelletravelsreborn.usecase.user.UserCreate;
import ru.almaz.caravelletravelsreborn.usecase.user.UserLogin;
import ru.almaz.caravelletravelsreborn.validation.UserValidator;

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
