package ru.pvv.crud.business.dao;

import ru.pvv.crud.business.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(String name, String email, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);

    User getUserById(Long id);
}
