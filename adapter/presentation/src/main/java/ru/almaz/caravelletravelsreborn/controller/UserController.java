package ru.almaz.caravelletravelsreborn.controller;

import ru.almaz.caravelletravelsreborn.model.UserView;
import ru.almaz.caravelletravelsreborn.usecase.user.UserCreate;

public class UserController {
    private final UserCreate userCreate;

    public UserController(UserCreate userCreate) {
        this.userCreate = userCreate;
    }

    public UserView createUser(UserView userView) {
        UserCreate.InputValues input = new UserCreate.InputValues(userView.toUser());
        return UserView.fromUser(userCreate.execute(input).user());
    }
}
