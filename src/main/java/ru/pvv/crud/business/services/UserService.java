package ru.pvv.crud.business.services;

import ru.pvv.crud.business.model.User;

import java.util.List;

public interface UserService {

    void saveUser(String name, String email, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);

    User getUserById(Long id);
}
