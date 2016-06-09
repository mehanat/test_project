package ru.testproject.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testproject.model.User;

import java.util.List;

/**
 * Created by Анатолий on 08.06.2016.
 */
public interface UserService {
    List<User> getUsers();
    User getUser(int id);
    void deleteUser(int id);
    User saveUser(User user);
}
