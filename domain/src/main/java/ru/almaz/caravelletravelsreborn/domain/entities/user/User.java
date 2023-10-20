package ru.almaz.caravelletravelsreborn.domain.entities.user;

public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean permissions = false;
    private String password;

    private User(Long id, String name, String email, String phone, boolean permissions, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.permissions = permissions;
        this.password = password;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private boolean permissions = false;
        private String password;

        UserBuilder() {}

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public UserBuilder permissions(boolean permissions) {
            this.permissions = permissions;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(id, name, email, phone, permissions, password);
        }
    }


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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", permissions=" + permissions +
                ", password='" + password + '\'' +
                '}';
    }
}
