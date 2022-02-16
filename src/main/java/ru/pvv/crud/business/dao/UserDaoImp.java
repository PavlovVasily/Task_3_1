package ru.pvv.crud.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pvv.crud.business.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void saveUser(String name, String email, byte age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setEmail(email);

        em.persist(user);
        em.flush();
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        em.remove(getUserById(id));
        em.flush();
    }

    @Override
    public List<User> getAllUsers() {

        TypedQuery<User> query =
                em.createQuery("SELECT u FROM User u", User.class);
        List<User> results = query.getResultList();

        return results;

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Пользователя с Id=" + id + " нет");
        }
        return user;
    }


}
