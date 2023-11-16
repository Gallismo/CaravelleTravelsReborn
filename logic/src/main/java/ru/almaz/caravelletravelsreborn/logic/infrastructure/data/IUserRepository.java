package ru.almaz.caravelletravelsreborn.logic.infrastructure.data;

import ru.almaz.caravelletravelsreborn.domain.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    List<User> findAll(Integer limit, Integer offset);
    List<User> findAllByName(String name, Integer limit, Integer offset);
    List<User> findAllByPhone(String phone, Integer limit, Integer offset);
    List<User> findAllByPermissions(boolean permissions, Integer limit, Integer offset);

    User save(Long id, User user);
    User delete(User user);
}
