package ru.testproject.DAO;

import org.springframework.stereotype.Repository;
import ru.testproject.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Анатолий on 08.06.2016.
 */
public interface UserDAO {
    List<User> getUsers();
    User getUser(int id);
    void deleteUser(int id);
    User saveUser(User user);
}
