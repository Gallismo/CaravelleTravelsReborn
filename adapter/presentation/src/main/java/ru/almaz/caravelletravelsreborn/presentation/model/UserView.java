package ru.almaz.caravelletravelsreborn.presentation.model;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;

public class UserView {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean permissions = false;
    private String password;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isPermissions() {
        return permissions;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPermissions(boolean permissions) {
        this.permissions = permissions;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .phone(phone)
                .permissions(permissions)
                .password(password)
                .build();
    }

    public static UserView fromUser(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setEmail(user.getEmail());
        userView.setName(user.getName());
        userView.setPhone(user.getPhone());
        userView.setPermissions(userView.isPermissions());
        return userView;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", permissions=" + permissions +
                ", password='" + password + '\'' +
                '}';
    }
}
